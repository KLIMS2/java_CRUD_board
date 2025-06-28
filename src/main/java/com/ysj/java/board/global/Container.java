package com.ysj.java.board.global;

import com.ysj.java.board.article.controller.ArticleController;
import com.ysj.java.board.article.repository.ArticleRepository;
import com.ysj.java.board.article.service.ArticleService;
import com.ysj.java.board.member.controller.MemberController;
import com.ysj.java.board.member.repository.MemberRepository;
import com.ysj.java.board.member.service.MemberService;

import java.util.Scanner;

public class Container
{
  static public Scanner sc;
  static public Rq rq;

  static public ArticleRepository articleRepository;
  static public ArticleService articleService;
  static public ArticleController articleController;

  static public MemberRepository memberRepository;
  static public MemberService memberService;
  static public MemberController memberController;

  static
  {
    sc = new Scanner(System.in);
    rq = null;

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    articleController = new ArticleController();

    memberRepository = new MemberRepository();
    memberService = new MemberService();
    memberController = new MemberController();
  }
}
