package com.qianfeng.android_15_demo.tools;

import android.util.Log;

import com.qianfeng.android_15_demo.cofigure.AppConfigure;

/**
 * Created by dupengfei on 16/1/25.
 */
public class LogU {

  public static void e(String tag, String content) {

    if (AppConfigure.DEGUG)
      Log.e(tag, content);
  }
}
