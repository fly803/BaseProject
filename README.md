README
===========================
# BaseProject
Android项目基础库，包含四大部分：一、分辨率适配 二、网络请求框架Retrofit2封装 
For the English readme：## [English](https://github.com/fly803/BaseProject/blob/master/README_EN.md) |
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
喜欢的话不妨star一下吧。项目地址：https://github.com/fly803/BaseProject


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
该方案参考的了Android适配领域做得比较好的方案，如郭霖，Stormzhang，鸿洋和凯子的方案，可以说综合了各家之所长。
以最小的代价，实现最好的适配效果。我们都希望分辨率适配是这样。拿到效果图，不需要额外计算，布局直接抄设计图上的尺寸。
我的适配方案就可以达到这样的效果，你说爽不爽。

#### 基本原理
App在运行的时候，会在Res下读取对应的dimens文件，BaseProject已经在Res里面对主流的手机的常用分辨率建立的所有的对应关系，
无需用户额外操作，就可以完美适配主流手机。亲测可以适配市面主流手机的95%，即使默认不能适配的手机，也可以通过本文的工具进行适配（ResolutionTools.jar）。用户如果想适配其他分辨率，也提供了相应的获取方法。
介于低于1280x720的低端手机已经很少，所有960x640,480x320等低分辨率手机没有提供默认支持，如需支持，请手动添加。

#### 默认支持的分辨率：
 分辨率 | 分辨率描述
 ---  | ---
| 1280 x 720   | 无虚拟键1280x702手机
| 1184 x 720   | 带虚拟键1280x720手机实际dimens夹
|    xhdpi     | 手机密度为xhdpi的其他型号手机
| 1920 x 1080  | 无虚拟键盘1920x1280手机，常见型号小米，OPPO，vivo，联想，中兴，魅族等手机
| 1776 x 1080  | 带虚拟键盘1920x1280手机，常见型号Google nexus5
| 1794 x 1080  | 带虚拟键盘1920x1280手机，常见型号oppo，vivio华为等手机型号
| 1812 x 1080  | 带虚拟键盘1920x1280手机，常见型号小米，华为等手机型号
| 2220 x 1080  | 渲染分辨率为2220x1080，物理分辨率2960x1440，常见型号三星S8(可以在设计里面进行分辨率的设置）
| 2280 x 1080  | 常见型号华为p20，屏幕比例19:9
|    xxhdpi    | 手机密度为xxhdpi的其他型号手机
| 2560 x 1440  | 无虚拟键盘2560x1440手机，常见型号小米，OPPO，vivo，联想，中兴，魅族等手机
| 2392 x 1440  | 带虚拟键盘2560x1440手机，常见型号Google pixsel系列
| 2712 x 1440  | 密度3.5，分辨率2560x1440手机，常见型号Google nexus6等
| 2792 x 1440  | 常见型号三星S8+等，dimens实际文件夹
| 2960 x 1440  | 常见型号三星s9等，dimens实际文件夹 长宽比18.5:9
|    xxxhdpi   | 手机密度为xxxhdpi的其他型号手机


### 集成分辨率适配
#### 使用概述 
有两种集成分辨率适配的方式。
###### 1.通过Gradle集成BaseProject。集成BaseProject，就对默认分辨率都进行了支持。
###### 其他分辨率通过doc/工具集/分配率适配dimens工具.jar手动添加，如图所示：
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionTools.png) 
##### 加需要额外支持的dimens拷贝到自己的工程res下,注意输入的名字是values+xxxdpi+分辨率的形式，如values-xhdpi-960x640
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/dimens.png) 
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/project_res.png) 
###### 分辨率适配工具下载地址：
https://github.com/fly803/BaseProject/blob/master/doc/Tools/ResolutionAdaption.jar

###### 2.通过BaseProject提供的工具手动集成，利用工具生成dimens，将所有的dimens和相应的文件夹拷贝到自己工程的res文件夹
###### 分辨率适配工具下载地址：
https://github.com/fly803/BaseProject/blob/master/doc/Tools/ResolutionAdaption.jar


###### 项目中具体使用方法：
拿到效果图:
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/Mark.png) 

##### 按照我们的思想：

    布局直接抄设计图上的尺寸

##### 布局文库应该这么写：
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/mark_layout.png) 

##### 来张组合图，直接根据效果图输入相应的尺寸，只是写法上，如果是1px，改成px1就可以了，字体如果是1sp，改成sp1就可以了，感受一下：
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/mark_compare.jpg) 
##### 感受完了，想一想，按照这种方式去写布局你说爽不爽。

