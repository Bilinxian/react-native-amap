# Android.mk
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# 模块名称
LOCAL_MODULE := amap3d

# React Native 新架构 Fabric 组件注册
LOCAL_C_INCLUDES := $(LOCAL_PATH) \
                    $(REACT_ANDROID_DIR) \
                    $(REACT_ANDROID_DIR)/ReactCommon \
                    $(REACT_ANDROID_DIR)/react/renderer/components/view \
                    $(REACT_ANDROID_DIR)/react/renderer/components/root \
                    $(REACT_ANDROID_DIR)/react/renderer/core

# 源文件
LOCAL_SRC_FILES := $(wildcard $(LOCAL_PATH)/*.cpp) \
                   $(wildcard $(LOCAL_PATH)/react/renderer/components/amap3d/*.cpp)

# 共享库
LOCAL_SHARED_LIBRARIES := \
    libfolly_runtime \
    libglog \
    libjsi \
    libreact_codegen_rncore \
    libreact_debug \
    libreact_render_componentregistry \
    libreact_render_core \
    libreact_render_debug \
    libreact_render_graphics \
    libreact_render_imagemanager \
    libreact_render_mapbuffer \
    libreact_render_mounting \
    libreact_render_uimanager \
    libreact_render_telemetry \
    libreact_utils \
    librrc_image \
    librrc_view \
    libyoga

# 静态库
LOCAL_STATIC_LIBRARIES := \
    libreact_render_components_progressbar \
    libreact_render_components_safeareaview \
    libreact_render_components_scrollview \
    libreact_render_components_switch \
    libreact_render_components_text \
    libreact_render_components_textinput \
    libreact_render_components_unimplementedview \
    libreact_render_components_view

# 编译标志
LOCAL_CFLAGS := \
    -DLOG_TAG=\"ReactNative\" \
    -fexceptions \
    -frtti \
    -std=c++17 \
    -Wall

# 链接标志
LOCAL_LDLIBS := -llog -landroid

# 构建共享库
include $(BUILD_SHARED_LIBRARY)

# 包含其他模块
$(call import-module,fb)
$(call import-module,fbjni)
$(call import-module,folly)
$(call import-module,glog)
$(call import-module,jsi)
$(call import-module,react/renderer/components/root)
$(call import-module,react/renderer/components/view)
$(call import-module,yogajni)
