package com.ysj.java.board.global;

import java.util.Map;

public class Rq {
  String URL;
  Map<String, String> params;
  String urlPath;

  public Rq(String URL) {
    this.URL = URL;
    params = Util.getParamsFromURL(URL);
    urlPath = Util.getUrlPathFromURL(URL);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}
