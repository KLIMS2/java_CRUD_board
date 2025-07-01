package com.ysj.java.board.member.controller;

import com.ysj.java.board.global.Container;
import com.ysj.java.board.global.Controller;
import com.ysj.java.board.global.Rq;
import com.ysj.java.board.global.Session;
import com.ysj.java.board.member.Member;
import com.ysj.java.board.member.service.MemberService;

import java.util.Scanner;

public class MemberController implements Controller
{
  private MemberService memberService;

  public MemberController()
  {
    memberService = Container.memberService;

    memberService.makeTestData(5); //
  }

  @Override
  public void doAction()
  {
    Rq rq = Container.rq;
    Scanner sc = Container.sc;
    String urlPath = rq.getUrlPath();

    switch(urlPath)
    {
      case "/usr/member/join": // join
        doJoin(rq, sc);
        break;

      case "/usr/member/login": // login
        doLogin(rq, sc);
        break;

      case "/usr/member/logout": // logout
        doLogout(rq, sc);
        break;

      case "/usr/member/myPage": // myPage
        doMyPage(rq, sc);
        break;

      default:
        System.out.println("잘못된 명령어입니다.");
        break;
    }
  }

  public void doJoin(Rq rq, Scanner sc)
  {
    String id; String password; String passwordVerify; String name;

    while(true)
    {
      System.out.print("로그인 아이디: "); id = sc.nextLine().trim();

      if(id.isEmpty())
      {
        System.out.println("로그인 아이디를 입력해주세요.");
      }
      else
      {
        if(memberService.isIdDuplicated(id))
        {
          System.out.println("아이디가 중복되었습니다.");
        }
        else
        {
          break;
        }
      }
    }

    while(true)
    {
      System.out.print("로그인 비밀번호: "); password = sc.nextLine().trim();

      if(password.isEmpty())
      {
        System.out.println("로그인 비밀번호를 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    while(true)
    {
      System.out.print("로그인 비밀번호 확인: "); passwordVerify = sc.nextLine().trim();

      if( !(passwordVerify.equals(password)) )
      {
        System.out.println("비밀번호와 일치하지 않습니다.");
      }
      else
      {
        break;
      }
    }

    while(true)
    {
      System.out.print("이름: "); name = sc.nextLine().trim();

      if(name.isEmpty())
      {
        System.out.println("이름을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    memberService.memberJoin(id, password, name);

    System.out.printf("%s님 회원 가입 되었습니다.\n", id);
  }

  public void doLogin(Rq rq, Scanner sc)
  {
    Member loginMember;
    String id; String password;

    while(true)
    {
      System.out.print("아이디: "); id = sc.nextLine().trim();

      if(id.isEmpty())
      {
        System.out.println("아이디를 입력해주세요.");
      }
      else
      {
        loginMember = memberService.findById(id);

        if(loginMember == null)
        {
          System.out.printf("%s는 존재하지 않는 회원입니다.\n", id);
        }
        else
        {
          break;
        }
      }
    }

    while(true)
    {
      System.out.print("비밀번호: "); password = sc.nextLine().trim();

      if(password.isEmpty())
      {
        System.out.println("비밀번호를 입력해주세요.");
      }
      else
      {
        if( !(password.equals(loginMember.getPassword())) )
        {
          System.out.println("비밀번호가 틀렸습니다.");
        }
        else
        {
          break;
        }
      }
    }

    rq.setSessionAttr("logined", id);

    System.out.printf("%s님 로그인 되었습니다.\n", id);
  }

  public void doLogout(Rq rq, Scanner sc)
  {

  }

  public void doMyPage(Rq rq, Scanner sc)
  {

  }
}
