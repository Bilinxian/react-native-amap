import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  // 高德地图SDK相关方法
  setApiKey(apiKey: string): void;
  getVersion(): string;
  
  // 坐标转换相关
  coordinateConvert(coordinate: {latitude: number, longitude: number}, type: string): {latitude: number, longitude: number};
  
  // 离线地图相关
  downloadOfflineMap(city: string): Promise<boolean>;
  getOfflineMapState(city: string): number;
}

export default TurboModuleRegistry.getEnforcing<Spec>('AMap3DModule');