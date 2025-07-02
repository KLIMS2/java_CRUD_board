package com.ysj.java.board.test;

import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.request.Rq;

import java.util.Map;

public class Test {
  public Test() {
    System.out.println("--> 테스트 시작 <--");

    String URL = "/usr/article/list?page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";
    Rq rq = Container.rq; rq.setURL(URL); rq.run();
    Map<String, String> params = rq.getParams();
    System.out.println(params);
    String UrlPath = rq.getUrlPath();
    System.out.println(UrlPath);

    System.out.println("--> 테스트 끝 <--\n");
  }
}
