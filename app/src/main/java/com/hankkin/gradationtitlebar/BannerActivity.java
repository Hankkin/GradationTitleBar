package com.hankkin.gradationtitlebar;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hankkin.gradationscroll.GradationScrollView;
import com.hankkin.gradationscroll.MaterialIndicator;
import com.hankkin.gradationscroll.StatusBarUtil;

public class BannerActivity extends AppCompatActivity  implements GradationScrollView.ScrollViewListener {
    private GradationScrollView scrollView;
    private ListView listView;
    private TextView textView;
    private int imageHeight;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setImgTransparent(this);
        setContentView(R.layout.activity_banner);

        scrollView = (GradationScrollView) findViewById(R.id.scrollview);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textview);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();

        MaterialIndicator indicator = (MaterialIndicator) findViewById(R.id.indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(indicator);
        indicator.setAdapter(viewPager.getAdapter());

        initListeners();
        initData();
    }

    /**
     * viewpager适配器
     */
    private  class MyPagerAdapter extends PagerAdapter {
        public int[] drawables = {R.drawable.banner1
                ,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(drawables[position]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {

        ViewTreeObserver vto = viewPager.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = viewPager.getHeight();

                scrollView.setScrollViewListener(BannerActivity.this);
            }
        });
    }



    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BannerActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }


    /**
     * 滑动监听
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            textView.setBackgroundColor(Color.argb((int) 0, 144,151,166));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            textView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
        } else {    //滑动到banner下面设置普通颜色
            textView.setBackgroundColor(Color.argb((int) 255, 144,151,166));
        }
    }
}
