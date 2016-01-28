package com.qianfeng.android_15_demo.bean;

import java.io.Serializable;

/**
 * Created by dupengfei on 16/1/25.
 */
public class Car implements Serializable {

  /**
   * PublishTime : 2016-01-25T09:33:00.0000000+08:00
   * Title : AMG GT高性能版谍照
   * ID : 6579258
   * FirstPicUrl :
   * http://img1.bitautoimg.com/wapimg-66-44/bitauto/news/2016/1/201612592054767_320.jpg
   */

  private String PublishTime;
  private String Title;
  private int ID;
  private String FirstPicUrl;

  public void setPublishTime(String PublishTime) {
    this.PublishTime = PublishTime;

  }

  public void setTitle(String Title) {
    this.Title = Title;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public void setFirstPicUrl(String FirstPicUrl) {
    this.FirstPicUrl = FirstPicUrl;
  }

  public String getPublishTime() {
    return PublishTime;
  }

  public String getTitle() {
    return Title;
  }

  public int getID() {
    return ID;
  }

  public String getFirstPicUrl() {
    return FirstPicUrl;
  }
}
