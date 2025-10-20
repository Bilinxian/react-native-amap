# react-native-amap3d [![][version-badge]][npm] [![](https://github.com/qiuxiang/react-native-amap3d/actions/workflows/build.yml/badge.svg)](https://github.com/qiuxiang/react-native-amap3d/actions/workflows/build.yml)

**🎉 现已支持 React Native 0.80.2 新架构（Fabric/TurboModule）！**

**注意：该项目目前只维护，不加新功能。**

react-native 高德地图组件，使用最新 3D SDK，支持 Android + iOS，受 [react-native-maps](https://github.com/airbnb/react-native-maps) 启发，提供功能丰富且易用的接口。

相关项目推荐：

- [react-native-baidumap-sdk（百度地图 SDK）](https://github.com/qiuxiang/react-native-baidumap-sdk)
- [react-native-amap-geolocation（高德地图定位模块）](https://github.com/qiuxiang/react-native-amap-geolocation)

## 功能

- 地图模式切换（常规、卫星、导航、夜间）
- 3D 建筑、路况、室内地图
- 内置地图控件的显示隐藏（指南针、比例尺、定位按钮、缩放按钮）
- 手势交互控制（平移、缩放、旋转、倾斜）
- 中心坐标、缩放级别、倾斜度的设置，支持动画过渡
- 地图事件（onPress、onLongPress、onLocation、onCameraMove、onCameraIdle 等）
- 地图标记（Marker）
- 折线绘制（Polyline）
- 多边形绘制（Polygon）
- 圆形绘制（Circle）
- 热力图（HeatMap）
- 海量点（MultiPoint）
- 点聚合（Cluster）

## 接口文档

https://qiuxiang.github.io/react-native-amap3d/api/

## 安装

```bash
npm i react-native-amap3d
```

### 新架构支持

本项目现已完全支持 React Native 新架构（Fabric/TurboModule），包括：

- ✅ Fabric 渲染引擎支持
- ✅ TurboModule 支持
- ✅ CodeGen 代码生成
- ✅ 类型安全的 JavaScript 接口

要启用新架构，请确保：

1. 使用 React Native 0.80.2 或更高版本
2. 在 `android/gradle.properties` 中设置 `newArchEnabled=true`
3. 运行 `cd android && ./gradlew generateCodegenArtifactsFromSchema` 生成代码

详细升级指南请参考 [NEW_ARCH_MIGRATION_GUIDE.md](./NEW_ARCH_MIGRATION_GUIDE.md) 和 [QUICK_START.md](./QUICK_START.md)。

### 添加高德 API Key

首先你需要获取高德地图 API Key：

- [Aandroid](http://lbs.amap.com/api/android-sdk/guide/create-project/get-key)
- [iOS](https://lbs.amap.com/api/ios-sdk/guide/create-project/get-key)

然后你需要在显示地图前调用接口设置 API key：

```js
import { AMapSdk } from "react-native-amap3d";
import { Platform } from "react-native";

AMapSdk.init(
  Platform.select({
    android: "c52c7169e6df23490e3114330098aaac",
    ios: "186d3464209b74effa4d8391f441f14d",
  })
);
```

## 用法

### 显示地图

```jsx
import { MapView, MapType } from "react-native-amap3d";

<MapView
  mapType={MapType.Satellite}
  initialCameraPosition={{
    target: {
      latitude: 39.91095,
      longitude: 116.37296,
    },
    zoom: 8,
  }}
/>;
```

<img src=https://user-images.githubusercontent.com/1709072/140698774-bdbfee64-d403-4e49-9a85-716d44783cfd.png height=500> <img src=https://user-images.githubusercontent.com/1709072/140849895-dada3f51-74c0-4685-b5d6-c1b69a4d06bb.PNG height=500>

### 监听地图事件

```jsx
import { MapView } from "react-native-amap3d";

<MapView
  onLoad={() => console.log("onLoad")}
  onPress={({ nativeEvent }) => console.log(nativeEvent)}
  onCameraIdle={({ nativeEvent }) => console.log(nativeEvent)}
/>;
```

<img src=https://user-images.githubusercontent.com/1709072/140705501-9ed3e038-e52a-48c2-a98a-235c5c890549.png height=500> <img src=https://user-images.githubusercontent.com/1709072/140849894-3add3858-fc7f-47cd-9786-94aeef399ebc.PNG height=500>

### 添加标记

其中 `icon` 支持 [ImageSource](https://reactnative.dev/docs/image#imagesource)。

同时支持 `children` 作为标记图标。

```jsx
import { MapView, Marker } from "react-native-amap3d";

<MapView>
  <Marker
    position={{ latitude: 39.806901, longitude: 116.397972 }}
    icon={require("../images/flag.png")}
    onPress={() => alert("onPress")}
  />
  <Marker
    position={{ latitude: 39.806901, longitude: 116.297972 }}
    icon={{
      uri: "https://reactnative.dev/img/pwa/manifest-icon-512.png",
      width: 64,
      height: 64,
    }}
  />
  <Marker position={{ latitude: 39.906901, longitude: 116.397972 }}>
    <Text
      style={{
        color: "#fff",
        backgroundColor: "#009688",
        alignItems: "center",
        borderRadius: 5,
        padding: 5,
      }}
    >
      {new Date().toLocaleString()}
    </Text>
  </Marker>
</MapView>;
```

<img src=https://user-images.githubusercontent.com/1709072/140707579-4abe070a-3fc1-481d-8a2e-91ac2ad8bdc7.png height=500> <img src=https://user-images.githubusercontent.com/1709072/140849886-7eb9322b-8fa8-4049-a7b0-3eb36d006992.PNG height=500>

### 点聚合

Marker 数量过多（尤其是使用自定义 View 的情况下）会导致性能问题，而且显示过于密集，这时候可以用点聚合改善。

```jsx
import { Cluster, MapView, Marker } from "react-native-amap3d";

const markers = Array(1000)
  .fill(0)
  .map((_, i) => ({
    position: { latitude: 39.5 + Math.random(), longitude: 116 + Math.random() },
    properties: { key: `Marker${i}` },
  }));

<MapView
  ref={(ref) => (this.mapView = ref)}
  onLoad={() => this.mapView?.moveCamera({ zoom: 8 }, 100)}
  onCameraIdle={({ nativeEvent }) => {
    this.status = nativeEvent;
    this.cluster?.update(nativeEvent);
  }}
>
  <Cluster
    ref={(ref) => (this.cluster = ref)}
    points={markers}
    renderMarker={(item) => (
      <Marker
        key={item.properties.key}
        icon={require("../images/flag.png")}
        position={item.position}
      />
    )}
  />
</MapView>;
```

<img src=https://user-images.githubusercontent.com/1709072/140710764-40f767cd-74fd-47ca-8310-897bbf58fbbd.png height=500> <img src=https://user-images.githubusercontent.com/1709072/140849888-6b6609c1-2e55-41c2-bdc3-f9d3fcc7a112.PNG height=500>

<img src=https://user-images.githubusercontent.com/1709072/140710758-63e81ade-2635-4412-a5fa-b6948605fe75.png height=500> <img src=https://user-images.githubusercontent.com/1709072/140849880-9eb7609d-55a7-43be-8b6a-bac725fb0a82.PNG height=500>

### 更多示例

参考 [example](https://github.com/qiuxiang/react-native-amap3d/tree/master/example-app)。

## 常见问题

- 尽量使用真实设备进行测试，在模拟器可能存在一些问题（常见的是 Android 模拟器因为缺少 GPU 加速而导致闪退）。
- onLocation 没有返回定位数据通常是因为 key 不正确，或没有申请 PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION 权限

### 新架构相关问题

**Q: 如何确认新架构是否启用成功？**
A: 运行应用时查看 Metro 日志，如果看到 "Fabric" 相关输出，说明新架构已启用。也可以在 Android Studio 的 Logcat 中搜索 "FabricUIManager" 验证。

**Q: 新架构和旧架构可以共存吗？**
A: 是的，本项目同时支持新旧架构。新架构通过 CodeGen 生成代码，旧架构通过传统的桥接方式工作。

**Q: 升级到新架构后遇到编译错误怎么办？**
A: 首先确保清理构建缓存：`cd android && ./gradlew clean`，然后重新生成 CodeGen 代码：`./gradlew generateCodegenArtifactsFromSchema`。

[npm]: https://www.npmjs.com/package/react-native-amap3d
[version-badge]: https://img.shields.io/npm/v/react-native-amap3d.svg
