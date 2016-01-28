package com.qianfeng.android_15_demo.base;

import android.text.TextUtils;

import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.qianfeng.android_15_demo.bean.DbCache;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dupengfei on 16/1/25.
 * <p/>
 * 1减少每次都创建一个解析gson的自定义callback做parseNetworkResponse方法
 */
public abstract class BaseCallBack<T> extends Callback<T> {

  private Type mType;

  private Class<T> mTClass;

  private String mUrl;

  private String content;

  /**
   * 应对jsonobject
   * 没有url，不需要保存
   *
   * @param TClass
   */
  public BaseCallBack(Class<T> TClass) {
    mTClass = TClass;
  }

  /**
   * 应对list类型，jsonarray
   * 不需要保存
   *
   * @param type
   */
  public BaseCallBack(Type type) {

    mType = type;

  }

  /**
   * 应对jsonobject
   * 需要保存
   *
   * @param TClass
   */
  public BaseCallBack(Class<T> TClass, String url) {
    mTClass = TClass;
    mUrl = url;
  }

  /**
   * 应对list类型，jsonarray
   * 需要保存
   *
   * @param type
   */
  public BaseCallBack(Type type, String url) {

    mType = type;
    mUrl = url;

  }

  /**
   * gson解析
   *
   * @param response
   * @return
   * @throws Exception
   */
  @Override
  public T parseNetworkResponse(Response response) throws Exception {
    content = response.body().string();
    if (mType != null) {
      // jsonarray
      return new Gson().fromJson(content, mType);

    }
    if (mTClass != null) {
      // jsonobject
      return new Gson().fromJson(content, mTClass);
    }
    return null;
  }

  // 从服务器拿到数据之后存储到本地
  @Override
  public void onResponse(T t) {

    // 从网络获取成功后保存到本地
    if (!TextUtils.isEmpty(mUrl)) {
      saveDb();
    }
    onSuccese(t);

  }

  private void saveDb() {
    DbCache dbCache;
    List<DbCache> execute = new Select().from(DbCache.class).where("url = ?", mUrl).execute();
    // 从本地查到了数据并且数量大于零，证明之前已经有过缓存，走更新逻辑
    if (execute != null && execute.size() > 0) {
      dbCache = execute.get(0);
      dbCache.setContent(content);
      // update更新
      dbCache.save();
    }
    // 新增
    else {
      dbCache = new DbCache();
      dbCache.setUrl(mUrl);
      dbCache.setContent(content);
      dbCache.save();
    }
  }


  /**
   * 没有网络的情况下，接口异常
   * 
   * @param call
   * @param e
   */
  @Override
  public void onError(Call call, Exception e) {

    if (TextUtils.isEmpty(mUrl)) return;
    // 从本地数据库获取一组DbCache对象
    List<DbCache> execute = new Select().from(DbCache.class).where("url = ?", mUrl).execute();
    // 有可能之前并没有对指定url进行缓存，通过where("url = ?", mUrl)
    if (execute == null) {
      onNullData();
      return;
    }

    // 获取第一条数据
    DbCache dbCache = execute.get(0);
    // content就是json字符串
    String content = dbCache.getContent();
    // 通过json转实体类
    if (mType != null)
    {
      T t = new Gson().fromJson(content, mType);
      onSucceseLocal(t);
    }

    if (mTClass != null) {
      {
        T t = new Gson().fromJson(content, mTClass);
        onSucceseLocal(t);
      }
    }

  }

  /**
   * 网络获取成功
   *
   * @param t
   */
  protected abstract void onSuccese(T t);

  /**
   * 网络获取失败但是本地有数据
   *
   * @param t
   */
  protected abstract void onSucceseLocal(T t);


  /**
   * 网络和本地都没数据
   */
  protected abstract void onNullData();
}
