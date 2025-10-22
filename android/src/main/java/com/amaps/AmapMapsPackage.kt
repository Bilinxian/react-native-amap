package com.amaps

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import java.util.HashMap

class AmapMapsPackage : TurboReactPackage() {
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
        return when (name) {
            AmapMapsModule.NAME -> AmapMapsModule(reactContext)
            else -> null
        }
    }

    override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
        return ReactModuleInfoProvider {
            val moduleInfo: MutableMap<String, ReactModuleInfo> = HashMap()
            moduleInfo[AmapMapsModule.NAME] = ReactModuleInfo(
                AmapMapsModule.NAME,
                AmapMapsModule::class.java.name,
                false, // canOverrideExistingModule
                false, // needsEagerInit
                true,  // hasConstants
                false, // isCxxModule
                true   // isTurboModule
            )
            moduleInfo
        }
    }
}
