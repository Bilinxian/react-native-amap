package qiuxiang.amap3d.map_view

import android.view.View
import com.amap.api.maps.CameraUpdateFactory
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import qiuxiang.amap3d.toLatLng

@Suppress("unused")
internal class MapViewManager : ViewGroupManager<MapView>() {

  companion object {
    private const val REACT_CLASS = "AMapView"

    // 命令常量
    private const val COMMAND_MOVE_CAMERA = 1
    private const val COMMAND_CALL = 2
  }

  override fun getName(): String {
    return REACT_CLASS
  }

  override fun createViewInstance(reactContext: ThemedReactContext): MapView {
    return MapView(reactContext)
  }

  override fun onDropViewInstance(view: MapView) {
    view.onDestroy()
    super.onDropViewInstance(view)
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return mutableMapOf(
      "onLoad" to mutableMapOf("registrationName" to "onLoad"),
      "onPress" to mutableMapOf("registrationName" to "onPress"),
      "onPressPoi" to mutableMapOf("registrationName" to "onPressPoi"),
      "onLongPress" to mutableMapOf("registrationName" to "onLongPress"),
      "onCameraMove" to mutableMapOf("registrationName" to "onCameraMove"),
      "onCameraIdle" to mutableMapOf("registrationName" to "onCameraIdle"),
      "onLocation" to mutableMapOf("registrationName" to "onLocation"),
      "onCallback" to mutableMapOf("registrationName" to "onCallback"),
      "onDragStart" to mutableMapOf("registrationName" to "onDragStart"),
      "onDrag" to mutableMapOf("registrationName" to "onDrag"),
      "onDragEnd" to mutableMapOf("registrationName" to "onDragEnd")
    )
  }

  override fun getCommandsMap(): MutableMap<String, Int> {
    return mutableMapOf(
      "moveCamera" to COMMAND_MOVE_CAMERA,
      "call" to COMMAND_CALL
    )
  }

  override fun receiveCommand(root: MapView, commandId: Int, args: ReadableArray?) {
    when (commandId) {
      COMMAND_MOVE_CAMERA -> root.moveCamera(args)
      COMMAND_CALL -> root.call(args)
    }
  }

  override fun receiveCommand(root: MapView, commandId: String, args: ReadableArray?) {
    when (commandId) {
      "moveCamera" -> root.moveCamera(args)
      "call" -> root.call(args)
    }
  }

  override fun addView(parent: MapView, child: View, index: Int) {
    parent.add(child)
  }

  override fun removeViewAt(parent: MapView, index: Int) {
    val child = parent.getChildAt(index)
    parent.remove(child)
  }

  override fun getChildCount(parent: MapView): Int {
    return parent.childCount
  }

  override fun getChildAt(parent: MapView, index: Int): View {
    return parent.getChildAt(index)
  }

  // React Props
  @ReactProp(name = "initialCameraPosition")
  fun setInitialCameraPosition(view: MapView, position: ReadableMap) {
    view.setInitialCameraPosition(position)
  }

  @ReactProp(name = "myLocationEnabled")
  fun setMyLocationEnabled(view: MapView, enabled: Boolean) {
    view.map.isMyLocationEnabled = enabled
  }

  @ReactProp(name = "indoorViewEnabled")
  fun setIndoorViewEnabled(view: MapView, enabled: Boolean) {
    view.map.showIndoorMap(enabled)
  }

  @ReactProp(name = "buildingsEnabled")
  fun setBuildingsEnabled(view: MapView, enabled: Boolean) {
    view.map.showBuildings(enabled)
  }

  @ReactProp(name = "compassEnabled")
  fun setCompassEnabled(view: MapView, show: Boolean) {
    view.map.uiSettings.isCompassEnabled = show
  }

  @ReactProp(name = "zoomControlsEnabled")
  fun setZoomControlsEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isZoomControlsEnabled = enabled
  }

  @ReactProp(name = "scaleControlsEnabled")
  fun setScaleControlsEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isScaleControlsEnabled = enabled
  }

  @ReactProp(name = "language")
  fun setLanguage(view: MapView, language: String) {
    view.map.setMapLanguage(language)
  }

  @ReactProp(name = "myLocationButtonEnabled")
  fun setMyLocationButtonEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isMyLocationButtonEnabled = enabled
  }

  @ReactProp(name = "trafficEnabled")
  fun setTrafficEnabled(view: MapView, enabled: Boolean) {
    view.map.isTrafficEnabled = enabled
  }

  @ReactProp(name = "maxZoom")
  fun setMaxZoom(view: MapView, zoomLevel: Float) {
    view.map.maxZoomLevel = zoomLevel
  }

  @ReactProp(name = "minZoom")
  fun setMinZoom(view: MapView, zoomLevel: Float) {
    view.map.minZoomLevel = zoomLevel
  }

  @ReactProp(name = "mapType")
  fun setMapType(view: MapView, mapType: Int) {
    view.map.mapType = mapType + 1
  }

  @ReactProp(name = "zoomGesturesEnabled")
  fun setZoomGesturesEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isZoomGesturesEnabled = enabled
  }

  @ReactProp(name = "scrollGesturesEnabled")
  fun setScrollGesturesEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isScrollGesturesEnabled = enabled
  }

  @ReactProp(name = "rotateGesturesEnabled")
  fun setRotateGesturesEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isRotateGesturesEnabled = enabled
  }

  @ReactProp(name = "tiltGesturesEnabled")
  fun setTiltGesturesEnabled(view: MapView, enabled: Boolean) {
    view.map.uiSettings.isTiltGesturesEnabled = enabled
  }

  @ReactProp(name = "cameraPosition")
  fun setCameraPosition(view: MapView, center: ReadableMap) {
    view.map.moveCamera(CameraUpdateFactory.changeLatLng(center.toLatLng()))
  }
}
