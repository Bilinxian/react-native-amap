#include <react/renderer/componentregistry/ComponentDescriptorProviderRegistry.h>
#include <react/renderer/components/amap3d/ComponentDescriptors.h>

namespace facebook {
namespace react {

void registerAMap3DComponents(
    std::shared_ptr<ComponentDescriptorProviderRegistry const> registry) {

    registry->add(concreteComponentDescriptorProvider<AMapViewComponentDescriptor>());
    registry->add(concreteComponentDescriptorProvider<AMapMarkerComponentDescriptor>());
}

} // namespace react
} // namespace facebook
