import { NativeModules } from 'react-native';

const { AmapMapsModule } = NativeModules;

export const initSDK = (appKey) => {
  return AmapMapsModule ? AmapMapsModule.initSDK(appKey) : Promise.resolve();
};

export const setLanguage = (language) => {
  if (AmapMapsModule && AmapMapsModule.setLanguage) {
    AmapMapsModule.setLanguage(language);
  }
};

export default {
  initSDK,
  setLanguage,
};
