package com.ysj.java.board.article.controller;

import com.ysj.java.board.article.Article;
import com.ysj.java.board.article.service.ArticleService;
import com.ysj.java.board.global.Container;
import com.ysj.java.board.global.Controller;
import com.ysj.java.board.global.Rq;

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
    final int DEFAULT = -1;

    Rq rq = Container.rq;
    String urlPath = rq.getUrlPath();
    Scanner sc = Container.sc;

    switch(urlPath)
    {
      case "/usr/article/write": // write
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
        System.out.println(articleService.getLastId() + "번 게시물이 생성됨");
        break;

      case "/usr/article/detail": // detail
        if(articleService.isArticleEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          break;
        }

        int id = rq.getIntParam("id", DEFAULT);
        int index = articleService.findById(id);

        if(id == DEFAULT)
        {
          System.out.println("id에 정수를 입력해주세요.");
        }
        else if(index == DEFAULT)
        {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        }
        else
        {
          System.out.println("> 게시물 상세");

          Article detailArticle = articleService.getArticle(index);

          System.out.printf("id: %d\n", detailArticle.getId());
          System.out.printf("title: %s\n", detailArticle.getTitle());
          System.out.printf("content: %s\n", detailArticle.getContent());
        }
        break;

      case "/usr/article/list": // list
        if(articleService.isArticleEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          break;
        }

        String order = rq.getParam("orderBy", "idDesc");
        String searchKeyword = rq.getParam("searchKeyword", "");

        if( !(order.equals("idAsc") || order.equals("idDesc")) )
        {
          System.out.println("잘못된 정렬방식입니다.");
          break;
        }

        System.out.println("> 게시물 리스트");

        System.out.println("(id | title)");
        List<Article> articles = articleService.findAll(searchKeyword, order);
        articles.forEach(article ->
            System.out.printf("%d: %s\n", article.getId(), article.getTitle()));
        break;

      case "/usr/article/modify": // modify
        if(articleService.isArticleEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          break;
        }

        id = rq.getIntParam("id", DEFAULT);
        index = Container.articleService.findById(id);

        if(id == DEFAULT)
        {
          System.out.println("id에 정수를 입력해주세요.");
        }
        else if(index == DEFAULT)
        {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        }
        else
        {
          System.out.printf("> %d번 게시물 수정\n", id);

          Article article;

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

          articleService.setArticle(index, id, title, content);
          System.out.println(id + "번 게시물이 수정됨");
        }
        break;

      case "/usr/article/delete": // delete
        if(articleService.isArticleEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          break;
        }

        id = rq.getIntParam("id", DEFAULT);
        index = Container.articleService.findById(id);

        if(id == DEFAULT)
        {
          System.out.println("id를 정수로 입력해 주세요");
        }
        else if(index == DEFAULT)
        {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        }
        else
        {
          articleService.removeArticle(index);
          System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
        }
        break;

      default:
        System.out.println("잘못된 명령어입니다.");
        break;
    }
  }
}
