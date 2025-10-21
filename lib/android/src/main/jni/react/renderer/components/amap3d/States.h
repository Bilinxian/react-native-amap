#pragma once

#ifdef ANDROID
#include <folly/dynamic.h>
#include <react/renderer/mapbuffer/MapBuffer.h>
#include <react/renderer/mapbuffer/MapBufferBuilder.h>
#endif

#include <react/renderer/imagemanager/ImageRequest.h>
#include <react/renderer/imagemanager/primitives.h>

namespace facebook {
namespace react {

// AMapView 状态
class AMapViewState final {
 public:
  using Shared = std::shared_ptr<const AMapViewState>;

  AMapViewState() = default;

#ifdef ANDROID
  AMapViewState(AMapViewState const &previousState, folly::dynamic data){};
  folly::dynamic getDynamic() const {
    return folly::dynamic::object();
  };
  MapBuffer getMapBuffer() const {
    return MapBufferBuilder::EMPTY();
  };
#endif
};

// AMapMarker 状态
class AMapMarkerState final {
 public:
  using Shared = std::shared_ptr<const AMapMarkerState>;

  AMapMarkerState(
      ImageSource const &imageSource,
      ImageRequest imageRequest,
      Float opacity)
      : imageSource_(imageSource),
        imageRequest_(std::make_shared<ImageRequest>(std::move(imageRequest))),
        opacity_(opacity){};

  AMapMarkerState() = default;

  // 获取图片源
  ImageSource getImageSource() const {
    return imageSource_;
  }

  // 获取图片请求
  std::shared_ptr<ImageRequest> getImageRequest() const {
    return imageRequest_;
  }

  // 获取透明度
  Float getOpacity() const {
    return opacity_;
  }

#ifdef ANDROID
  AMapMarkerState(AMapMarkerState const &previousState, folly::dynamic data){};

  folly::dynamic getDynamic() const {
    auto result = folly::dynamic::object();

    if (imageSource_.type != ImageSource::Type::Invalid) {
      result["imageSource"] = imageSource_.getDynamic();
    }

    result["opacity"] = opacity_;

    return result;
  }

  MapBuffer getMapBuffer() const {
    auto builder = MapBufferBuilder();

    if (imageSource_.type != ImageSource::Type::Invalid) {
      auto imageSourceBuffer = MapBufferBuilder();
      // 构建图片源 MapBuffer
      // 这里需要根据 ImageSource 结构实现
      builder.putMapBuffer(/* key */ 0, imageSourceBuffer.build());
    }

    builder.putDouble(/* key */ 1, opacity_);

    return builder.build();
  }
#endif

 private:
  ImageSource imageSource_;
  std::shared_ptr<ImageRequest> imageRequest_;
  Float opacity_{1.0f};
};


} // namespace react
} // namespace facebook
