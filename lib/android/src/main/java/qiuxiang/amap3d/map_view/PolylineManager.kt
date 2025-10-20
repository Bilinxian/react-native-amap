package qiuxiang.amap3d.map_view

import android.graphics.Color
import com.amap.api.maps.model.LatLng
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import qiuxiang.amap3d.getEventTypeConstants
import qiuxiang.amap3d.toLatLng

/**
 * 折线管理器
 * 支持新架构（Fabric）和旧架构
 */
internal class PolylineManager : ViewGroupManager<Polyline>() {
  override fun getName(): String {
    return "AMapPolyline"
  }

  override fun createViewInstance(reactContext: ThemedReactContext): Polyline {
    return Polyline(reactContext)
  }

  override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any> {
    return getEventTypeConstants("onPress")
  }

  @ReactProp(name = "coordinates")
  fun setCoordinates(view: Polyline, coordinates: ReadableArray) {
    val points = mutableListOf<LatLng>()
    for (i in 0 until coordinates.size()) {
      points.add(coordinates.getMap(i).toLatLng())
    }
    view.setPoints(points)
  }

  @ReactProp(name = "strokeWidth")
  fun setStrokeWidth(view: Polyline, strokeWidth: Float) {
    view.setWidth(strokeWidth)
  }

  @ReactProp(name = "strokeColor")
  fun setStrokeColor(view: Polyline, strokeColor: Int) {
    view.setColor(strokeColor)
  }

  @ReactProp(name = "colors")
  fun setColors(view: Polyline, colors: ReadableArray) {
    val colorList = mutableListOf<Int>()
    for (i in 0 until colors.size()) {
      colorList.add(colors.getInt(i))
    }
    view.setColors(colorList)
  }

  @ReactProp(name = "dashed")
  fun setDashed(view: Polyline, dashed: Boolean) {
    view.setDashed(dashed)
  }

  @ReactProp(name = "gradient")
  fun setGradient(view: Polyline, gradient: Boolean) {
    view.setGradient(gradient)
  }

  @ReactProp(name = "geodesic")
  fun setGeodesic(view: Polyline, geodesic: Boolean) {
    view.setGeodesic(geodesic)
  }

  @ReactProp(name = "zIndex")
  fun setZIndex(view: Polyline, zIndex: Float) {
    view.setZIndex(zIndex)
  }
}
