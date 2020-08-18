### 百度地图集成流程

1. 收集签名SHA1值、包名信息并录入平台，然后获取开发秘钥

2. 下载地图开发包，拷贝至工程app/libs目录，并添加依赖到jar包

3. 配置AndroidManifest.xml

   配置秘钥，配置相关权限，配置声明定位的service组件

4. 初始化SDK

5. 显示地图，创建MapView或者MapFragment/SupportMapFragment

6. 显示定位，LocationClient.registerLocationListener()

7. zoom级别

8. 更新Map到目标位置，BaiduMap.setMapStatus()



注意事项：

1. HTTP/HTTPS，Android P或更高版本禁止使用未加密的连接

2. 在国内获得的坐标系类型可以是：

   国测局坐标、百度墨卡托坐标 和 百度经纬度坐标。

   在海外地区，只能获得WGS84坐标。

3. 定位SDK默认输出GCJ02坐标，地图SDK默认输出BD09ll坐标。

   注意是否需要调用百度计算根据SDK进行坐标转换。



### 高德地图集成流程

1. 收集签名SHA1值、包名信息并录入平台，然后获取开发秘钥

2. 下载地图开发包或者通过Gradle集成

3. 配置AndroidManifest.xml

   配置秘钥，配置相关权限，配置声明定位的service组件

4. Ndk设置，设置支持的SO库架构

5. 显示地图，创建MapView或者MapFragment/SupportMapFragment

6. 显示定位，AMapLocationClient.setLocationListener()

7. zoom级别，CameraUpdate.zoomTo(int)

8. 更新Map到目标位置，AMap.moveCamera()



注意事项：

1. 在项目中使用地图的时候需要注意，需要合理的管理地图生命周期，这非常的重要。

2. UnsatisfiedLinkError，高版本模拟器（Android 8.0）运行地图出现黑屏，缩放按钮及LOGO正常显示。

   解决方法：使用Android 6.0版本模拟器
   
3. 高德地图默认使用国测局坐标系GCJ-02



### 腾讯地图集成流程

1. 录入应用包名信息获取Key，并在AndroidManifest中配置Key
2. 通过Gradle集成SDK
3. 显示地图，创建地图提供等SupportMapFragment/MapView.
4. 显示定位
5. 更新Map到目标位置，TencentMap.moveCamera()

注意事项：

1. 地图SDK不包含定位功能，如果用户想使用定位功能，可以使用单独的腾讯定位SDK.

2. 地图SDK使用国测局坐标系GCJ02。

   腾讯SDK中没有提供坐标转换的方法。


### 坐标系知识

1. WGS84：一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系。
2. GCJ02：由中国国家测绘局制订的地理信息系统的坐标系统，是由WGS84坐标系经过加密后的坐标系。
3. BD09：百度坐标系，在GCJ02坐标系基础上再次加密。其中BD09LL表示百度经纬度坐标，BD09MC表示百度墨卡托米制坐标。

### TODO：谷歌地图集成流程

### TODO：各平台Android版本适配



Note: 有空闲就持续更新中....





