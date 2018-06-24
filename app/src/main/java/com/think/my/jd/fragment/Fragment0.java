package com.think.my.jd.fragment;

import android.view.View;

import com.think.my.jd.R;
import com.think.my.jd.base.BaseFragment;
import com.zxning.library.tool.UIUtils;

/**
 * Created by think on 2017/11/27.
 */

public class Fragment0 extends BaseFragment{
    @Override
    protected View onCreateSuccessedView() {
        View view =UIUtils.inflate(R.layout.fragment_0);
        return view;
    }
}
