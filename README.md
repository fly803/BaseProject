README
===========================
# BaseProject
Android项目基础库，包含分辨率适配，Retrofit2封装，rxbus，和项目常用Utils方法等。
Android分辨率适配方案，解决大家的分辨率适配烦恼，可以直接依据设计图写尺寸，不做额外的操作，简单方便准确。
封装Retrofit2，统一了异常处理，对网络错误，网络错误，连接失败，证书验证失败进行了统一封装，无需用户
在单独处理，框架已经做了统一处理，只需在处理正确返回部分。对服务器定义的Api错误，也做了处理，未来完善可以让用户
自定义服务器返回错误，和自定义服务器返回数据类型，现在还需要遵循统一格式。
Rxbus封装，在Rxjava基础上进行了封装，并对Android进行了优化
封装android开发中常用的Utils,也许你的项目只需要这一个库就完全够了。不信你看，有图有真相。
高仿iOS进度条和对话框、activity基类的封装(可继承自BaseActivity自行拓展)、常用自定义View(圆角头像等)、
Glide一行代码加载图片、可直接依赖使用，喜欢的话不妨star一下吧。

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


## 屏幕分辨率适配
### 分辨率适配概述
#### 基本原理
App在运行的时候，会在Res下读取对应的dimens文件，BaseProject已经在Res里面对主流的手机的常用分辨率建立的所有的对应关系，
无需用户额外操作，就可以完美适配主流手机。亲测可以适配市面主流手机的95%。用户如果想适配其他分辨率，也提供了相应的获取方法。
介于低于1280x720的低端手机已经很少，所有960x640,480x320等低分辨率手机没有提供默认支持，如需支持，请手动添加。默认支持的分辨率：
| 1184 x 720   |
| 1196 x 720   |
| 1280 x 720   |
| 1776 x 1080  |
| 1812 x 1080  |
| 1920 x 1080  |
| 2392 x 1440  |
| 2560 x 1440  |
| 2960 x 1440  |
### 使用
#####有2种使用方法 

