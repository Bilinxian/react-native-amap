import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface PolylineProps {
  coordinates: Array<{
    latitude: number;
    longitude: number;
  }>;
  strokeWidth?: number;
  strokeColor?: string;
  colors?: string[];
  dashed?: boolean;
  gradient?: boolean;
  geodesic?: boolean;
  zIndex?: number;
  onPress?: (event: any) => void;
}

export default TurboModuleRegistry.getEnforcing<NativeAMapPolyline>('AMapPolyline');
