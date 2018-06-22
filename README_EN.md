README
===========================
# BaseProject
The Android project foundation library contains four main parts: first, resolution adaptation two, network request frame Retrofit2 encapsulation.
Three, Android base class encapsulation and common Utils method of project four, event bus RxBus based on RxJava and RxAndroid.
1.Android resolution adaptation scheme, to solve the problem of resolution adaptation, can be directly based on the design of the size of the drawings, do not do extra operation, simple and convenient and accurate.
2. encapsulates Retrofit2, unifies exception handling, and makes unified encapsulation of network errors, network errors, connection failures, certificate validation failures, without users.
In separate processing, the framework has been dealt with in a unified manner, only dealing with the correct return part. The server defined Api errors have also been processed, and the future improvements can allow users.
The custom server returns errors, and the custom server returns the data type. Now it needs to follow the uniform format.
3. encapsulate Utils commonly used in Android development. Maybe your project only needs this library. Do not believe you, there is a picture of the truth.
The high imitation iOS progress bar and dialog box, the encapsulation of the activity base class (can inherit from BaseActivity self-development), commonly used custom View (round corner head image, etc.), Glide one line code load the picture, can be directly dependent on use.
4. encapsulated on Rxjava and RxAndroid, and optimized event bus RxBus for Android. The event bus is a channel for communication.
With all the information running on it, every component or control in Android can send a variety of information to it, just subscribe to the bus in each component or control.
When the bus receives the message, these subscribers can also receive the messages. Reduce the use of high occupancy resource controls such as broadcasting.
If you like, you might as well star. Project address: https://github.com/fly803/BaseProject

[![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-陈刚-orange.svg)](https://github.com/fly803/BaseProject) 
[![](https://jitpack.io/v/fly803/BaseProject.svg)](https://jitpack.io/#fly803/BaseProject)
[![](https://img.shields.io/badge/QQ-356576318-brightgreen.svg)]()

****
### Author陈刚 sam QQ：356576318 QQ群：688700847
### E-mail:356576318@qq.com
****
```diff

```


Catalogues

* [screen resolution adaptation]

* [Retrofit2 encapsulation] (#Retrofit2 package)

* [RxBus integration] (#RxBus integration)

* [commonly used Utils package] (commonly used Utils package))

* [integration of the library]


Screen resolution adaptation

Overview of resolution adaptation

The scheme referred to the better Android adaptation areas, such as the plans of Guo Lin, Stormzhang, Hongyang and Kezi, which can be said to be integrated with the director of the family.

The best adaptation results are achieved at the lowest cost. We all want resolution adaptation to be the same. To get the effect map, no additional calculation is required, and the layout directly copies the size of the design.

My adaptation plan can achieve this effect, you say it is not pleasant.


The basic principle

When App runs, it reads the corresponding dimens files under Res, and BaseProject has all the corresponding relationships that have been established in the Res for the common resolution of the mainstream mobile phones.

Without user extra operation, the mainstream mobile phone can be perfectly adapted. The pro test can match 95% of the mainstream mobile phone, even if the default can not fit the mobile phone, it can also be adapted through the tools of this article (ResolutionTools.png). If users want to match other resolutions, they also provide corresponding acquisition methods.

Low - end mobile phones with less than 1280x720 are very few, and all 960x640480x320 and other low - resolution phones do not provide default support. If you need support, please add them manually.


The resolution of the default:

Resolution description
 ---  | ---
#### Default Support Resolution：
 Resolution | description
 ---  | ---
| 1184 x 720   | 带虚拟键1280x720手机实际dimens夹
| 1280 x 720   | 无虚拟键1280x702手机
| 1776 x 1080  | 带虚拟键盘1920x1280手机，常见型号Google nexus5
| 1794 x 1080  | 带虚拟键盘1920x1280手机，常见型号oppo，vivio华为等手机型号
| 1812 x 1080  | 带虚拟键盘1920x1280手机，常见型号小米，华为等手机型号
| 1920 x 1080  | 无虚拟键盘1920x1280手机，常见型号小米，OPPO，vivo，联想，中兴，魅族等手机
| 2280 x 1080  | 常见型号华为p20，屏幕比例19:9
| 2076 x 1080  | 物理分辨率2950x1440，渲染分辨率实际dimens文件夹，常见型号三星S8
| 2392 x 1440  | 带虚拟键盘2560x1440手机，常见型号Google pixsel系列
| 2560 x 1440  | 无虚拟键盘2560x1440手机，常见型号小米，OPPO，vivo，联想，中兴，魅族等手机
| 2712 x 1440  | 密度3.5，分辨率2560x1440手机，常见型号Google nexus6等
| 2792 x 1440  | 常见型号三星S8+等，dimens实际文件夹
| 2960 x 1440  | 常见型号三星s9等，dimens实际文件夹 长宽比18.5:9

Integrated resolution adaptation

An overview of the use

There are two ways to adapt the integrated resolution.

The 1. is integrated with BaseProject through Gradle. With BaseProject integration, the default resolution is supported.

Other resolutions are added manually through the doc/ toolkit / allocation rate adapter dimens tool.Jar, as shown in the figure:

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionTools.png)

The dimens copy is added to the res of your project under the additional support. Note that the input name is the form of values+xxxdpi+ resolution, such as values-xhdpi-960x640.

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/dimens.png)

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/project_res.png)

The resolution adapter tool download address:

Https://github.com/fly803/BaseProject/blob/master/doc/Tools/ResolutionAdaption.jar


By manually integrating tools provided by BaseProject and using tools to generate dimens, all dimens and corresponding folders are copied to the res folder of their own project.

The resolution adapter tool download address:

Https://github.com/fly803/BaseProject/blob/master/doc/Tools/ResolutionAdaption.jar





The specific use method in the project:

Get the effect map:

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/Mark.png)


According to our thought:


The size of the layout of the design


The layout library should be written in this way:

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/mark_layout.png)


If you want to input the corresponding size according to the effect picture, if you write 1px, you can change it to PX1.

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/mark_compare.jpg)

