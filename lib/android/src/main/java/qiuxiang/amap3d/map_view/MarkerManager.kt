package qiuxiang.amap3d.map_view

import android.view.View
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import qiuxiang.amap3d.toLatLng

@Suppress("unused")
internal class MarkerManager : ViewGroupManager<Marker>() {

  companion object {
    private const val REACT_CLASS = "AMapMarker"

    // 命令常量
    private const val COMMAND_UPDATE = 1
    private const val COMMAND_UPDATE_ICON = 2
  }

  override fun getName(): String {
    return REACT_CLASS
  }

  override fun createViewInstance(reactContext: ThemedReactContext): Marker {
    return Marker(reactContext)
  }

  override fun addView(parent: Marker, child: View, index: Int) {
    parent.addView(child, index)
  }

  override fun removeViewAt(parent: Marker, index: Int) {
    parent.removeViewAt(index)
  }

  override fun getChildCount(parent: Marker): Int {
    return parent.childCount
  }

  override fun getChildAt(parent: Marker, index: Int): View {
    return parent.getChildAt(index)
  }

  // Fabric 兼容：使用直接事件类型常量
  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return mutableMapOf(
      "onPress" to mutableMapOf("registrationName" to "onPress"),
      "onDrag" to mutableMapOf("registrationName" to "onDrag"),
      "onDragStart" to mutableMapOf("registrationName" to "onDragStart"),
      "onDragEnd" to mutableMapOf("registrationName" to "onDragEnd")
    )
  }

  override fun getCommandsMap(): MutableMap<String, Int> {
    return mutableMapOf(
      "update" to COMMAND_UPDATE,
      "updateIcon" to COMMAND_UPDATE_ICON
    )
  }

  override fun receiveCommand(root: Marker, commandId: Int, args: ReadableArray?) {
    when (commandId) {
      COMMAND_UPDATE -> root.updateIcon()
      COMMAND_UPDATE_ICON -> root.updateIconImmediately()
    }
  }

  override fun receiveCommand(root: Marker, commandId: String, args: ReadableArray?) {
    when (commandId) {
      "update" -> root.updateIcon()
      "updateIcon" -> root.updateIconImmediately()
    }
  }

  // React Props
  @ReactProp(name = "coordinate")
  fun setCoordinate(marker: Marker, coordinate: ReadableMap) {
    marker.updatePosition(coordinate.toLatLng())
  }

  @ReactProp(name = "latLng")
  fun setLatLng(marker: Marker, position: ReadableMap) {
    marker.updatePosition(position.toLatLng())
  }

  @ReactProp(name = "flat")
  fun setFlat(marker: Marker, flat: Boolean) {
    marker.flat = flat
  }

  @ReactProp(name = "opacity")
  override fun setOpacity(marker: Marker, opacity: Float) {
    marker.opacity = opacity
  }

  @ReactProp(name = "draggable")
  fun setDraggable(marker: Marker, draggable: Boolean) {
    marker.draggable = draggable
  }

  @ReactProp(name = "zIndex")
  fun setZIndex(marker: Marker, zIndex: Float) {
    marker.zIndex = zIndex
  }

  @ReactProp(name = "rotation")
  fun setRotation(marker: Marker, rotation: Float) {
    marker.rotation = rotation
  }

  @ReactProp(name = "anchor")
  fun setAnchor(marker: Marker, anchor: ReadableMap) {
    val x = anchor.getDouble("x")
    val y = anchor.getDouble("y")
    marker.setAnchor(x, y)
  }

  @ReactProp(name = "icon")
  fun setIcon(marker: Marker, icon: ReadableMap?) {
    icon?.let { marker.setIcon(it) }
  }

  @ReactProp(name = "title")
  fun setTitle(marker: Marker, title: String?) {
    marker.title = title
  }

  @ReactProp(name = "snippet")
  fun setSnippet(marker: Marker, snippet: String?) {
    marker.snippet = snippet
  }

  // 新架构兼容：支持更多属性
  @ReactProp(name = "enabled")
  fun setEnabled(marker: Marker, enabled: Boolean) {
    marker.isEnabled = enabled
  }

  @ReactProp(name = "visible")
  fun setVisible(marker: Marker, visible: Boolean) {
    marker.visibility = if (visible) View.VISIBLE else View.GONE
  }
}
