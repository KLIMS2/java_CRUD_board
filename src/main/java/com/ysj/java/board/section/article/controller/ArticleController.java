package com.ysj.java.board.section.article.controller;

import com.ysj.java.board.section.article.Article;
import com.ysj.java.board.section.article.service.ArticleService;
import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.controller.Controller;
import com.ysj.java.board.global.request.Rq;

import java.util.List;
import java.util.Scanner;

public class ArticleController implements Controller
{
  private ArticleService articleService;

  public ArticleController()
  {
    articleService = Container.articleService;
    articleService.makeTestArticles(100); //
  }

  @Override
  public void doAction()
  {
    Rq rq = Container.rq;
    Scanner sc = Container.sc;
    String urlPath = rq.getUrlPath();

    switch(urlPath)
    {
      case "/usr/article/write": // write
        doWrite(rq, sc);
        break;

      case "/usr/article/detail": // detail
        doDetail(rq, sc);
        break;

      case "/usr/article/list": // list
        doList(rq, sc);
        break;

      case "/usr/article/modify": // modify
        doModify(rq, sc);
        break;

      case "/usr/article/delete": // delete
        doDelete(rq, sc);
        break;

      default:
        System.out.println("잘못된 명령어입니다.");
        break;
    }
  }

  public void doWrite(Rq rq, Scanner sc)
  {
    System.out.println("> 게시물 생성");
    String title; String content;

    while(true)
    {
      System.out.print("제목: "); title = sc.nextLine();

      if(title.trim().isEmpty())
      {
        System.out.println("제목을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    while(true)
    {
      System.out.print("내용: "); content = sc.nextLine();

      if(content.trim().isEmpty())
      {
        System.out.println("내용을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    articleService.addArticle(title, content);

    System.out.println(Article.getLastID() + "번 게시물이 생성됨");
  }

  public void doDetail(Rq rq, Scanner sc)
  {
    if(articleService.isArticleEmpty())
    {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    final int DEFAULT = -1;
    int id = rq.getIntParam("id", DEFAULT);

    if(id == DEFAULT)
    {
      System.out.println("id에 정수를 입력해주세요.");
      return;
    }

    Article findArticle = articleService.findArticleById(id);

    if(findArticle == null)
    {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.println("> 게시물 상세");

    Article detailArticle = articleService.getArticle(id);

    System.out.printf("id: %d\n", detailArticle.getId());
    System.out.printf("title: %s\n", detailArticle.getTitle());
    System.out.printf("writer: %s(%s)\n", detailArticle.getWriterID(), detailArticle.getWriterName());
    System.out.printf("write date: %s\n", detailArticle.getWriteDate());
    System.out.printf("modify date: %s\n", detailArticle.getModifyDate());
    System.out.printf("content: %s\n", detailArticle.getContent());
  }

  public void doList(Rq rq, Scanner sc)
  {
    if(articleService.isArticleEmpty())
    {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    String order = rq.getParam("orderBy", "idDesc");
    String searchKeyword = rq.getParam("searchKeyword", "");

    if( !(order.equals("idAsc") || order.equals("idDesc")) )
    {
      System.out.println("잘못된 정렬방식입니다.");
      return;
    }

    List<Article> articles = articleService.findAll(searchKeyword, order);

    System.out.printf("> 게시물 리스트 (총 %d개)\n", articles.size());

    System.out.println("(id | title | writer)");
    articles.forEach(article ->
        System.out.printf("%d: %s (%s)\n", article.getId(), article.getTitle(), article.getWriterID()));
  }

  public void doModify(Rq rq, Scanner sc)
  {
    if(articleService.isArticleEmpty())
    {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    final int DEFAULT = -1;
    int id = rq.getIntParam("id", DEFAULT);

    if(id == DEFAULT)
    {
      System.out.println("id에 정수를 입력해주세요.");
      return;
    }

    Article findArticle = articleService.findArticleById(id);

    if(findArticle == null)
    {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    if( !(rq.getLoginedMember().getId().equals(findArticle.getWriterID())) )
    {
      System.out.println("다른 사람의 게시물은 수정할 수 없습니다.");
      return;
    }

    System.out.printf("> %d번 게시물 수정\n", id);

    String title; String content;

    while(true)
    {
      System.out.print("제목: "); title = sc.nextLine();

      if(title.trim().isEmpty())
      {
        System.out.println("제목을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    while(true)
    {
      System.out.print("내용: "); content = sc.nextLine();

      if(content.trim().isEmpty())
      {
        System.out.println("내용을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    articleService.setArticle(id, title, content);

    System.out.println(id + "번 게시물이 수정됨");
  }

  public void doDelete(Rq rq, Scanner sc)
  {
    if(articleService.isArticleEmpty())
    {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    final int DEFAULT = -1;
    int id = rq.getIntParam("id", DEFAULT);

    if(id == DEFAULT)
    {
      System.out.println("id에 정수를 입력해주세요.");
      return;
    }

    Article findArticle = articleService.findArticleById(id);

    if(findArticle == null)
    {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    if( !(rq.getLoginedMember().getId().equals(findArticle.getWriterID())) )
    {
      System.out.println("다른 사람의 게시물은 수정할 수 없습니다.");
      return;
    }

    articleService.removeArticle(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}