##### 首先说一下：这个px1并不代表写死1px像素，我在内部会进行dp处理，转成相应手机对应的尺寸。字体单位sp1也在内部进行相应的到安卓sp的转换。这就是本库适配的原理。

##### 接下来：看下不同手机，不同分辨率下的效果：
三星S7 分辨率:2560x1440
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/S7.png) 

Google pixsel XL 分辨率：2560x1440 带虚拟键
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/Pixsel_XL.png) 

Google pixsel2 分辨率：1920x1080 带虚拟键
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/Pixcel2.png) 

华为P9 分辨率：1920x1080
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/P9.png) 


联想手机 分辨率：1280x720
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/720.png)

上述若干不同分辨率的手机，完美实现了适配，最为重要的是：

    再也不用拿着设计稿去想这控件的宽高到底取多少dp
    再也不用去计算百分比了（如果使用百分比控件完成适配）
    再也不用去跟UI MM去解释什么是dp了

而且细心的同学肯定发现，最底下有3条横向的细线很奇怪，效果图怎么会出这种图。其实那3条横线只是为了说明适配效果，我特意加上去的。现在效果图的尺寸是
1920x1080，现在照抄宽度px1080，和宽度的一半px540.大家可以看到多款手机型号和分辨率不同，但是宽度条正好充满了全部和充满了一半，证明适配效果良好。
## 二、网络请求框架Retrofit2封装 
###  1.Retrofit+RxJava 优雅的处理服务器返回异常、错误 
异常&错误

实际开发经常有这种情况，比如登录请求，接口返回的
信息包括请求返回的状态：失败还是成功，错误码，User对象等等。如果网络等原因引起的登录失败可以归结为异常，如果是用户信息输入错误导致的登录失败算是错误。

假如服务器返回的是统一数据格式：

/**
 * 标准数据格式
 * @param <T>
 */
public class Response<T> {
    public int state;
    public String message;
    public T data;
}

    网络异常导致的登录失败，在使用Retrofit+RxJava请求时都会直接调用subscribe的onError事件；
    密码错误导致的登录失败，在使用Retrofit+RxJava请求时都会调用subscribe的onNext事件；

无论是异常还是错误，都要在subscribe里面处理异常信息，如下代码：
```Java
APIWrapper.getInstance().login("username", "password")
                .subscribe(new Observer<Response<User>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<User> data) {
                        if(data.state == 1001){
                            //.....
                        }else if(data.state == 1002){

                        }
                    }
                });
```



现在我希望在发生任何错误的情况下，都会调用onError事件，并且由model来处理错误信息。那么，此时我们就应该有一个ExceptionEngine来处理事件流中的错误信息了。
在工作流中处理异常

在正常情况下，我们获取网络数据的流程通常如下：

请求接口->解析数据->更新ＵＩ

整个数据请求过程都是发生在Rx中的工作流之中。当有异常产生的时候，我们要尽量不在ui层里面进行判断，换句话说，我们没有必要去告诉ui层具体的错误信息，只需要让他弹出一个信息（Toast或者Dialog）展示我们给它的信息就行。

统一封装，拦截异常错误

