import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface MarkerProps {
  position: {
    latitude: number;
    longitude: number;
  };
  title?: string;
  snippet?: string;
  draggable?: boolean;
  flat?: boolean;
  opacity?: number;
  rotation?: number;
  anchor?: {
    u: number;
    v: number;
  };
  icon?: any;
  infoWindowEnabled?: boolean;
  active?: boolean;
  onPress?: () => void;
  onDragStart?: (event: any) => void;
  onDrag?: (event: any) => void;
  onDragEnd?: (event: any) => void;
  onInfoWindowPress?: () => void;
}

export default TurboModuleRegistry.getEnforcing<NativeAMapMarker>('AMapMarker');
