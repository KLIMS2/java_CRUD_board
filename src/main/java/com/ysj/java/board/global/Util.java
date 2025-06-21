package com.ysj.java.board.global;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
  static Map<String, String> getParamsFromURL(String URL) {
    Map<String, String> params = new LinkedHashMap<>();

    String[] queryString = URL.trim().split("\\?", 2);
    if (queryString.length == 1) {
      return params;
    }
    String[] queryParts = queryString[1].trim().split("&");

    for (String queryPart : queryParts) {
      String[] querys = queryPart.trim().split("=", 2);
      if (querys.length == 1) {
        continue;
      }
      params.put(querys[0], querys[1]);
    }

    return params;
  }

  static String getUrlPathFromURL(String URL) {
    String[] queryString = URL.trim().split("\\?", 2);
    return queryString[0];
  }
}
