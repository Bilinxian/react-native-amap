package qiuxiang.amap3d.map_view

import android.view.View
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import qiuxiang.amap3d.map_view.fabric.FabricMapView

class FabricMapViewManager : ViewGroupManager<FabricMapView>() {

  override fun getName(): String {
    return "AMapView"
  }

  override fun createViewInstance(reactContext: ThemedReactContext): FabricMapView {
    return FabricMapView(reactContext)
  }

  @ReactProp(name = "mapType")
  fun setMapType(view: FabricMapView, mapType: Int) {
    view.setMapType(mapType)
  }

  @ReactProp(name = "initialCamera")
  fun setInitialCamera(view: FabricMapView, camera: ReadableMap?) {
    camera?.let { view.setInitialCamera(it) }
  }

  @ReactProp(name = "showsTraffic")
  fun setShowsTraffic(view: FabricMapView, shows: Boolean) {
    view.setShowsTraffic(shows)
  }

  @ReactProp(name = "showsIndoorMap")
  fun setShowsIndoorMap(view: FabricMapView, shows: Boolean) {
    view.setShowsIndoorMap(shows)
  }

  @ReactProp(name = "showsIndoorSwitch")
  fun setShowsIndoorSwitch(view: FabricMapView, shows: Boolean) {
    view.setShowsIndoorSwitch(shows)
  }

  @ReactProp(name = "showsCompass")
  fun setShowsCompass(view: FabricMapView, shows: Boolean) {
    view.setShowsCompass(shows)
  }

  @ReactProp(name = "showsScale")
  fun setShowsScale(view: FabricMapView, shows: Boolean) {
    view.setShowsScale(shows)
  }

  @ReactProp(name = "showsBuildings")
  fun setShowsBuildings(view: FabricMapView, shows: Boolean) {
    view.setShowsBuildings(shows)
  }

  // 添加更多属性方法...
}
