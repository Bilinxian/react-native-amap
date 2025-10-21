package qiuxiang.amap3d.modules;

import androidx.annotation.NonNull;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;

@DoNotStrip
public interface AMapSdkSpec extends com.facebook.react.turbomodule.core.interfaces.TurboModule {
  @DoNotStrip
  void initSDK(String apiKey);

  @DoNotStrip
  void getVersion(Promise promise);
}