##### 方法1：
集成BaseProject，就对默认分辨率都进行了支持。其他分辨率通过doc/工具集/分配率适配dimens工具.jar手动添加
https://github.com/fly803/BaseProject/blob/master/doc/%E5%B7%A5%E5%85%B7%E9%9B%86/%E5%88%86%E9%85%8D%E7%8E%87%E9%80%82%E9%85%8Ddimens%E5%B7%A5%E5%85%B7.jar
 ![log](https://github.com/devzwy/KUtils/raw/master/images/loginfo.png) 
##### 方法2：
分配率适配dimens工具生成dimens，将所有的dimens和相应的文件夹拷贝到自己工程的res文件夹
## 引导页集成
-----------
- [x] 引导界面导航效果
- [x] 支持根据服务端返回的数据动态设置广告条的总页数
- [x] 支持大于等于1页时的无限循环自动轮播、手指按下暂停轮播、抬起手指开始轮播
- [x] 支持自定义指示器位置和广告文案位置
- [x] 支持图片指示器和数字指示器
- [x] 支持 ViewPager 各种切换动画
- [x] 支持选中特定页面
- [x] 支持监听 item 点击事件
- [x] 加载网络数据时支持占位图设置，避免出现整个广告条空白的情况
- [x] 多个 ViewPager 跟随滚动

### 使用

#### 1.在布局文件中添加 BGABanner

```xml
<com.zwy.kutils.widget.guide.BGABanner
    android:id="@+id/banner_guide_content"
    style="@style/MatchMatch"
    app:banner_pageChangeDuration="1000"
    app:banner_pointAutoPlayAble="false"
    app:banner_pointContainerBackground="@android:color/transparent"
    app:banner_pointDrawable="@drawable/bga_banner_selector_point_hollow"
    app:banner_pointTopBottomMargin="15dp"
    app:banner_transitionEffect="alpha" />
```

#### 2.在 Activity 或者 Fragment 中配置 BGABanner 的数据源

有多种配置数据源的方式，这里仅列出三种方式。

>配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播

```java
mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(MainActivity.this)
                .load(model)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .centerCrop()
                .dontAnimate()
                .into(itemView);
    }
});

mContentBanner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3"), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));
```

> 配置数据源的方式2：通过直接传入视图集合的方式配置数据源，主要用于自定义引导页每个页面布局的情况

```java
List<View> views = new ArrayList<>();
views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_1));
views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_2));
views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_3));
mContentBanner.setData(views);
```

> 配置数据源的方式3：通过传入图片资源 id 的方式配置数据源，主要用于引导页每一页都是只显示图片的情况

```
mContentBanner.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);
```

#### 3.监听广告 item 的单击事件，在 BGABanner 里已经帮开发者处理了防止重复点击事件

```java
mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
    }
});
```

#### 4.设置「进入按钮」和「跳过按钮」控件资源 id 及其点击事件，如果进入按钮和跳过按钮有一个不存在的话就传 0，在 BGABanner 里已经帮开发者处理了防止重复点击事件，在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏

```java
mContentBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
    @Override
    public void onClickEnterOrSkip() {
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        finish();
    }
});
```

### 自定义属性说明
```xml
<declare-styleable name="BGABanner">
    <!-- 指示点容器背景 -->
    <attr name="banner_pointContainerBackground" format="reference|color" />
    <!-- 指示点背景 -->
    <attr name="banner_pointDrawable" format="reference" />
    <!-- 指示点容器左右内间距 -->
    <attr name="banner_pointContainerLeftRightPadding" format="dimension" />
    <!-- 指示点上下外间距 -->
    <attr name="banner_pointTopBottomMargin" format="dimension" />
    <!-- 指示点左右外间距 -->
    <attr name="banner_pointLeftRightMargin" format="dimension" />
    <!-- 指示器的位置 -->
    <attr name="banner_indicatorGravity">
        <flag name="top" value="0x30" />
        <flag name="bottom" value="0x50" />
        <flag name="left" value="0x03" />
        <flag name="right" value="0x05" />
        <flag name="center_horizontal" value="0x01" />
    </attr>
    <!-- 是否开启自动轮播 -->
    <attr name="banner_pointAutoPlayAble" format="boolean" />
    <!-- 自动轮播的时间间隔 -->
    <attr name="banner_pointAutoPlayInterval" format="integer" />
    <!-- 页码切换过程的时间长度 -->
    <attr name="banner_pageChangeDuration" format="integer" />
    <!-- 页面切换的动画效果 -->
    <attr name="banner_transitionEffect" format="enum">
        <enum name="defaultEffect" value="0" />
        <enum name="alpha" value="1" />
        <enum name="rotate" value="2" />
        <enum name="cube" value="3" />
        <enum name="flip" value="4" />
        <enum name="accordion" value="5" />
        <enum name="zoomFade" value="6" />
        <enum name="fade" value="7" />
        <enum name="zoomCenter" value="8" />
        <enum name="zoomStack" value="9" />
        <enum name="stack" value="10" />
        <enum name="depth" value="11" />
        <enum name="zoom" value="12" />
    </attr>
    <!-- 提示文案的文字颜色 -->
    <attr name="banner_tipTextColor" format="reference|color" />
    <!-- 提示文案的文字大小 -->
    <attr name="banner_tipTextSize" format="dimension" />
    <!-- 加载网络数据时覆盖在 BGABanner 最上层的占位图 -->
    <attr name="banner_placeholderDrawable" format="reference" />
    <!-- 是否是数字指示器 -->
    <attr name="banner_isNumberIndicator" format="boolean" />
    <!-- 数字指示器文字颜色 -->
    <attr name="banner_numberIndicatorTextColor" format="reference|color" />
    <!-- 数字指示器文字大小 -->
    <attr name="banner_numberIndicatorTextSize" format="dimension" />
    <!-- 数字指示器背景 -->
    <attr name="banner_numberIndicatorBackground" format="reference" />
    <!-- 当只有一页数据时是否显示指示器，默认值为 false -->
    <attr name="banner_isNeedShowIndicatorOnOnlyOnePage" format="boolean" />
    <!-- 自动轮播区域距离 BGABanner 底部的距离，用于使指示器区域与自动轮播区域不重叠 -->
    <attr name="banner_contentBottomMargin" format="dimension"/>
</declare-styleable>
```
#### 效果图  
![log](https://github.com/devzwy/KUtils/raw/master/images/guide.gif)  




## 对话框进度条封装
------------------
### 如下为简单示例 其他效果请自行拓展
#### 1. 展示进度条
```Java
 DialogUIUtils.showLoadingHorizontal(mContext, "请稍后……", true, false, true).show();
```
#### 效果图  
![log](https://github.com/devzwy/KUtils/raw/master/images/dialog_1.png)  
#### 2. 展示仿iOS对话框(中间)
```Java
 DialogUIUtils.showTwoButtonAlertDialog(mContext, "提示", "是否进入主页", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }, "进入", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readyGoThenKill(MainActivity.class);
                }
            }, false);
```
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



## 数据库操作
-------------------
#### 1 配置项目的build.gradle
```Java
apply plugin: 'org.greenrobot.greendao'

android {
      greendao {
            schemaVersion 1//数据库版本号
            daoPackage 'com.alan.kutilssample.greendao'//设置DaoMaster、DaoSession、Dao包名 
            targetGenDir 'src/main/java'//设置DaoMaster、DaoSession、Dao目录
            //targetGenDirTestA：设置生成单元测试目录
            //generateTests：设置自动生成单元测试用例
        }
}
```
##### 创建一张表(新建实体)
###### 注解说明：  
- @Entity-------实体注解  
- @NotNull-------设置表中当前列的值不可为空  
- @Convert-------指定自定义类型  
- @Generated-------GreenDao运行所产生的构造函数或者方法，被此标注的代码可以变更或者下次运行时清除
- @Id-------主键Long型，可以通过@Id(autoincrement=true)设置自增长。通过这个注解标记的字段必须是Long，数据库中表示它就是主键，并且默认是自增的。
- @Index-------使用@Index作为一个属性来创建一个索引；定义多列索引(@linkEntity#indexes())
- @JoinEntity-------定义表连接关系
- @JoinProperty-------定义名称和引用名称属性关系
- @Keep-------注解的代码段在GreenDao下次运行时保持不变---1.注解实体类：默认禁止修改此类---2.注解其他代码段，默认禁止修改注解的代码段
- @OrderBy------- 指定排序
- @Property-------设置一个非默认关系映射所对应的列名，默认是的使用字段名,举例:@Property(nameInDb="name")
- @ToMany-------定义与多个实体对象的关系
- @ToOne-------定义与另一个实体（一个实体对象）的关系
- @Transient-------添加次标记之后不会生成数据库表的列
- @Unique-------向数据库列添加了一个唯一的约束
```Java

/**
 * ================================================================
 * 创建时间：2017/7/31 上午10:42
 * 创建人：Alan
 * 文件描述：greendao测试表 注意 该类不能实现Serializable接口
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
@Entity
public class User{
    @Id(autoincrement = true)
    private long id;
    private String name;
    private int age;
}

```
##### 编译项目(菜单Build-RebuildProject)后你会看到在你指定的目录下生成的类，如下图:
![circleimageview](https://github.com/devzwy/KUtils/raw/master/images/greendao.png)   
##### 现在可以初始化数据库相关参数了,application中：
```Java
   private void initGreenDao() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), DBNAME);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        Log.d("GreenDao初始化成功");
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
```
##### 业务操作部分代码。其他请自行百度或查看demo
```Java
//添加一条数据
String userName = Utils.getRandomName(1);
                int age = new Random().nextInt(30);
                User user = new User();
                user.setName(userName);
                user.setAge(age);
                userDao.insert(user);
                apped("已成功插入一条(name = " + userName + ",age = " + age + ")的数据");
//删除全部数据
//                //删除一般配和查询完成
//                QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
//                List<User> userList = userQueryBuilder.where(UserDao.Properties.Name.eq("志文")).list();
//                User u = (userList != null && userList.size() > 0) ? userList.get(0) : null;
//                if (u == null) {
//                    apped("当前要删除的用户不存在");
//                    return;
//                }
//                userDao.delete(u);
//                apped("删除成功");
                userDao.deleteAll();
                apped("已删除全部数据");
//修改
 QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
                List<User> userList = userQueryBuilder.where(UserDao.Properties.Name.eq("志强")).list();
                if (userList != null && userList.size() > 0) {
                    userList.get(0).setName("张三");
                    userDao.update(userList.get(0));
                    apped("已将姓名为志强的数据修改姓名为张三");
                } else {
                    apped("要修改的数据不存在");
                }
//查询
 apped("当前User表中所有的数据：");
                for (int i = 0; i < userDao.loadAll().size(); i++) {
                    apped(userDao.loadAll().get(i).toString());
                }
```










## 万能适配器
-------------------
#### 1 定义一个适配器
```Java
 private class MyAdapter extends BaseQuickAdapter<TitleModel, BaseViewHolder> {

        public MyAdapter(@Nullable List<TitleModel> data) {
            super(R.layout.item_rv_main, data);//item 资源ID
        }

        /**
         * Implement this method and use the helper to adapt the view to the given item.
         *
         * @param helper A fully initialized helper.
         * @param item   The item that needs to be displayed.
         */
        @Override
        protected void convert(BaseViewHolder helper, TitleModel item) {
            helper.setText(R.id.title, item.getTitle());//给控件设值
            helper.addOnClickListener(R.id.clicktestview);//给子view添加点击事件
        }
    }
```
#### 2 初始化适配器相关参数
```Java
    private void initAdapter() {
        mAdapter = new MyAdapter(null);//可以直接传入数据，数据未获取到的情况下可以传null
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置列表加载动画
        mAdapter.isFirstOnly(false);//是否仅在第一次加载列表时展示动画
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.setAdapter(mAdapter);
    }
```
#### 3 给适配器添加数据
```Java
//第一种方式
 List<TitleModel> list = new ArrayList<>();
                                list.add(new TitleModel("GreenDao使用", true));
                                list.add(new TitleModel("功能2", false));
                                list.add(new TitleModel("功能3", false));
                                list.add(new TitleModel("...", false));
                                mAdapter.setNewData(list);//添加集合数据
                                
//第二种方式
 for (int i = 0; i < 6; i++) {
                                mAdapter.addData(new TitleModel("RV功能展示", false));//添加单条数据
                            }
//        mAdapter.setEmptyView(getEmptyview());//设置没有数据时的空白页面
//        mAdapter.addFooterView(getFootView());//添加一个脚布局 有三个相应的方法
//        mAdapter.addHeaderView(getHeaderView());//添加一个头布局 有三个相应的方法
//        mAdapter.notify...();//内容发生改变时刷新方法
//        mAdapter.remove();
```

#### 4 点击事件处理
```Java
 //适配器Item点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if( ((TitleModel)adapter.getData().get(position)).getIndex()){
                    toNext(position);
                }else {
                    showToast("您点击了第" + (position + 1) + "条数据");
                }
            }
        });
        //适配器子view点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.clicktestview:
                        showToast("您点击了第" + (position + 1) + "条数据的子view");
                        break;
                }
            }
        });
```
#### 5 设置下拉刷新和上啦加载相关代码，具体使用请参考demo
```Java
        //下拉刷新监听
        mSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapterData();
            }
        });
        //加载更多监听
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                addData();
            }
        }, mRv);
```
```Java
 Random random = new Random();
                            int a = random.nextInt(3);
                            Log.d("nextInt = " + a);
                            if (a == 0) {
                                Log.d("nextInt = " + a);
                                for (int i = 0; i < 5; i++) {
                                    mAdapter.addData(new TitleModel("我是新添加的第" + (i + 1) + "条数据", false));
                                }
                                mAdapter.loadMoreComplete();//关闭“正在加载更多提示”
                            } else if (a == 1) {
                                mAdapter.loadMoreEnd();//显示没有更多数据
                            } else if (a == 2) {
                                mAdapter.loadMoreFail();//显示加载失败，点击重试
                            } else {
                                Log.d("nextInt = " + a);
                                for (int i = 0; i < 20; i++) {
                                    mAdapter.addData(new TitleModel("我是新添加的第" + (i + 1) + "条数据", false));
                                }
                                mAdapter.loadMoreComplete();
                            }
```
#### 效果图  
![circleimageview](https://github.com/devzwy/KUtils/raw/master/images/adapter.gif)  



## 网络请求
---------------
##### 发起一个简单的网络请求
```Java
 //发起一个简单的网络请求
        OkGo.<String>post("").tag("").params("key", "v").execute(new AbsCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                return null;
            }
        });
```
##### [详细文档请点击这里](https://github.com/jeasonlzy/okhttp-OkGo/wiki "OkGo")


## 事件分发
------------------------  
##### 根据EventBus3.0源码拓展而来,实现post事件和接收事件时可以通过tag进行筛选,比如EventBus.getDefault().post("内容","tag1");发出的事件只有@Subscribe(threadMode=ThreadMode.MAIN,tag="tag1")这样的形式接收才能收到，如果在发送时未指定tag，那么接收时也不要使用tag，相反的，如果发送时用了tag，而接收时未使用tag，也是收不到的。粘性事件保持与EventBus3.0一样的机制，未加入tag
##### 使用方式和官方保持一致
#### 注册
```Java
EventBus.getDefault().register(mContext);
```
#### 注销
```Java
 EventBus.getDefault().unregister(mContext);
```
#### 发出一般事件
```Java
EventBus.getDefault().post("我是事件内容1");//未指定tag，接收时也不要写入tag
EventBus.getDefault().post("我是事件内容2","tag1");//指定tag，只有接收时指定了该tag才能收到事件
```
#### 接收一般事件
```Java
 @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String msg){
        // TODO: 2017/8/1 我能接收到 "我是事件内容1"
    }
 @Subscribe(threadMode = ThreadMode.MAIN,tag = "tag1")
    public void onEvent(String msg){
        // TODO: 2017/8/1 我能接收到 "我是事件内容2"
    }
```
#### 发出粘性事件
```Java
EventBus.getDefault().postSticky("我是粘性事件");
```
#### 接收粘性事件

```Java
@Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(String msg){
        // TODO: 2017/8/1 我能接收到粘性事件
    }
```

## 动画集成
-------------
### 使用
```java
YoYo.with(Techniques.Tada)
    .duration(700)
    .repeat(5)
    .playOn(findViewById(R.id.edit_area));
```
### 效果
#### Attension
`Flash`, `Pulse`, `RubberBand`, `Shake`, `Swing`, `Wobble`, `Bounce`, `Tada`, `StandUp`, `Wave`

#### Special
`Hinge`, `RollIn`, `RollOut`,`Landing`,`TakingOff`,`DropOut`

#### Bounce
`BounceIn`, `BounceInDown`, `BounceInLeft`, `BounceInRight`, `BounceInUp`

#### Fade
`FadeIn`, `FadeInUp`, `FadeInDown`, `FadeInLeft`, `FadeInRight`

`FadeOut`, `FadeOutDown`, `FadeOutLeft`, `FadeOutRight`, `FadeOutUp`

#### Flip
`FlipInX`, `FlipOutX`, `FlipOutY`

#### Rotate
`RotateIn`, `RotateInDownLeft`, `RotateInDownRight`, `RotateInUpLeft`, `RotateInUpRight`

`RotateOut`, `RotateOutDownLeft`, `RotateOutDownRight`, `RotateOutUpLeft`, `RotateOutUpRight`

#### Slide
`SlideInLeft`, `SlideInRight`, `SlideInUp`, `SlideInDown`

`SlideOutLeft`, `SlideOutRight`, `SlideOutUp`, `SlideOutDown`

#### Zoom
`ZoomIn`, `ZoomInDown`, `ZoomInLeft`, `ZoomInRight`, `ZoomInUp`

`ZoomOut`, `ZoomOutDown`, `ZoomOutLeft`, `ZoomOutRight`, `ZoomOutUp`

### 效果图  
![animation](https://github.com/devzwy/KUtils/raw/master/images/animation.gif)  


## 自动隐藏输入框
------------------
#### 进入activity后调用即可
```Java
HideUtil.init(context);
```
#### 手动隐藏
```Java
//手动隐藏
HideUtil.hideSoftKeyboard(getActivity());
```




























## 集成该库
-----------

#### 1.通过jcenter仓库方式依赖
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
        jcenter()
        maven { url "https://jitpack.io" }//加入这一行代码
    }
}
```
##### 在APP的build.gradle dependencies节点下加入
```Java
compile 'com.github.zwy:kutils:+'
```
#### 2.clone项目到本地，将kutils库直接依赖到项目。
```Java
compile project(':kutils')
```
#### 3.aar方式依赖
##### 在项目根目录下新建aars文件夹，将kutils目录build/outputs下的kutils-release.aar文件copy进aars文件夹  在app的build.gradle 最外层节点加入
```xml
repositories { flatDir { dirs '../aars' } }
```
#####   在dependencies节点下加入对aar的依赖
```xml
compile(name: 'kutils-release', ext: 'aar') 
```
#### 无论采用哪种形式，都需要在application中初始化操作（注意：在使用库之前必须在application中执行如下代码。不初始化直接调用Log输出日志会有空指针异常抛出）
              
```Java
    @Override
      public void onCreate() {
          super.onCreate();
           KUtilLibs.init(true, TAG, this, new HttpBuild.Build(null, 10, HttpBuild.CookieType.MemoryCookieStore));
      }
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