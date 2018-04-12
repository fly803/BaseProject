README
===========================
# BaseProject
Android项目基础库，包含四大部分：一、分辨率适配 二、网络请求框架Retrofit2封装 
三、Android基类封装和项目常用Utils方法 四、基于RxJava、RxAndroid的事件总线RxBus。
1.Android分辨率适配方案，解决大家的分辨率适配烦恼，可以直接依据设计图写尺寸，不做额外的操作，简单方便准确。
2.封装Retrofit2，统一了异常处理，对网络错误，网络错误，连接失败，证书验证失败进行了统一封装，无需用户
在单独处理，框架已经做了统一处理，只需在处理正确返回部分。对服务器定义的Api错误，也做了处理，未来完善可以让用户
自定义服务器返回错误，和自定义服务器返回数据类型，现在还需要遵循统一格式。
3.封装android开发中常用的Utils,也许你的项目只需要这一个库就完全够了。不信你看，有图有真相。
高仿iOS进度条和对话框、activity基类的封装(可继承自BaseActivity自行拓展)、常用自定义View(圆角头像等)、Glide一行代码加载图片、可直接依赖使用。
4.在Rxjava、RxAndroid基础上进行了封装，并对Android进行了优化的事件总线RxBus。事件总线就是一条通信用的通道，
上面跑着各种信息，Android中的各个组件或是控件都可以向它发送各种信息，在各个组件或控件中只要订阅这条总线，每
当总线收到消息的时候，这些订阅者同样就能收到这些消息。减少广播等高占用资源控件的使用。
喜欢的话不妨star一下吧。

