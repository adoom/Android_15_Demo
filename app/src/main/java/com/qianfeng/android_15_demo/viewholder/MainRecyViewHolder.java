package com.qianfeng.android_15_demo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.android_15_demo.R;

/**
 * Created by dupengfei on 16/1/25.
 */
public class MainRecyViewHolder extends RecyclerView.ViewHolder {

  private TextView mTextViewTitle;

  private TextView mTextViewPublishTime;

  private ImageView mImageView;

  public TextView getTextViewTitle() {
    return mTextViewTitle;
  }

  public void setTextViewTitle(TextView textViewTitle) {
    mTextViewTitle = textViewTitle;
  }

  public TextView getTextViewPublishTime() {
    return mTextViewPublishTime;
  }

  public void setTextViewPublishTime(TextView textViewPublishTime) {
    mTextViewPublishTime = textViewPublishTime;
  }

  public ImageView getImageView() {
    return mImageView;
  }

  public void setImageView(ImageView imageView) {
    mImageView = imageView;
  }

  /**
   * 构造
   * 初始化各个小控件
   * 
   * @param itemView
   */
  public MainRecyViewHolder(View itemView) {
    super(itemView);

    mTextViewTitle = (TextView) itemView.findViewById(R.id.text_itle_item_mainrecy);
    mTextViewPublishTime = (TextView) itemView.findViewById(R.id.text_publictime_item_mainrecy);
    mImageView = (ImageView) itemView.findViewById(R.id.img_item_mainrecy);
  }


}
