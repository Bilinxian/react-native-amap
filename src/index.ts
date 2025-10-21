// js/src/index.ts
import AmapMap from './AmapMap';
import NativeAmapMaps from './NativeAmapMaps';

// 导出主要组件
export {default as AmapMap} from './AmapMap';
export {default as NativeAmapMaps} from './NativeAmapMaps';

// 默认导出地图组件
export default AmapMap;

// 工具函数
export const initSDK = NativeAmapMaps.initSDK;
export const setLanguage = NativeAmapMaps.setLanguage;

// 版本信息
export const VERSION = {
  major: 0,
  minor: 1,
  patch: 0,
  version: '0.1.0',
};
