package com.ysj.java.board.global.container;

import com.ysj.java.board.section.article.controller.ArticleController;
import com.ysj.java.board.section.article.repository.ArticleRepository;
import com.ysj.java.board.section.article.service.ArticleService;
import com.ysj.java.board.global.interceptor.NeedLoginInterceptor;
import com.ysj.java.board.global.interceptor.NeedLogoutInterceptor;
import com.ysj.java.board.global.request.Rq;
import com.ysj.java.board.global.session.Session;
import com.ysj.java.board.section.member.controller.MemberController;
import com.ysj.java.board.section.member.repository.MemberRepository;
import com.ysj.java.board.section.member.service.MemberService;

import java.util.Scanner;

public class Container
{
  static public Scanner sc;
  static public Session session;
  static public Rq rq;

  static public NeedLoginInterceptor needLoginInterceptor;
  static public NeedLogoutInterceptor needLogoutInterceptor;

  static public ArticleRepository articleRepository;
  static public ArticleService articleService;
  static public ArticleController articleController;

  static public MemberRepository memberRepository;
  static public MemberService memberService;
  static public MemberController memberController;

  static
  {
    sc = new Scanner(System.in);
    session = new Session();
    rq = new Rq();

    needLoginInterceptor = new NeedLoginInterceptor();
    needLogoutInterceptor = new NeedLogoutInterceptor();

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    articleController = new ArticleController();

    memberRepository = new MemberRepository();
    memberService = new MemberService();
    memberController = new MemberController();
  }
}
