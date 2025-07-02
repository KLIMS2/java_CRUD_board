package com.ysj.java.board.global.interceptor;

import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.request.Rq;

public class NeedLoginInterceptor implements Interceptor
{
  @Override
  public boolean run()
  {
    Rq rq = Container.rq;

    if(rq.isLogined())
    {
      return true;
    }
    else
    {
      return switch (rq.getUrlPath()) {
        case "/usr/article/write",
             "/usr/article/modify",
             "/usr/article/delete",
             "/usr/member/logout",
             "/usr/member/myPage" -> {
          System.out.println("로그인을 해야 이용할 수 있습니다.");
          yield false;
        }

        default -> true;
      };
    }
  }
}
