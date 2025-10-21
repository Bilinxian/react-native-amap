package com.amaps

import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class AmapMapViewManager : SimpleViewManager<AmapMapView>() {
  override fun getName() = "AmapMapView"

  override fun createViewInstance(reactContext: ThemedReactContext): AmapMapView {
    return AmapMapView(reactContext)
  }

  @ReactProp(name = "region")
  fun setRegion(view: AmapMapView, region: ReadableMap?) {
    view.setRegion(region)
  }

  @ReactProp(name = "showsUserLocation", defaultBoolean = false)
  fun setShowsUserLocation(view: AmapMapView, shows: Boolean) {
    view.map?.isMyLocationEnabled = shows
  }

  override fun onDropViewInstance(view: AmapMapView) {
    view.destroyMap()
    super.onDropViewInstance(view)
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return mutableMapOf(
      "onRegionChange" to mutableMapOf("registrationName" to "onRegionChange")
    )
  }
}
