import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  initSDK(appKey: string): Promise<void>;

  setLanguage(language: string): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('AmapMapsModule');
