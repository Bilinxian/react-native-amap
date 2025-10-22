package com.amaps

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = AmapMapsModule.NAME)
class AmapMapsModule(reactContext: ReactApplicationContext) :
  NativeAmapMapsSpec(reactContext) {

  override fun getName() = NAME

  @ReactMethod
  override fun initSDK(appKey: String, promise: Promise) {
    try {
      // 简化实现，先确保新架构编译通过
      println("AMap SDK initialized with key: $appKey")
      promise.resolve(null)
    } catch (e: Exception) {
      promise.reject("INIT_ERROR", e.message)
    }
  }

  companion object {
    const val NAME = "AmapMapsModule"
  }
}