统一封装拦截异常错误主要是为了获取具体的错误信息，分发给上层的UI，给用户以提示，增强用户体验。
```Java
/**
     * 所以调研接口，统一调用这个方法
     * @param ob
     * @param subscriber
     */
  public void toSubscribe(Observable ob, final ProgressSubscriber subscriber) {
    //数据预处理
    ObservableTransformer<BaseResponse<Object>, Object> result = handleResult();
    Observable observable = ob.compose(result);
    observable.subscribeWith(subscriber);
  }

    /**
     * 默认情况下,发送者和接收者都运行在主线程,但是这显然是不符合实际需求的,我们在日常使用中,
     * 通常用的最多的就是在子线程进行各种耗时操作,然后发送到主线程进行,难道我们就没有办法继续
     * 用这个优秀的库了?想多了你,一个优秀的库如果连这都想不到,怎么能被称为优秀呢,RxJava中有线
     * 程调度器,通过线程调度器,
     * 
     * Transformer的变化:RxJava1.X为rx.Observable.Transformer接口, 继承自
     * Func1<Observable<T>, Observable<R>>, RxJava2.X为io.reactivex.ObservableTransformer<Upstream, Downstream>,是一个独立的接口。
     * Flowable则是FlowableTransformer，如果你使用Flowable，以下ObservableTransformer
     * 替换FlowableTransformer即可。
     * @param <T>
     * @return
     */
  public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
    return new ObservableTransformer<BaseResponse<T>, T>() {
      @Override public Observable<T> apply(Observable<BaseResponse<T>> tObservable) {
        return tObservable.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
          @Override public Observable<T> apply(BaseResponse<T> result) {
            //成功后交给界面处理
            if (result.getCode() == BaseProjectConfig.SUCCESS_CODE) {
                return createData(result.getData());
            } else {
                //统一处理服务器返回值非正常结果
                Log.d(BaseProjectConfig.TAG, "统一处理服务器返回值非正常结果apply: " + ServerReturnCode.getReasonByCode(result.getCode()));
                return Observable.error(new ApiException(ServerReturnCode.getReasonByCode(0)));
            }
          }
        })
         /*
          - Schedulers.io()      io操作的线程, 通常io操作,如文件读写,读写数据库、网络信息交互等.
          - Schedulers.computation()      计算线程,适合高计算,数据量高的操作.
          - Schedulers.newThread()      创建一个新线程,适合子线程操作.
          - AndroidSchedulers.mainThread()      Android的主线程,主线程       
         */
        .subscribeOn(Schedulers.io()) //线程调度器,将发送者运行在子线程,subscribeOn(),只有在第一次调用的时候生效,之后不管调用多少次,只会以第一次为准.
        .unsubscribeOn(Schedulers.io())//解除订阅
//        .subscribeOn(AndroidSchedulers.mainThread())//
        .observeOn(AndroidSchedulers.mainThread());//接受者运行在主线程 observeOn(),可以被调用多次,每次调用都会更改线程.
      }
    };
  }

  /**
   * 创建成功的数据,观察者模式,这里产生事件,事件产生后发送给接受者
   */
  private static <T> Observable<T> createData(final T data) {
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override public void subscribe(ObservableEmitter<T> e) throws Exception {
          e.onNext(data);
          e.onComplete();
      }
    });
  }

```
所以整个逻辑是这样的： 
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/RetrofitExceptionHandle.png) 
请求接口和数据解析都可能出错，所以在这两层进行错误处理。为了更好的解耦，我们通过拦截器拦截错误，然后根据错误类型分发信息。

###  2.集成Retrofit封装使用方法
BaseProject测试工程app工程中的api文件夹拷贝到自己的工程目录下，包含四个文件：
![log](https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/api.png) 

在AppConfig里面进行相应的baseurl的设置。
在RequestApiInterface进行相应的接口设置。
UrlConstants用来拼接接口字符串。

进行如上操作好，就可以调用相应的接口了，调用方式如下所示。
```Java
 RequestBusiness.getInstance()
.toSubscribe(RequestBusiness.getInstance().getAPI().demoRxJava2("220.181.90.8"),
        new ProgressSubscriber<BaseResponse<IpResult>>(new SubscriberOnNextListener<IpResult>() {
            @Override
            public void onNext(IpResult ipResult) {
                Log.d(AppConfig.TAG, "!!!onNext: "+ipResult.getCity());
                Snackbar.make(mRecyclerView, "postRequest:" + ipResult.getCity(), Snackbar.LENGTH_SHORT).show();
            }
        }, this));
```
由于统一对异常和错误进行了封装，所以只写onNext方法就可以了。

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
```Java
@Subscribe
public void onEvent(SomeEvent event) {
    // TODO: Do something
}
```
你应该也创建订阅像下面这样：
```Java
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
```
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

allprojects {
    repositories {
        maven { url "https://jitpack.io" }//加入这一行代码
    }
}

##### 在APP的build.gradle dependencies节点下加入

implementation 'com.github.fly803:BaseProject:相应的版本号：例如1.0.0'
配置仓库，添加mavenCentral() maven { url 'https://jitpack.io' }

###### 当前可以下载版本
implementation 'com.github.fly803:BaseProject:1.0.0',已release里面的最新release为准

#### 2.clone项目到本地，将BaseProject库直接依赖到项目。

compile project(':BaseProject')

#### 3.aar方式依赖
##### 在项目根目录下新建aars文件夹，将BaseProject目录build/outputs下的kutils-release.aar文件copy进aars文件夹  在app的build.gradle 最外层节点加入

repositories { flatDir { dirs '../aars' } }

#####   在dependencies节点下加入对aar的依赖

compile(name: 'BaseProject-release', ext: 'aar')


****
项目地址：https://github.com/fly803/BaseProject
集成过程出现问题可联系本人QQ：356576318(注明来自github)
****


## [English](https://github.com/fly803/BaseProject/blob/master/README_EN.md) | 中文

#### 友情链接
[github/UCodeUStory/S-MVP](https://github.com/UCodeUStory/S-MVP) 

[github/UCodeUStory/GradlePluginDevelop](https://https://github.com/UCodeUStory/GradlePluginDevelop)  

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
    Project location：https://github.com/fly803/BaseProject
[点我回到顶部](#readme)