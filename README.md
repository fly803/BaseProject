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


## 三、Android基类封装和常用Utils方法
### 1.Android常用工具栏
 工具 | 描述
 ---  | ---
 [AnimationUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/AnimationUtils.java)| 动画工具类
 [AppUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/AppUtils.java)| APP相关信息工具类
 [AssetDatabaseOpenHelper](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/AssetDatabaseOpenHelper.java)| 目录资源获取
 [Base64](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/Base64.java)| 加密
 [BitmapUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/BitmapUtil.java)| 获取Bitmap和对Bitmap的操作
  [ChannelUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ChannelUtil.java)| 获取市场号
  [Colors](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/Colors.java)| 颜色工具类 包括常用的色值
  [DES](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DES.java)| DES加密解密类
  [DataCleanManager](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DataCleanManager.java)| 本应用数据清除管理器
  [DatabaseExportUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DatabaseExportUtils.java)| 应用数据库导出工具类
  [DateUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DateUtil.java)| 日期操作工具类
  [DbHelper](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DbHelper.java)| 数据库帮助类
  [DensityUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DensityUtil.java)| 屏幕信息获取数值的转换
  [DeviceStatusUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DeviceStatusUtils.java)| 手机状态工具类 主要包括网络、蓝牙、屏幕亮度、飞行模式、音量等
  [DigestUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DigestUtils.java)| DigestUtils
  [FileUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/FileUtils.java)| 文件操作
  [HanziToPinyin](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/HanziToPinyin.java)| 拼音汉字处理
  [IOUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/IOUtils.java)| IOUtils
  [MD5](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/MD5.java)| MD5
  [MiscUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/MiscUtils.java)| 设备信息的获取
  [NetWorkUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/NetWorkUtils.java)| 网络状态
  [PhoneUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/PhoneUtil.java)| 手机组件调用工具类
  [PreferencesUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/PreferencesUtils.java)| sp工具类
  [RandomUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/RandomUtils.java)| 随机数工具类
  [RecorderControl](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/RecorderControl.java)| 录音工具类
  [SerializeUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/SerializeUtils.java)| Serialize
  [ShellUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ShellUtils.java)| shell指令
  [ShortCutUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ShortCutUtils.java)| 创建删除快捷图标
 [SingletonUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/SingletonUtils.java)| 单例工具
 [SizeUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/SizeUtils.java)| SizeUtils
 [SqliteUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/SqliteUtils.java)| SqliteUtils
 [StreamUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/StreamUtils.java)| 流转换成字符串
 [StringUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/StringUtils.java)| String
 [SystemUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/SystemUtils.java)| 线程池工具类
 [TimeUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/TimeUtils.java)| TimeUtils
 [ToastUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ToastUtils.java)| ToastUtils
 [TransitionTime](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/TransitionTime.java)|用来计算显示的时间是多久之前
 [ViewAnimationUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ViewAnimationUtils.java)|视图动画工具箱，提供简单的控制视图的动画的工具方法
 [ViewUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ViewUtils.java)|view控制
 [WiFiUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/WiFiUtil.java)|WiFiUtil
 [WindowUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/WindowUtils.java)|窗口工具箱
 [ZipUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ZipUtil.java)|实现的Zip工具
 [BadgeUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/BadgeUtil.java)|设置Badge
 [LogUtil](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/LogUtil.java)|LogUti工具类
 [ArrayUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ArrayUtils.java)|数组工具类，提供一些有关数组的便捷方法
 [ByteUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ByteUtils.java)|字节工具类，提供一些有关字节的便捷方法
 [CheckAdapter](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/CheckAdapter.java)|选择适配器
[CheckingUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/CheckingUtils.java)|提供常用数据验证的工具类，不符合的话就抛异常
[Countdown](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/Countdown.java)|倒计时器
[DialogUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DialogUtils.java)|对话框工具箱
[DoubleClickExitDetector](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/DoubleClickExitDetector.java)|双击退出识别器
[ImageProcessor](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ImageProcessor.java)|图片处理器
[InputMethodUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/InputMethodUtils.java)|软键盘工具类
[LoopTimer](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/LoopTimer.java)|循环定时器
[NestedGridView](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/NestedGridView.java)|嵌套使用的GridView
[NestedListView](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/NestedListView.java)|嵌套使用的ListView
[OSUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/OSUtils.java)|Android系统工具箱
[OtherUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/OtherUtils.java)|主要是给字符串添加html
[ReflectUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/ReflectUtils.java)|反射工具类，提供一些Java基本的反射功能
[RegexUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/RegexUtils.java)|正则表达式工具类，提供一些常用的正则表达式
[SDCardUtils](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/SDCardUtils.java)|SD卡工具箱
[Symbols](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/Symbols.java)|常用符号
[WebViewManager](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/WebViewManager.java)|WebView管理器，提供常用设置
[AmUtiles](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/AmUtiles.java)|定时器工具类
[XmlParseUtiles](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/XmlParseUtiles.java)|对pull解析xml进行了封装
[AntiEmulatorUtiles](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/AntiEmulatorUtiles.java)|检测是否在模拟器上运行
[code style](https://github.com/l123456789jy/Lazy/tree/master/lazylibrary/src/file)|drakeet的code style
[FastBlur](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/FastBlur.java)|毛玻璃效果
[AES](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/AES.java)|AES加解密
[M3U8ParserUtiles](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/M3U8ParserUtiles.java)|解析m3u8文件
[Hex](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/Hex.java)|转换成16进制
[CreatQRCodeImg](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/CreatQRCodeImg.java)|生成带log二维码
[NoPreloadViewPager](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/NoPreloadViewPager.java)|禁止预加载的viewpager
[App](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/App.java)|通过反射获取Application单利的工具类
[MediaPlayerUtiles](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/MediaPlayerUtiles.java)|播放资源目录下音频文件
[Repair7PopupWindow](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/view/Repair7PopupWindow.java)|修复7.0上popwindow位置显示错误
[NioFileUtiles](https://github.com/l123456789jy/Lazy/blob/master/lazylibrary/src/main/java/com/github/lazylibrary/util/NioFileUtiles.java)|使用NIO操作文件提高性能

#### 仿ios对话框
#### 效果图  
![log](https://github.com/devzwy/KUtils/raw/master/images/dialog_2.png)  
#### 3. 展示仿iOS对话框(底部)
```Java
new ActionSheetDialog(mContext)
                    .builder()
                    .setCancelable(false)
                    .setCanceledOnTouchOutside(false)
                    .setTitle("提示")
                    .addSheetItem("删除", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {

                        }
                    })
                    .addSheetItem("进入", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            readyGoThenKill(MainActivity.class);
                        }
                    }).show();
```
#### 效果图  
![log](https://github.com/devzwy/KUtils/raw/master/images/dialog_3.png)  

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
## 圆角图片及图片加载
-------------------

#### 使用和系统Image相同
```xml
<!--border_color  边框的颜色    border_width边框的宽度-->
<com.zwy.kutils.widget.customview.circleimageview.CircleImageView
            android:id="@+id/cv"
            android:layout_width="40dp"
            android:layout_height="40dp"
            app:border_color="@color/blue_3682"
            app:border_width="2dp"
            android:layout_alignParentLeft="true"
            android:layout_centerVertical="true"
            android:layout_marginLeft="15dp"/>
```

#### Glide图片加载的简单使用
```Java
Glide.with(mContext).load("http://img14.poco.cn/mypoco/myphoto/20130410/14/173420773201304101425203047950463653_010.jpg")
                .asBitmap().placeholder(R.drawable.nullimg).into(mCv);
```
#### 效果图  
![circleimageview](https://github.com/devzwy/KUtils/raw/master/images/circleimageview.png)  

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
```Java
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
```
##### 在APP的build.gradle dependencies节点下加入
```Java
implementation 'com.github.fly803:BaseProject:vXXX'
```
#### 2.clone项目到本地，将BaseProject库直接依赖到项目。
```Java
compile project(':BaseProject')
```
#### 3.aar方式依赖
##### 在项目根目录下新建aars文件夹，将BaseProject目录build/outputs下的kutils-release.aar文件copy进aars文件夹  在app的build.gradle 最外层节点加入
```xml
repositories { flatDir { dirs '../aars' } }
```
#####   在dependencies节点下加入对aar的依赖
```xml
compile(name: 'BaseProject-release', ext: 'aar') 
```

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