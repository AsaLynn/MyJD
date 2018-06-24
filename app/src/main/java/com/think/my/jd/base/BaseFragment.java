package com.think.my.jd.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by think on 2017/11/27.
 */

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return BaseFragment.this.onCreateSuccessedView();
    }

    //BaseFragment中依然是不知道子界面的展示效果,所以抽象,让其放到子类中去处理
    protected abstract View onCreateSuccessedView();

    @Override
    public void setMenuVisibility(boolean menuVisible) {

        //如果当前view是可见的,则menuVisible就是true
        //每一个子类fragment对应view是否存在的操作
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
        super.setMenuVisibility(menuVisible);
    }


}
