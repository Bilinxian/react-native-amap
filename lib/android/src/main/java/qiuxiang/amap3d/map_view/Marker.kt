package qiuxiang.amap3d.map_view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.model.*
import com.amap.api.maps.model.Marker as AMapMarker
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.views.view.ReactViewGroup
import qiuxiang.amap3d.fetchImage

class Marker(context: Context) : ReactViewGroup(context), Overlay {
    private var view: View? = null
    private var icon: BitmapDescriptor? = null
    private var anchorX: Float = 0.5f
    private var anchorY: Float = 1f
    var marker: AMapMarker? = null

    var position: LatLng? = null
        set(value) {
            field = value
            marker?.position = value
        }

    var zIndex: Float = 0.0f
        set(value) {
            field = value
            marker?.zIndex = value
        }

    var flat: Boolean = false
        set(value) {
            field = value
            marker?.isFlat = value
        }

    var opacity: Float = 1f
        set(value) {
            field = value
            marker?.alpha = value
        }

    var draggable: Boolean = false
        set(value) {
            field = value
            marker?.isDraggable = value
        }

    var rotation: Float = 0f
        set(value) {
            field = value
            marker?.rotateAngle = value
        }

    var title: String? = null
        set(value) {
            field = value
            marker?.title = value
        }

    var snippet: String? = null
        set(value) {
            field = value
            marker?.snippet = value
        }

    // Fabric 兼容：标记是否已添加到地图
    private var isAddedToMap: Boolean = false

    fun updateIcon() {
        view?.let { childView ->
            if (childView.width > 0 && childView.height > 0) {
                try {
                    val bitmap = Bitmap.createBitmap(
                        childView.width,
                        childView.height,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    childView.draw(canvas)
                    icon = BitmapDescriptorFactory.fromBitmap(bitmap)
                    marker?.setIcon(icon)
                } catch (e: Exception) {
                    // 处理位图创建异常
                    e.printStackTrace()
                }
            }
        }
    }

    fun setAnchor(x: Double, y: Double) {
        anchorX = x.toFloat()
        anchorY = y.toFloat()
        marker?.setAnchor(anchorX, anchorY)
    }

    override fun addView(child: View, index: Int) {
        super.addView(child, index)
        view = child
        // 使用更安全的布局监听器
        view?.addOnLayoutChangeListener { _, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            val widthChanged = (right - left) != (oldRight - oldLeft)
            val heightChanged = (bottom - top) != (oldBottom - oldTop)
            if (widthChanged || heightChanged) {
                updateIcon()
            }
        }
    }

    override fun removeView(child: View) {
        super.removeView(child)
        if (view == child) {
            view = null
        }
    }

    fun setIcon(source: ReadableMap) {
        fetchImage(source) { bitmapDescriptor ->
            icon = bitmapDescriptor
            Handler(Looper.getMainLooper()).post {
                marker?.setIcon(bitmapDescriptor)
            }
        }
    }

    override fun add(map: AMap) {
        if (isAddedToMap) {
            return // 避免重复添加
        }

        val options = MarkerOptions().apply {
            position?.let { position(it) }
            icon?.let { icon(it) }
            flat(flat)
            alpha(opacity)
            draggable(draggable)
            anchor(anchorX, anchorY)
            zIndex(zIndex)
            infoWindowEnable(false)
            rotation?.let { rotateAngle(it) }
            title?.let { title(it) }
            snippet?.let { snippet(it) }
        }

        marker = map.addMarker(options)
        isAddedToMap = true
    }

    override fun remove() {
        marker?.destroy()
        marker = null
        isAddedToMap = false
    }

    // Fabric 兼容：更新标记位置
    fun updatePosition(latLng: LatLng) {
        position = latLng
    }

    // Fabric 兼容：更新标记图标
    fun updateIconImmediately() {
        updateIcon()
    }

    // Fabric 兼容：清理资源
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // 可选：在这里清理资源
    }
}
