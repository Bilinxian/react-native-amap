# Custom Android toolchain configuration to fix linker issues
set(CMAKE_ANDROID_NDK "${ANDROID_NDK}")
set(CMAKE_SYSTEM_NAME Android)
set(CMAKE_SYSTEM_VERSION 24)
set(CMAKE_ANDROID_ARCH_ABI "${ANDROID_ABI}")
set(CMAKE_ANDROID_STL_TYPE c++_shared)

# Fix for -fuse-ld=gold error in newer NDK versions
if(CMAKE_HOST_SYSTEM_NAME MATCHES "Darwin")
    set(CMAKE_LINKER "${ANDROID_NDK}/toolchains/llvm/prebuilt/darwin-x86_64/bin/ld")
elseif(CMAKE_HOST_SYSTEM_NAME MATCHES "Linux")
    set(CMAKE_LINKER "${ANDROID_NDK}/toolchains/llvm/prebuilt/linux-x86_64/bin/ld")
elseif(CMAKE_HOST_SYSTEM_NAME MATCHES "Windows")
    set(CMAKE_LINKER "${ANDROID_NDK}/toolchains/llvm/prebuilt/windows-x86_64/bin/ld")
endif()

# Disable problematic linker flags
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,--build-id=sha1")
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,--build-id=sha1")

# Remove problematic flags
string(REPLACE "-fuse-ld=gold" "" CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS}")
string(REPLACE "-fuse-ld=gold" "" CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS}")