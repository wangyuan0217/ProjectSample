package com.trump.main;

import com.trump.library_common.ui.base.fragment.BaseFragment;
import com.trump.library_common.ui.base.view.IBasePresenter;

/**
 * @author 王元_Trump
 * @time 2020/03/26 16:42
 * @desc
 */
public class MainItemFragment extends BaseFragment {
    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_main_item;
    }
}
