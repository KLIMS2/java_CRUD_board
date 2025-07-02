package com.ysj.java.board.global.interceptor;

import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.request.Rq;

public class NeedLogoutInterceptor implements Interceptor
{
  @Override
  public boolean run()
  {
    Rq rq = Container.rq;

    if( !(rq.isLogined()) )
    {
      return true;
    }
    else
    {
      return switch (rq.getUrlPath()) {
        case "/usr/member/join",
             "/usr/member/login" -> {
          System.out.println("로그아웃을 해야 이용할 수 있습니다.");
          yield false;
        }

        default -> true;
      };
    }
  }
}