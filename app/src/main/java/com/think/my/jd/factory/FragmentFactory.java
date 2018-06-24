package com.think.my.jd.factory;

import android.support.v4.app.Fragment;

import com.think.my.jd.fragment.Fragment0;
import com.think.my.jd.fragment.Fragment1;
import com.think.my.jd.fragment.Fragment2;
import com.think.my.jd.fragment.Fragment3;
import com.think.my.jd.fragment.Fragment4;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {
    //Map<Integer,Fragment>
    private static Map<Integer, Fragment> fragmentMap = new HashMap<Integer, Fragment>();

    /**
     * 根据
     *
     * @param intId
     * @return
     */
    public static Fragment createFragment(int intId) {//position
       Fragment fragment = fragmentMap.get(intId);
        if (fragment != null) {
            return fragment;
        } else {
            switch (intId) {
                case 0:
                    //首页
                    fragment = new Fragment0();
                    break;
                case 1:
                    //财富.wealth
                    fragment = new Fragment1();
                    break;
                case 2:
                    fragment = new Fragment2();
                    break;
                case 3:
                    fragment = new Fragment3();
                    break;
                case 4:
                    //页面第一页.
                    fragment = new Fragment4();
                    break;
//                case 5:
//                    //页面第一页.
//                    fragment = new Fragment5();
//                    break;
            }
            fragmentMap.put(intId, fragment);
        }
        return fragment;
    }

    public static void clearAllFragment() {//position
        fragmentMap.clear();
    }

}