[![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-陈刚-orange.svg)](https://github.com/fly803/BaseProject) 
[![](https://jitpack.io/v/fly803/BaseProject.svg)](https://jitpack.io/#fly803/BaseProject)
[![](https://img.shields.io/badge/QQ-356576318-brightgreen.svg)]()

****
### Author陈刚 sam QQ：356576318 QQ群：688700847
### E-mail:356576318@qq.com
****
```diff

```
## 目录
* [屏幕分辨率适配](#屏幕分辨率适配)
* [Retrofit2封装](#Retrofit2封装)
* [RxBus集成](#RxBus集成)
* [常用Utils封装](#常用Utils封装])
* [集成该库](#集成该库)


## 一、屏幕分辨率适配
### 分辨率适配概述
#### 基本原理
App在运行的时候，会在Res下读取对应的dimens文件，BaseProject已经在Res里面对主流的手机的常用分辨率建立的所有的对应关系，
无需用户额外操作，就可以完美适配主流手机。亲测可以适配市面主流手机的95%。用户如果想适配其他分辨率，也提供了相应的获取方法。
介于低于1280x720的低端手机已经很少，所有960x640,480x320等低分辨率手机没有提供默认支持，如需支持，请手动添加。默认支持的分辨率：
| 1184 x 720   |
| 1280 x 720   |
| 1776 x 1080  |
| 1794 x 1080  |
| 1812 x 1080  |
| 1920 x 1080  |
| 2280 x 1080  |
| 2076 x 1080  |
| 2392 x 1440  |
| 2560 x 1440  |
| 2712 x 1440  |
| 2792 x 1440  |
| 2960 x 1440  |
### 使用
### 有2种使用方法 

### 方法1：
集成BaseProject，就对默认分辨率都进行了支持。其他分辨率通过doc/工具集/分配率适配dimens工具.jar手动添加
https://github.com/fly803/BaseProject/blob/master/doc/%E5%B7%A5%E5%85%B7%E9%9B%86/%E5%88%86%E9%85%8D%E7%8E%87%E9%80%82%E9%85%8Ddimens%E5%B7%A5%E5%85%B7.jar
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionTools.png) 
加需要额外支持的dimens拷贝到自己的工程res下,注意输入的名字是values+xxxdpi+分辨率的形式，如values-xhdpi-960x640
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/dimens.png) 
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/project_res.png) 
### 方法2：
分配率适配dimens工具生成dimens，将所有的dimens和相应的文件夹拷贝到自己工程的res文件夹

#####工程中使用方法：
所有效果图宽度标注比如10px，那对应的标注应该是px10

## 二、网络请求框架Retrofit2封装 
###  Retrofit+RxJava 优雅的处理服务器返回异常、错误 
异常&错误

实际开发经常有这种情况，比如登录请求，接口返回的
信息包括请求返回的状态：失败还是成功，错误码，User对象等等。如果网络等原因引起的登录失败可以归结为异常，如果是用户信息输入错误导致的登录失败算是错误。

## 三、Android基类封装和常用Utils方法
### 1.Android常用工具栏
 工具 | 描述
 ---  | ---
 [ActivityUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ActivityUtils.java)| Activity管理工具类
 [AppUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/AppUtils.java)| Adb工具类
 [AndroidSystemUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/AndroidSystemUtils.java)|Android系统工具类
 [AnimationUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/AnimationUtils.java)| 动画工具类
 [AppUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/AppUtils.java)| App工具类
  [AssetsUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/AssetsUtils.java)| 获取Assets资源
  [BitmapUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/BitmapUtils.java)| BitmapUtils工具类 
  [CameraUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/CameraUtils.java)| 调用相机工具类
  [ConvertUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ConvertUtils.java)| 转换工具类
  [DateUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/DateUtils.java)| Date工具类
  [EasyPermissionUtil](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/EasyPermissionUtil.java)| 权限操作工具类
  [FileUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/FileUtils.java)|文件帮助类
  [IDCardUtil](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/IDCardUtil.java)| 身份证工具类
  [DeviceStatusUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/DeviceStatusUtils.java)| 手机状态工具类 主要包括网络、蓝牙、屏幕亮度、飞行模式、音量等
  [JsonUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/JsonUtils.java)| JsonUtils
  [KeyBoardUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/KeyBoardUtils.java)| 键盘操作
  [NetworkUtilsNetworkUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/NetworkUtils.java)| 判断网络连接工具类
  [NioFileUtiles](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/NioFileUtiles.java)| NioFileUtiles
  [NullUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/NullUtils.java)| NullUtils
  [PicassoUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/PicassoUtils.java)| PicassoUtils
  [RadixCoversion](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/RadixCoversion.java)| 进制转换
  [ResolutionAdaptationUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ResolutionAdaptationUtils.java)| 分辨率适配工具类
  [ResourceUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ResourceUtils.java)| 资源操作工具类
  [SdCardUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/SdCardUtils.java)| SdCardUtils工具类
  [SettingUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/SettingUtils.java)| SettingUtils
  [SharedPreferencesUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/SharedPreferencesUtils.java)| SharedPreferencesUtils
  [SpannableStringUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/SpannableStringUtils.java)| 设置文字工具类
  [StringUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/StringUtils.java)| StringUtils
 [TintUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/TintUtils.java)|  Drawable 着色工具类
 [ToastUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ToastUtils.java)| ToastUtils
 [URLUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/URLUtils.java)| URLUtils
 [ValidateUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ValidateUtils.java)| 验证工具类
 [ZipUtils](https://github.com/fly803/BaseProject/tree/master/baseproject/src/main/java/com/cg/baseproject/utils/ZipUtils.java)| ZipUtils



## 基类封装
--------------------
### 使用请参考demo，请继承自BaseActivity自行拓展
```Java
public abstract class BaseActivity extends AppCompatActivity {

    protected BaseActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTranslucentStatus()!=0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(true);
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintResource(isTranslucentStatus());//通知栏所需颜色
            }
        }

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        mContext = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        initView(savedInstanceState);
        initData();
    }

    /**
     * 是否需要沉浸式状态栏 不需要时返回0 需要时返回颜色
     *
     * @return StatusBarTintModle(boolean isTranslucentStatus, int color);
     */
    protected abstract @ColorRes int isTranslucentStatus();


    /**
     * 设置布局ID
     *
     * @return 资源文件ID
     */
    protected abstract
    @LayoutRes
    int getLayoutId();

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据源
     */
    protected abstract void initData();

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //Toast显示
    protected void showToast(String string) {
        DialogUIUtils.showToast(string);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 界面跳转
     *
     * @param cls 目标Activity
     */
    protected void readyGo(Class<?> cls) {
        readyGo(cls, null);
    }

    /**
     * 跳转界面，传参
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转界面并关闭当前界面
     *
     * @param cls 目标Activity
     */
    protected void readyGoThenKill(Class<?> cls) {
        readyGoThenKill(cls, null);
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> cls, Bundle bundle) {
        readyGo(cls, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
```

## 四、事件总线RxBus
### 使用方法
我们推荐获取一个RxBus的单例：

Bus bus = BusProvider.getInstance();

### 订阅Subscribing

为了订阅事件，声明和注解使用 @Subscribe。这方法应该是public和使用only一个single参数
@Subscribe
public void onEvent(SomeEvent event) {
    // TODO: Do something
}

你应该也创建订阅像下面这样：
CustomSubscriber<SomeEvent> customSubscriber = bus.obtainSubscriber(SomeEvent.class,
    new Consumer<SomeEvent>() {
        @Override
        public void accept(SomeEvent someEvent) throws Exception {
            // TODO: Do something
        }
    })
    .withFilter(new Predicate<SomeEvent>() {
        @Override
        public boolean test(SomeEvent someEvent) throws Exception {
            return "Specific message".equals(someEvent.message);
        }
    })
    .withScheduler(Schedulers.trampoline());

### 注册和注销你的观察者，为了收到事件，一个类实例应该注册使用RxBus
bus.register(this);

这定制的订阅器也需要注册使用RxBus
bus.registerSubscriber(this, customSubscriber);

请也记得注销RxBus当适当的时候
bus.unregister(this);

### 发布事件

为了发布一个事件，调用post方法
bus.post(new SomeEvent("Message"));


## 集成该库
-----------

#### 1.通过jitpack.io仓库方式依赖
##### 项目根目录下的build.gradle 中加入
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        //greenDao
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.1'//加入这一行代码
    }
}

allprojects {
    repositories {
        maven { url "https://jitpack.io" }//加入这一行代码
    }
}

##### 在APP的build.gradle dependencies节点下加入

implementation 'com.github.fly803:BaseProject:vXXX'

#### 2.clone项目到本地，将BaseProject库直接依赖到项目。

compile project(':BaseProject')

#### 3.aar方式依赖
##### 在项目根目录下新建aars文件夹，将BaseProject目录build/outputs下的kutils-release.aar文件copy进aars文件夹  在app的build.gradle 最外层节点加入

repositories { flatDir { dirs '../aars' } }

#####   在dependencies节点下加入对aar的依赖

compile(name: 'BaseProject-release', ext: 'aar')


****
集成过程出现问题可联系本人QQ：356576318(注明来自github)
****

## License

    Copyright 2015 bingoogolapple

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
[点我回到顶部](#readme)