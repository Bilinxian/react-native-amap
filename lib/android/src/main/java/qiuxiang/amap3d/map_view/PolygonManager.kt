package qiuxiang.amap3d.map_view

import com.amap.api.maps.model.LatLng
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import qiuxiang.amap3d.getEventTypeConstants
import qiuxiang.amap3d.toLatLng

/**
 * 多边形管理器
 * 支持新架构（Fabric）和旧架构
 */
internal class PolygonManager : ViewGroupManager<Polygon>() {
  override fun getName(): String {
    return "AMapPolygon"
  }

  override fun createViewInstance(reactContext: ThemedReactContext): Polygon {
    return Polygon(reactContext)
  }

  override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any> {
    return getEventTypeConstants("onPress")
  }

  @ReactProp(name = "coordinates")
  fun setCoordinates(view: Polygon, coordinates: ReadableArray) {
    val points = mutableListOf<LatLng>()
    for (i in 0 until coordinates.size()) {
      points.add(coordinates.getMap(i).toLatLng())
    }
    view.setPoints(points)
  }

  @ReactProp(name = "strokeWidth")
  fun setStrokeWidth(view: Polygon, strokeWidth: Float) {
    view.setStrokeWidth(strokeWidth)
  }

  @ReactProp(name = "strokeColor")
  fun setStrokeColor(view: Polygon, strokeColor: Int) {
    view.setStrokeColor(strokeColor)
  }

  @ReactProp(name = "fillColor")
  fun setFillColor(view: Polygon, fillColor: Int) {
    view.setFillColor(fillColor)
  }

  @ReactProp(name = "zIndex")
  fun setZIndex(view: Polygon, zIndex: Float) {
    view.setZIndex(zIndex)
  }
}
