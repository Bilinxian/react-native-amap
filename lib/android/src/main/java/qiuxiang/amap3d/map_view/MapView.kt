package qiuxiang.amap3d.map_view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.TextureMapView
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.Marker
import com.amap.api.maps.model.MyLocationStyle
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter
import qiuxiang.amap3d.getFloat
import qiuxiang.amap3d.toJson
import qiuxiang.amap3d.toLatLng
import qiuxiang.amap3d.toPoint

@SuppressLint("ViewConstructor")
class MapView(context: ThemedReactContext) : TextureMapView(context) {
  private val markerMap = HashMap<String, qiuxiang.amap3d.map_view.Marker>()
  private val polylineMap = HashMap<String, Polyline>()
  private var initialCameraPosition: ReadableMap? = null
  private var locationStyle: MyLocationStyle

  // Fabric 事件发射器
  private val eventEmitter: RCTEventEmitter?
    get() {
      val reactContext = context as? ThemedReactContext
      return reactContext?.getJSModule(RCTEventEmitter::class.java)
    }

  init {
    super.onCreate(null)

    locationStyle = MyLocationStyle()
    locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER)
    map.myLocationStyle = locationStyle

    setupMapListeners()
  }

  private fun setupMapListeners() {
    map.setOnMapLoadedListener { emit(id, "onLoad") }
    map.setOnMapClickListener { latLng -> emit(id, "onPress", latLng.toJson()) }
    map.setOnPOIClickListener { poi -> emit(id, "onPressPoi", poi.toJson()) }
    map.setOnMapLongClickListener { latLng -> emit(id, "onLongPress", latLng.toJson()) }
    map.setOnPolylineClickListener { polyline -> emit(polylineMap[polyline.id]?.id, "onPress") }

    map.setOnMarkerClickListener { marker ->
      markerMap[marker.id]?.let { emit(it.id, "onPress") }
      true
    }

    map.setOnMarkerDragListener(object : AMap.OnMarkerDragListener {
      override fun onMarkerDragStart(marker: Marker) {
        emit(markerMap[marker.id]?.id, "onDragStart")
      }

      override fun onMarkerDrag(marker: Marker) {
        emit(markerMap[marker.id]?.id, "onDrag")
      }

      override fun onMarkerDragEnd(marker: Marker) {
        emit(markerMap[marker.id]?.id, "onDragEnd", marker.position.toJson())
      }
    })

    map.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
      override fun onCameraChangeFinish(position: CameraPosition) {
        emit(id, "onCameraIdle", Arguments.createMap().apply {
          putMap("cameraPosition", position.toJson())
          putMap("latLngBounds", map.projection.visibleRegion.latLngBounds.toJson())
        })
      }

      override fun onCameraChange(position: CameraPosition) {
        emit(id, "onCameraMove", Arguments.createMap().apply {
          putMap("cameraPosition", position.toJson())
          putMap("latLngBounds", map.projection.visibleRegion.latLngBounds.toJson())
        })
      }
    })

    map.setOnMultiPointClickListener { item ->
      item.customerId?.split("_")?.let {
        if (it.size >= 2) {
          emit(
            it[0].toInt(),
            "onPress",
            Arguments.createMap().apply { putInt("index", it[1].toInt()) },
          )
        }
      }
      false
    }

    map.setOnMyLocationChangeListener { location ->
      if (location.time > 0) {
        emit(id, "onLocation", location.toJson())
      }
    }
  }

  fun emit(viewId: Int?, event: String, data: WritableMap = Arguments.createMap()) {
    viewId?.let { id ->
      eventEmitter?.receiveEvent(id, event, data)
    }
  }

  fun add(child: View) {
    if (child is Overlay) {
      child.add(map)
      if (child is qiuxiang.amap3d.map_view.Marker) {
        child.marker?.id?.let { markerId ->
          markerMap[markerId] = child
        }
      }
      if (child is Polyline) {
        child.polyline?.id?.let { polylineId ->
          polylineMap[polylineId] = child
        }
      }
    }
  }

  fun remove(child: View) {
    if (child is Overlay) {
      child.remove()
      if (child is qiuxiang.amap3d.map_view.Marker) {
        child.marker?.id?.let { markerId ->
          markerMap.remove(markerId)
        }
      }
      if (child is Polyline) {
        child.polyline?.id?.let { polylineId ->
          polylineMap.remove(polylineId)
        }
      }
    }
  }

  private val animateCallback = object : AMap.CancelableCallback {
    override fun onCancel() {}
    override fun onFinish() {}
  }

  fun moveCamera(args: ReadableArray?) {
    val current = map.cameraPosition
    val position = args?.getMap(0)
    position?.let {
      val target = it.getMap("target")?.toLatLng() ?: current.target
      val zoom = it.getFloat("zoom") ?: current.zoom
      val tilt = it.getFloat("tilt") ?: current.tilt
      val bearing = it.getFloat("bearing") ?: current.bearing
      val cameraUpdate = CameraUpdateFactory.newCameraPosition(
        CameraPosition(target, zoom, tilt, bearing)
      )
      val duration = args.getInt(1).toLong()
      map.animateCamera(cameraUpdate, duration, animateCallback)
    }
  }

  fun setInitialCameraPosition(position: ReadableMap) {
    if (initialCameraPosition == null) {
      initialCameraPosition = position
      moveCamera(Arguments.createArray().apply {
        pushMap(Arguments.createMap().apply { merge(position) })
        pushInt(0)
      })
    }
  }

  fun call(args: ReadableArray?) {
    val callbackId = args?.getDouble(0)
    val method = args?.getString(1)

    if (callbackId != null && method == "getLatLng") {
      val pointMap = args.getMap(2)
      pointMap?.let {
        val point = it.toPoint()
        val latLng = map.projection.fromScreenLocation(point)
        callback(callbackId, latLng.toJson())
      }
    }
  }

  private fun callback(id: Double, data: WritableMap) {
    emit(this.id, "onCallback", Arguments.createMap().apply {
      putDouble("id", id)
      putMap("data", data)
    })
  }

  // Fabric 生命周期方法
  override fun onStart() {
    super.onStart()
  }

  override fun onResume() {
    super.onResume()
  }

  override fun onPause() {
    super.onPause()
  }

  override fun onStop() {
    super.onStop()
  }

  override fun onDestroy() {
    super.onDestroy()
  }
}
