package com.ysj.java.board.app;

import com.ysj.java.board.global.Container;
import com.ysj.java.board.global.Controller;
import com.ysj.java.board.global.Rq;
import com.ysj.java.board.test.Test;

import java.util.Scanner;

public class App
{
  public void run()
  {
    Test test = new Test();

    System.out.println("--> 자바 게시판 시작 <--");
    Scanner sc = Container.sc;
    String input;

    while(true)
    {
      System.out.print("명령) "); input = sc.nextLine();
      Rq rq = new Rq(input); Container.rq = rq;
      Controller controller = rq.getController();

      if(controller != null)
      {
        controller.doAction();
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