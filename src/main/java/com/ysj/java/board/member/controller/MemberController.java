package com.ysj.java.board.member.controller;

import com.ysj.java.board.global.Controller;

public class MemberController implements Controller
{
  @Override
  public void doAction()
  {
    else if(rq.getUrlPath().equals("/usr/member/join")) // member/join
    {
      String loginId; String password; String name;

      System.out.print("로그인 아이디: "); input = sc.nextLine();

      if(input.trim().isEmpty())
      {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      loginId = input;

      System.out.print("로그인 비밀번호: "); input = sc.nextLine();

      if(input.trim().isEmpty())
      {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      password = input;

      while(true)
      {
        System.out.print("로그인 비밀번호 확인: "); input = sc.nextLine();

        if( !(input.equals(password)) )
        {
          System.out.println("비밀번호와 일치하지 않습니다.");
          continue;
        }

        break;
      }

      System.out.print("이름: "); input = sc.nextLine();

      if(input.trim().isEmpty())
      {
        System.out.println("이름을 입력해주세요.");
        continue;
      }

      name = input;

      System.out.printf("%s님 회원 가입되었습니다.\n", loginId);
    }
    else if(rq.getUrlPath().equals("/usr/member/login")) // member/login
    {

    }
  }
}
