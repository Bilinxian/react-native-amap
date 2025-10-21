package com.amaps

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.turbomodule.core.interfaces.TurboModule

abstract class NativeAmapMapsSpec(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext), TurboModule {

  abstract fun initSDK(appKey: String, promise: Promise)
  abstract fun setLanguage(language: String)
}
