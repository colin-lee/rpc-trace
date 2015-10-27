package com.github.trace.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面信息上报的bean对象，主要用于观察页面pv以及反爬虫的效果
 * <p/>
 * Created by colinli on 14-2-21.
 */
public class URIBean implements Serializable {
  /**
   * 当前时间戳
   */
  private long stamp;
  private String serverIp;
  /**
   * 业务名
   */
  private String app;
  /**
   * URI信息
   */
  private String uri;
  /**
   * 所有PV
   */
  private int totalPv;
  /**
   * 判定为spider访问的pv
   */
  private int spiderPv;
  /**
   * 返回码>400的pv
   */
  private int failPv;

  private int pv20x;
  private int pv30x;
  private int pv40x;
  private int pv50x;

  /**
   * 整体耗时
   */
  private long totalCost;

  public URIBean() {
  }

  private URIBean(Builder builder) {
    setStamp(builder.stamp);
    setApp(builder.app);
    setUri(builder.uri);
    setTotalPv(builder.totalPv);
    setSpiderPv(builder.spiderPv);
    setFailPv(builder.failPv);
    setTotalCost(builder.totalCost);
    setPv20x(builder.pv20x);
    setPv30x(builder.pv30x);
    setPv40x(builder.pv40x);
    setPv50x(builder.pv50x);
  }

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  public String getServerIp() {
    return serverIp;
  }

  public void setServerIp(String serverIp) {
    this.serverIp = serverIp;
  }

  public long getStamp() {
    return stamp;
  }

  public void setStamp(long stamp) {
    this.stamp = stamp;
  }

  @JSONField(serialize = false)
  public Date getStartTime() {
    return new Date(stamp);
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public int getTotalPv() {
    return totalPv;
  }

  public void setTotalPv(int totalPv) {
    this.totalPv = totalPv;
  }

  public int getSpiderPv() {
    return spiderPv;
  }

  public void setSpiderPv(int spiderPv) {
    this.spiderPv = spiderPv;
  }

  public int getFailPv() {
    return failPv;
  }

  public void setFailPv(int failPv) {
    this.failPv = failPv;
  }

  public int getPv20x() {
    return pv20x;
  }

  public void setPv20x(int pv20x) {
    this.pv20x = pv20x;
  }

  public int getPv30x() {
    return pv30x;
  }

  public void setPv30x(int pv30x) {
    this.pv30x = pv30x;
  }

  public int getPv40x() {
    return pv40x;
  }

  public void setPv40x(int pv40x) {
    this.pv40x = pv40x;
  }

  public int getPv50x() {
    return pv50x;
  }

  public void setPv50x(int pv50x) {
    this.pv50x = pv50x;
  }

  public long getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(long totalCost) {
    this.totalCost = totalCost;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(32);
    sb.append("URIBean{uri='").append(uri).append('\'');
    sb.append(", totalPv=").append(totalPv);
    sb.append('}');
    return sb.toString();
  }

  public static final class Builder {
    private long stamp;
    private String app;
    private String uri;
    private int totalPv;
    private int spiderPv;
    private int failPv;
    private int pv20x;
    private int pv30x;
    private int pv40x;
    private int pv50x;
    private long totalCost;

    public Builder() {
      stamp = System.currentTimeMillis();
    }

    public Builder(URIBean copy) {
      stamp = copy.stamp;
      app = copy.app;
      uri = copy.uri;
      totalPv = copy.totalPv;
      spiderPv = copy.spiderPv;
      failPv = copy.failPv;
      pv20x = copy.pv20x;
      pv30x = copy.pv30x;
      pv40x = copy.pv40x;
      pv50x = copy.pv50x;
      totalCost = copy.totalCost;
    }

    public Builder stamp(long stamp) {
      this.stamp = stamp;
      return this;
    }

    public Builder uri(String uri) {
      this.uri = uri;
      return this;
    }

    public Builder app(String app) {
      this.app = app;
      return this;
    }

    public Builder totalPv(int totalPv) {
      this.totalPv = totalPv;
      return this;
    }

    public Builder spiderPv(int spiderPv) {
      this.spiderPv = spiderPv;
      return this;
    }

    public Builder failPv(int failPv) {
      this.failPv = failPv;
      return this;
    }

    public Builder totalCost(long totalCost) {
      this.totalCost = totalCost;
      return this;
    }

    public Builder pv20x(int i) {
      this.pv20x = i;
      return this;
    }

    public Builder pv30x(int i) {
      this.pv30x = i;
      return this;
    }

    public Builder pv40x(int i) {
      this.pv40x = i;
      return this;
    }

    public Builder pv50x(int i) {
      this.pv50x = i;
      return this;
    }

    public URIBean build() {
      return new URIBean(this);
    }
  }
}
