package com.qianfeng.android_15_demo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.qianfeng.android_15_demo.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by dupengfei on 16/1/25.
 * 功能1是管理2方法抽取
 *
 * 1appbar
 * 2正在加载
 */
public abstract class BaseActivity extends AppCompatActivity {

  private Toolbar mToolbar;

  private LinearLayout mLinearLayout;

  private ProgressBar mProgressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_base);

    // 相关方法省略
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mProgressBar = (ProgressBar) findViewById(R.id.progress);

    initChildView();

  }


  public void onResume() {
    super.onResume();
    // 友盟
    MobclickAgent.onResume(this);

  }

  public void onPause() {
    super.onPause();

    // 友盟
    MobclickAgent.onPause(this);
  }

  private void initChildView() {
    // 装载子activity的布局容器
    mLinearLayout = (LinearLayout) findViewById(R.id.line_base);
    // 加载子activity布局
    mLinearLayout.addView(View.inflate(this, setContentView(), null));

  }

  /**
   * 让子类实现，子类提供自己的xml
   * 
   * @return
   */
  protected abstract int setContentView();

  /**
   * 开放给子类
   * 设置正在加载显隐
   * 
   * @param isV
   */
  protected void setProgressBarV(boolean isV) {
    mProgressBar.setVisibility(isV ? View.VISIBLE : View.GONE);
  }
}
