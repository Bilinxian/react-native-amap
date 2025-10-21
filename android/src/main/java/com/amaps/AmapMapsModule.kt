package com.amaps

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule
import com.amap.api.maps.MapsInitializer

@ReactModule(name = AmapMapsModule.NAME)
class AmapMapsModule(reactContext: ReactApplicationContext) :
  NativeAmapMapsSpec(reactContext) {

  override fun getName() = NAME

  @ReactMethod
  override fun initSDK(appKey: String, promise: Promise) {
    try {
      MapsInitializer.updatePrivacyShow(reactApplicationContext, true, true)
      MapsInitializer.updatePrivacyAgree(reactApplicationContext, true)
      MapsInitializer.setApiKey(appKey)
      promise.resolve(null)
    } catch (e: Exception) {
      promise.reject("INIT_ERROR", e.message)
    }
  }

  @ReactMethod
  override fun setLanguage(language: String) {
    // 语言设置实现
  }

  companion object {
    const val NAME = "AmapMapsModule"
  }
}
