module.exports = {
  dependencies: {
    'react-native-amap3d': {
      platforms: {
        android: {
          packageInstance: 'new AmapMapsPackage()'
        },
        ios: null // 暂时不实现 iOS
      }
    }
  }
};
