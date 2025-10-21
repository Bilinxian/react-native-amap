#pragma once

#include <react/renderer/components/view/ViewProps.h>
#include <react/renderer/core/PropsParserContext.h>
#include <react/renderer/graphics/Color.h>
#include <react/renderer/imagemanager/primitives.h>

namespace facebook {
namespace react {

// AMapView 组件的 Props
class AMapViewProps : public ViewProps {
 public:
  AMapViewProps() = default;
  AMapViewProps(
      const PropsParserContext &context,
      const AMapViewProps &sourceProps,
      const RawProps &rawProps);

#pragma mark - Props

  // 地图类型
  int mapType{1};

  // 初始相机位置
  folly::dynamic initialCameraPosition{};

  // 是否显示交通
  bool showsTraffic{false};

  // 是否显示室内地图
  bool showsIndoorMap{false};

  // 是否显示室内地图切换控件
  bool showsIndoorSwitch{false};

  // 是否显示指南针
  bool showsCompass{true};

  // 是否显示比例尺
  bool showsScale{true};

  // 是否显示建筑物
  bool showsBuildings{true};

  // 是否显示我的位置
  bool myLocationEnabled{false};

  // 是否显示我的位置按钮
  bool myLocationButtonEnabled{true};

  // 语言设置
  std::string language{"zh_cn"};

  // 最大缩放级别
  float maxZoom{20.0f};

  // 最小缩放级别
  float minZoom{3.0f};

  // 手势控制
  bool zoomGesturesEnabled{true};
  bool scrollGesturesEnabled{true};
  bool rotateGesturesEnabled{true};
  bool tiltGesturesEnabled{true};
};

// AMapMarker 组件的 Props
class AMapMarkerProps : public ViewProps {
 public:
  AMapMarkerProps() = default;
  AMapMarkerProps(
      const PropsParserContext &context,
      const AMapMarkerProps &sourceProps,
      const RawProps &rawProps);

#pragma mark - Props

  // 坐标位置
  folly::dynamic coordinate{};

  // 标题
  std::string title{};

  // 描述
  std::string description{};

  // 图标
  ImageSource icon{};

  // 锚点
  folly::dynamic anchor{{"x", 0.5}, {"y", 1.0}};

  // 是否平贴地图
  bool flat{false};

  // 透明度
  float opacity{1.0f};

  // 是否可拖拽
  bool draggable{false};

  // Z轴索引
  float zIndex{0.0f};

  // 旋转角度
  float rotation{0.0f};

  // 是否可见
  bool visible{true};

  // 是否启用
  bool enabled{true};
};


} // namespace react
} // namespace facebook
