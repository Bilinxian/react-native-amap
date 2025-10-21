package qiuxiang.amap3d.modules

import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.MapsInitializer
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.turbomodule.core.interfaces.TurboModule

@Suppress("unused")
@ReactModule(name = SdkModule.NAME)
class SdkModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext), TurboModule {

  companion object {
    const val NAME = "AMapSdk"
  }

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun initSDK(apiKey: String?) {
    apiKey?.let {
      MapsInitializer.setApiKey(it)
      MapsInitializer.updatePrivacyAgree(reactApplicationContext, true)
      MapsInitializer.updatePrivacyShow(reactApplicationContext, true, true)
      AMapLocationClient.updatePrivacyAgree(reactApplicationContext, true)
      AMapLocationClient.updatePrivacyShow(reactApplicationContext, true, true)
    }
  }

  @ReactMethod
  fun getVersion(promise: Promise) {
    promise.resolve(MapsInitializer.getVersion())
  }

  // TurboModule 需要实现 invalidate 方法
  override fun invalidate() {
    Log.d("test","invalidate")
    // 清理资源
  }
}
