#include <fbjni/fbjni.h>
#include <react/renderer/componentregistry/ComponentDescriptorProviderRegistry.h>
#include <react/renderer/components/amap3d/ComponentDescriptors.h>

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *) {
    return facebook::jni::initialize(vm, [] {
        // 注册组件
        facebook::react::registerAMap3DComponents(
            std::make_shared<facebook::react::ComponentDescriptorProviderRegistry>());
    });
}
