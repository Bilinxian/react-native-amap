package com.amaps

import android.content.Context
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.amap.api.maps.AMap
import com.amap.api.maps.TextureMapView
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng

class AmapMapView(context: ReactContext) : TextureMapView(context) {
  private var reactContext: ReactContext = context
  private var map: AMap? = null

  init {
    // 创建地图
    this.onCreate(null)
    // 获取地图实例
    this.map = this.getMap()
    setupMap()
  }

  private fun setupMap() {
    map?.apply {
      setOnCameraChangeListener(cameraChangeListener)
      uiSettings.isZoomControlsEnabled = false
      uiSettings.isCompassEnabled = true
    }
  }

  fun setRegion(region: ReadableMap?) {
    region?.let {
      val latitude = it.getDouble("latitude")
      val longitude = it.getDouble("longitude")
      val latitudeDelta = it.getDouble("latitudeDelta")
      val longitudeDelta = it.getDouble("longitudeDelta")

      val latLng = LatLng(latitude, longitude)
      val cameraPosition = CameraPosition.Builder()
        .target(latLng)
        .zoom(getZoomLevel(latitudeDelta, longitudeDelta))
        .build()

      map?.moveCamera(
        com.amap.api.maps.CameraUpdateFactory
          .newCameraPosition(cameraPosition)
      )
    }
  }

  private fun getZoomLevel(latDelta: Double, lngDelta: Double): Float {
    return (16 - Math.log(latDelta * 2) / Math.log(2.0)).toFloat()
  }

  private val cameraChangeListener = object : AMap.OnCameraChangeListener {
    override fun onCameraChange(position: CameraPosition) {
      // 相机变化中
    }

    override fun onCameraChangeFinish(position: CameraPosition) {
      val event = WritableNativeMap().apply {
        putDouble("latitude", position.target.latitude)
        putDouble("longitude", position.target.longitude)
        putDouble("zoom", position.zoom.toDouble())
      }
      reactContext
        .getJSModule(RCTEventEmitter::class.java)
        .receiveEvent(id, "onRegionChange", event)
    }
  }

  fun destroyMap() {
    this.onDestroy()
  }
}
