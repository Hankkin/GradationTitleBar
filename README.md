# GradationTitleBar
仿QQ空间标题栏渐变
###look at the screenshot:

<img src="http://img.blog.csdn.net/20160803211804429" width = "320" height = "640" alt="高仿微信群聊头像" align=center />

###xml
```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.hankkin.gradationtitlebar.QQSpeakActivity">

    <com.hankkin.gradationscroll.GradationScrollView
        android:id="@+id/scrollview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="none">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical" >
            <ImageView
                android:id="@+id/iv_banner"
                android:scaleType="fitXY"
                android:src="@drawable/banner3"
                android:layout_width="match_parent"
                android:layout_height="200dp" />
            <com.hankkin.gradationscroll.NoScrollListview
                android:id="@+id/listview"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" >
            </com.hankkin.gradationscroll.NoScrollListview>
        </LinearLayout>
    </com.hankkin.gradationscroll.GradationScrollView>
    <TextView
        android:paddingBottom="10dp"
        android:id="@+id/textview"
        android:layout_width="match_parent"
        android:layout_height="55dp"
        android:gravity="center|bottom"
        android:text="我是标题"
        android:textSize="18sp"
        android:textColor="@color/transparent"
        android:background="#00000000" />
</RelativeLayout>
```
###获取顶部图片高度后，设置滚动监听
```java
    private void initListeners() {

        ViewTreeObserver vto = ivBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = ivBanner.getHeight();

                scrollView.setScrollViewListener(QQSpeakActivity.this);
            }
        });
    }
```
###滑动监听,设置透明度
```java
@Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            textView.setBackgroundColor(Color.argb((int) 0, 144,151,166));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            textView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
        } else {    //滑动到banner下面设置普通颜色
            textView.setBackgroundColor(Color.argb((int) 255, 144,151,166));
        }
    }
```
###博客地址
---------------------------
<http://blog.csdn.net/lyhhj/article/details/52107851>

###License
