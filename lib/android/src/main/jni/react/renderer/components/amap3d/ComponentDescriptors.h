#pragma once

#include <react/renderer/core/ConcreteComponentDescriptor.h>
#include <react/renderer/components/amap3d/Props.h>
#include <react/renderer/components/amap3d/ShadowNodes.h>

namespace facebook {
namespace react {

// AMapView 组件描述符
using AMapViewComponentDescriptor = ConcreteComponentDescriptor<AMapViewShadowNode>;

// AMapMarker 组件描述符
using AMapMarkerComponentDescriptor = ConcreteComponentDescriptor<AMapMarkerShadowNode>;

} // namespace react
} // namespace facebook
