package com.ysj.java.board.app;

import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.controller.Controller;
import com.ysj.java.board.global.interceptor.NeedLoginInterceptor;
import com.ysj.java.board.global.interceptor.NeedLogoutInterceptor;
import com.ysj.java.board.global.request.Rq;
import com.ysj.java.board.test.Test;

import java.util.Scanner;

public class App
{
  public void run()
  {
    Test test = new Test();

    System.out.println("--> 자바 게시판 시작 <--");
    final String DEFAULT_PROMPT = "명령";

    Rq rq = Container.rq;
    Scanner sc = Container.sc;
    NeedLoginInterceptor interceptor_login = Container.needLoginInterceptor;
    NeedLogoutInterceptor interceptor_logout = Container.needLogoutInterceptor;

    String command; String prompt = DEFAULT_PROMPT;

    while(true)
    {
      System.out.printf("%s) ", prompt); command = sc.nextLine();
      rq.setURL(command); rq.run();
      Controller controller = rq.getController();

      if( !(interceptor_login.run() && interceptor_logout.run()) )
      {
        continue;
      }

      if(controller != null)
      {
        controller.doAction();

        if(rq.isLogined())
        {
          prompt = (String) rq.getSessionAttr("logined");
        }
        else
        {
          prompt = DEFAULT_PROMPT;
        }
      }
      else if(rq.getUrlPath().equals("exit")) // exit
      {
        System.out.println("실행 종료");
        break;
      }
      else
      {
        System.out.println("잘못된 명령어입니다.");
      }
    }

    sc.close();
    System.out.println("--> 자바 게시판 끝 <--");
  }
}