cmake_minimum_required(VERSION 3.13)

project(react-native-amap3d)

# Add library target for react-native-amap3d
add_library(
    react_codegen_react-native-amap3d
    STATIC
)

# Set linker language to CXX
set_target_properties(
    react_codegen_react-native-amap3d
    PROPERTIES
    LINKER_LANGUAGE CXX
)

# Add include directories if needed
# target_include_directories(react_codegen_react-native-amap3d PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})