When you feel it, think about it. It's not easy to write a layout in this way.


The first thing to say is: This PX does not represent PX1 pixels. I will do DP processing internally and turn it into the corresponding size of the corresponding phone. This is the principle of the fit of the library.

Next, look at the effects of different cell phones and different resolutions:

Samsung S7 resolution: 2560x1440

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/S7.png)


Google pixsel XL resolution: 2560x1440 with virtual key

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/Pixsel_XL.png)


Google pixsel2 resolution: 1920x1080 with virtual keys

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/Pixcel2.png)


HUAWEI P9 resolution: 1920x1080

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/P9.png)



Lenovo cell phone resolution: 1280x720

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/ResolutionShow/720.png)


The above several mobile phones with different resolutions perfectly realized their adaptation.


No longer need to take the design draft to think about the width of the control and how much DP it takes.

No more percentage is calculated (if percentage controls are used to complete the adaptation).

No more to explain to UI MM what DP is.


And careful students must find that there are 3 horizontal lines at the bottom, which are very strange. How can the effect picture come out? Actually, those 3 horizontal lines are just to illustrate the matching effect. Now the size of the effect diagram is

1920x1080, now copying the width of px1080, and half the width of px540., you can see a number of mobile phone models and different resolution, but the width is full and half full, proving that the matching effect is good.

Retrofit2 encapsulation of network request framework Retrofit2

1.Retrofit+RxJava handles gracefully the server returns exceptions and errors.

Abnormality & error


This often happens in real development, such as logon requests, interfaces returned.

Information includes the state of request return: failure or success, error code, User object, and so on. If the login failure caused by network and other reasons can be attributed to an exception, if the user information input error caused by login failure is wrong.


If the server returns a unified data format:


/**
 * 标准数据格式
 * @param <T>
 */
public class Response<T> {
    public int state;
    public String message;
    public T data;
}



The login failure caused by network exception will call the onError event of subscribe directly when using Retrofit+RxJava request.

The login failure caused by password error will call the onNext event of subscribe when using Retrofit+RxJava request.


Exception or error, you should process exception information in subscribe, as follows:

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



Now I hope that in any case of error, the onError event will be invoked and the error message will be processed by model. Then, we should have a ExceptionEngine to deal with the error message in the event stream.

Handling exceptions in Workflow


Under normal circumstances, the process of obtaining network data is usually as follows:


Request interface - > parse data - > update UI


The entire data request process takes place in the workflow of Rx. When there is an exception, we should try not to make a judgment in the UI layer. In other words, we don't have to tell the UI layer specific error information, just let him pop up a message (Toast or Dialog) to show the information we give it.


Unified encapsulation, intercepting exception errors


Unified encapsulation interception exception error is mainly to obtain specific error information, distribute to the upper level of UI, give users hints, enhance user experience.


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


So the whole logic is:

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/RetrofitExceptionHandle.png)

Request interfaces and data parsing may be wrong, so error handling is done on these two levels. For better decoupling, we intercept the error through the interceptor and distribute the information according to the type of error.


The method of using 2. integrated Retrofit package

BaseProject test engineering app project API folder copied to its own engineering directory, contains four documents:

[log] (https://raw.githubusercontent.com/fly803/BaseProject/master/doc/GitHubPictures/api.png)


The corresponding baseurl settings are set up in AppConfig.

The corresponding interface settings are set up in RequestApiInterface.

UrlConstants is used to splice interface strings.


If the operation is done as above, the corresponding interface can be called, and the way of calling is shown below.

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
Because of the encapsulation of exceptions and errors, the onNext method can only be written.


Three, Android base class encapsulation and common Utils methods

1.Android common toolbar

Tool description
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

Encapsulation of base class

- -- - ---

For reference, please refer to demo. Please inherit from BaseActivity.

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



Event bus based on RxJava and optimized for Android.
Usage

We recommend obtaining the single instance of bus through injection or another appropriate mechanism.

Or get singleton like following:

Bus bus = BusProvider.getInstance();

Subscribing

To subscribe to an event, declare and annotate a method with @Subscribe. The method should be public and take only a single parameter.

@Subscribe
public void onEvent(SomeEvent event) {
    // TODO: Do something
}

You can also create subscription like following:

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

Register and unregister your observer

To receive events, a class instance needs to register with the bus.

bus.register(this);

The customSubscriber also needs to register with the bus.

bus.registerSubscriber(this, customSubscriber);

Remember to also call the unregister method when appropriate.

bus.unregister(this);

Publishing

To publish a new event, call the post method:

bus.post(new SomeEvent("Message"));

# Add RxBus to your project

Gradle:

implementation 'com.github.fly803:BaseProject:1.0.0'
get latest realease
https://github.com/fly803/BaseProject/releases
-----------

#### 1.jitpack.io
##### build.gradle

allprojects {
    repositories {
        maven { url "https://jitpack.io" }//加入这一行代码
    }
}



****
Contact QQ：356576318(from github)
****


## [English](https://github.com/fly803/BaseProject/README_EN.md) | 中文

#### Friend Link
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