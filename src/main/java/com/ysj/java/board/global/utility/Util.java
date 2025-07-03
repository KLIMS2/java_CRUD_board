package com.ysj.java.board.global.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
  public static Map<String, String> getParamsFromURL(String URL) {
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

  public static String getUrlPathFromURL(String URL) {
    String[] queryString = URL.trim().split("\\?", 2);
    return queryString[0];
  }

  public static String getNowDateStr()
  {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String date = now.format(formatter);
    return date;
  }

  public static int mapping(int start, int end, int n, int num)
  {
    int rs = -1;
    int interval = (end - start + 1) / n;

    if(interval * n < num)
    {
      rs = n;
    }

    for(int a = 1; a <= n; a++)
    {
      if(start + interval * (a - 1) <= num && num <= interval * a)
      {
        rs = a;
      }
    }

    return rs;
  }
}
