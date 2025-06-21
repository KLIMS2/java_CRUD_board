package com.ysj.java.board.test;

import com.ysj.java.board.global.Rq;

import java.util.Map;

public class Test {
  public Test() {
    String URL = "/usr/article/list?page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";
    Rq rq = new Rq(URL);
    Map<String, String> params = rq.getParams();
    System.out.println(params);
    String UrlPath = rq.getUrlPath();
    System.out.println(UrlPath);
  }
}
