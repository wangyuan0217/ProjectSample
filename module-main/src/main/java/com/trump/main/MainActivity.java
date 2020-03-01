package com.trump.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.router.ActivityJumper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/main/home")
public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.tv)
    public void click(View view) {
        ActivityJumper.toPersonnalInfo(this);
    }

}
