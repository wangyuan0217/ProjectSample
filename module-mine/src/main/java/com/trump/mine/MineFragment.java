package com.trump.mine;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.ui.base.fragment.BaseFragment;
import com.trump.mine.databinding.MineFragmentMineBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/03/26 16:42
 * @desc
 */
@Route(path = "/mine/fragment")
public class MineFragment extends BaseFragment<MineFragmentMineBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment_mine;
    }

    @Override
    protected void initListener() {
        Disposable disposable = RxView.clicks(getBinding().text)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> ActivityJumper.toUserInfo());
    }
}
