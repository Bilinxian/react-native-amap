# Application.mk
APP_ABI := armeabi-v7a arm64-v8a x86 x86_64
APP_PLATFORM := android-21
APP_STL := c++_shared
APP_CPPFLAGS := -frtti -fexceptions -std=c++17

# 构建模式 (debug/release)
# APP_OPTIM := debug

# C++ 运行时
NDK_TOOLCHAIN_VERSION := clang

# 异常处理
APP_CPPFLAGS += -fexceptions

# RTTI 支持
APP_CPPFLAGS += -frtti

# C++ 标准
APP_CPPFLAGS += -std=c++17

# 警告级别
APP_CPPFLAGS += -Wall -Werror

# 优化级别
# APP_CFLAGS += -O2
# APP_CPPFLAGS += -O2

# 调试信息
# APP_CFLAGS += -g
# APP_CPPFLAGS += -g
