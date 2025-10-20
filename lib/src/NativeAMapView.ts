import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Point {
  latitude: number;
  longitude: number;
}

export interface CameraPosition {
  target: Point;
  zoomLevel: number;
  tilt: number;
  bearing: number;
}

export interface MapViewProps {
  mapType: number;
  initialCameraPosition?: CameraPosition;
  cameraPosition?: CameraPosition;
  myLocationEnabled: boolean;
  myLocationButtonEnabled: boolean;
  indoorViewEnabled: boolean;
  buildingsEnabled: boolean;
  compassEnabled: boolean;
  zoomControlsEnabled: boolean;
  scaleControlsEnabled: boolean;
  trafficEnabled: boolean;
  maxZoom: number;
  minZoom: number;
  zoomGesturesEnabled: boolean;
  scrollGesturesEnabled: boolean;
  rotateGesturesEnabled: boolean;
  tiltGesturesEnabled: boolean;
  language: string;
  onLoad?: () => void;
  onPress?: (event: any) => void;
  onPressPoi?: (event: any) => void;
  onLongPress?: (event: any) => void;
  onCameraMove?: (event: any) => void;
  onCameraIdle?: (event: any) => void;
  onLocation?: (event: any) => void;
  onCallback?: (event: any) => void;
}

export default TurboModuleRegistry.getEnforcing<NativeAMapView>('AMapView');
