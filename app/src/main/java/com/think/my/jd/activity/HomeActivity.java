package com.think.my.jd.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.think.my.jd.R;
import com.think.my.jd.factory.FragmentFactory;

public class HomeActivity extends AppCompatActivity {

    protected FrameLayout contentFl;
    protected RadioGroup rgBottom;
    private final String[] tabNames = new String[]{"页面", "存储", "网络", "我的", "好的"};
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        contentFl = (FrameLayout) findViewById(R.id.content_fl);
        rgBottom = (RadioGroup) findViewById(R.id.rg_bottom);

        rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb0:
                        index = 0;
                        break;
                    case R.id.rb1:
                        index = 1;
                        break;
                    case R.id.rb2:
                        index = 2;
                        break;
                    case R.id.rb3:
                        index = 3;
                        break;
                    case R.id.rb4:
                        index = 4;
                        break;
                }
                Fragment fragment = (Fragment) fragmentStatePagerAdapter.instantiateItem(contentFl, index);
                //替换方法3,需要去替换的fragment对象
                fragmentStatePagerAdapter.setPrimaryItem(null, 0, fragment);
                //提交操作
                fragmentStatePagerAdapter.finishUpdate(null);
            }
        });
        rgBottom.check(R.id.rb0);//选中首页.
    }

    //创建fragment的数据适配器
    FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    };
}
