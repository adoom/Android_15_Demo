package com.qianfeng.android_15_demo.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by dupengfei on 16/1/25.
 */
@Table(name = "dbcache")
public class DbCache extends Model {

  @Column
  private String url;
  @Column
  private String content;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
