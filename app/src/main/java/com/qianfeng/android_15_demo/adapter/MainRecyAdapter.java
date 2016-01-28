package com.qianfeng.android_15_demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.android_15_demo.R;
import com.qianfeng.android_15_demo.bean.Car;
import com.qianfeng.android_15_demo.tools.DateFormatU;
import com.qianfeng.android_15_demo.tools.LogU;
import com.qianfeng.android_15_demo.viewholder.MainRecyViewHolder;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

import java.util.List;

/**
 * Created by dupengfei on 16/1/25.
 */
public class MainRecyAdapter extends RecyclerView.Adapter<MainRecyViewHolder> {

  private List<Car> mCars;

  private Context mContext;


  public void setCars(List<Car> cars) {
    mCars = cars;
  }

  /**
   * 生成一个可以复用的viewholder
   * 
   * @param parent
   * @param viewType
   * @return
   */
  @Override
  public MainRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    mContext = parent.getContext();
    View view = View.inflate(parent.getContext(), R.layout.item_mainrecy, null);
    MainRecyViewHolder mainRecyViewHolder = new MainRecyViewHolder(view);
    return mainRecyViewHolder;
  }

  @Override
  public void onBindViewHolder(final MainRecyViewHolder holder, int position) {

    holder.getTextViewTitle().setText(mCars.get(position).getTitle());

    String publishTime = mCars.get(position).getPublishTime();
    String text = DateFormatU.formatDate(publishTime);
    holder.getTextViewPublishTime().setText(text);

    Picasso.with(mContext).load(mCars.get(position).getFirstPicUrl()).into(holder.getImageView());
  }

  @Override
  public int getItemCount() {
    return mCars.size();
  }
}
