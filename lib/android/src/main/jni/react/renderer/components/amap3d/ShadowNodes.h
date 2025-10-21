#pragma once

#include <react/renderer/components/amap3d/Props.h>
#include <react/renderer/components/view/ConcreteViewShadowNode.h>
#include <react/renderer/imagemanager/ImageManager.h>
#include <react/renderer/imagemanager/primitives.h>

namespace facebook {
namespace react {

extern const char AMapViewComponentName[];
extern const char AMapMarkerComponentName[];

// AMapView ShadowNode
class AMapViewShadowNode final : public ConcreteViewShadowNode<
                                     AMapViewComponentName,
                                     AMapViewProps> {
 public:
  using ConcreteViewShadowNode::ConcreteViewShadowNode;

  // 状态管理
  void updateStateIfNeeded();
};

// AMapMarker ShadowNode
class AMapMarkerShadowNode final : public ConcreteViewShadowNode<
                                       AMapMarkerComponentName,
                                       AMapMarkerProps> {
 public:
  using ConcreteViewShadowNode::ConcreteViewShadowNode;

  // 图片管理
  void setImageManager(const SharedImageManager &imageManager);

#pragma mark - LayoutableShadowNode

  void layout(LayoutContext layoutContext) override;

 private:
  SharedImageManager imageManager_;
};


} // namespace react
} // namespace facebook
