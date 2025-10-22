package com.amaps

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.amap.api.maps.MapsInitializer

class AmapMapsModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName() = "AmapMapsModule"

  @ReactMethod
  fun initSDK(appKey: String, promise: Promise) {
    try {
      // 隐私政策合规
      MapsInitializer.updatePrivacyShow(reactApplicationContext, true, true)
      MapsInitializer.updatePrivacyAgree(reactApplicationContext, true)

      // 设置 API Key
      MapsInitializer.setApiKey(appKey)

      // 初始化 SDK
      MapsInitializer.initialize(reactApplicationContext)

      promise.resolve("AMap SDK initialized successfully")
    } catch (e: Exception) {
      promise.reject("INIT_ERROR", "Failed to initialize AMap SDK: ${e.message}")
    }
  }

  @ReactMethod
  fun setLanguage(language: String) {
    try {
      when (language) {
        "en" -> MapsInitializer.setLanguage(MapsInitializer.ENGLISH)
        "zh" -> MapsInitializer.setLanguage(MapsInitializer.CHINESE)
        else -> MapsInitializer.setLanguage(MapsInitializer.CHINESE)
      }
    } catch (e: Exception) {
      // 忽略语言设置错误
    }
  }
}
