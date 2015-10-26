package com.github.trace.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面访问
 * Created by lirui on 2015-10-24 18:41.
 */
public class AccessBean implements Serializable{
  private long stamp;
  private int cost;
  private int code;
  private int size;
  private String traceId;
  private String rpcId;
  private String clientIp;
  private String serverIp;
  private String url;
  private String referer;
  private String userAgent;
  private String cookie;
  private String profile;
  private String uid;
  private String extra;

  public long getStamp() {
    return stamp;
  }

  public void setStamp(long stamp) {
    this.stamp = stamp;
  }

  public Date getStartTime() {
    return new Date(stamp);
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public String getRpcId() {
    return rpcId;
  }

  public void setRpcId(String rpcId) {
    this.rpcId = rpcId;
  }

  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public String getServerIp() {
    return serverIp;
  }

  public void setServerIp(String serverIp) {
    this.serverIp = serverIp;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }
}
