package com.qianfeng.android_15_demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qianfeng.android_15_demo.Consts.Consts;
import com.qianfeng.android_15_demo.R;
import com.qianfeng.android_15_demo.adapter.MainRecyAdapter;
import com.qianfeng.android_15_demo.base.BaseActivity;
import com.qianfeng.android_15_demo.bean.Car;
import com.qianfeng.android_15_demo.bean.DbCache;
import com.qianfeng.android_15_demo.tools.LogU;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

import java.lang.reflect.Type;
import java.util.List;


public class MainActivity extends BaseActivity {

  private RecyclerView mRecyclerView;

  private List<Car> mArrayList;

  private MainRecyAdapter mMainRecyAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initRecyclerView();
    loadData();
    LogU.e("youmeng", getDeviceInfo(this));
  }


  public static String getDeviceInfo(Context context) {
    try {
      org.json.JSONObject json = new org.json.JSONObject();
      android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
          .getSystemService(Context.TELEPHONY_SERVICE);

      String device_id = tm.getDeviceId();

      android.net.wifi.WifiManager wifi =
          (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

      String mac = wifi.getConnectionInfo().getMacAddress();
      json.put("mac", mac);

      if (TextUtils.isEmpty(device_id)) {
        device_id = mac;
      }

      if (TextUtils.isEmpty(device_id)) {
        device_id =
            android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
      }

      json.put("device_id", device_id);

      return json.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected int setContentView() {
    return R.layout.activity_main;
  }


  /**
   * 初始化recycler
   */
  private void initRecyclerView() {
    mRecyclerView = (RecyclerView) findViewById(R.id.recy_main);
    mMainRecyAdapter = new MainRecyAdapter();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(linearLayoutManager);
  }

  /**
   * 加载数据
   *
   * @return
   */
  private void loadData() {

    setProgressBarV(true);


    OkHttpUtils.get().url(Consts.MAIN_URL).build().execute(new Callback<List<Car>>() {

      private String mContent;

      @Override
      public List<Car> parseNetworkResponse(Response response) throws Exception {
        Type type = new TypeToken<List<Car>>() {}.getType();
        mContent = response.body().string();
        return new Gson().fromJson(mContent, type);
      }

      /**
       * 从网络获取失败，从本地读取
       * 
       * @param call
       * @param e
       */

      @Override
      public void onError(Call call, Exception e) {

        LogU.e("onError", e.getMessage());

        Toast.makeText(MainActivity.this, Consts.NET_ERROR, Toast.LENGTH_SHORT).show();
        List<DbCache> execute =
            new Select().from(DbCache.class).where("url = ?", Consts.MAIN_URL).execute();

        // 从本地获取到有效数据了
        if (execute != null && execute.size() > 0) {
          DbCache db = execute.get(0);
          String json = db.getContent();
          Type type = new TypeToken<List<Car>>() {}.getType();
          List<Car> cars = new Gson().fromJson(json, type);
          onDataSuccese(cars);
        }

      }

      // 网络数据获取成功，进行本地存储
      @Override
      public void onResponse(List<Car> cars) {
        onDataSuccese(cars);
        DbCache db;
        // 看之前有没有对指定的url进行过缓存
        List<DbCache> execute =
            new Select().from(DbCache.class).where("url = ?", Consts.MAIN_URL).execute();
        // 之前对此url进行过存储
        if (execute != null && execute.size() > 0) {
          // 取出来老数据
          db = execute.get(0);
          // 重新赋值
          db.setContent(mContent);
          // 进行更新
          db.save();
        }
        // 对指定url之前并没有进行过缓存
        else {
          // 插入一条新数据
          db = new DbCache();
          db.setUrl(Consts.MAIN_URL);
          db.setContent(mContent);
          db.save();
        }
      }
    });
  }

  /**
   * 获取数据成功
   *
   * @param cars
   */

  private void onDataSuccese(List<Car> cars) {
    setProgressBarV(false);
    // /判空!!!
    if (cars == null) return;
    mMainRecyAdapter.setCars(cars);
    mRecyclerView.setAdapter(mMainRecyAdapter);
  }


}
