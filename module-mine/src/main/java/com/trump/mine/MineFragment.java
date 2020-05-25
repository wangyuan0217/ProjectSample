package com.trump.mine;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.ui.base.fragment.BaseFragment;
import com.trump.library_common.ui.base.view.IBasePresenter;

/**
 * @author 王元_Trump
 * @time 2020/03/26 16:42
 * @desc
 */
@Route(path = "/mine/fragment")
public class MineFragment extends BaseFragment {
    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment_mine;
    }
}
