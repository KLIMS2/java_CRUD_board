package com.ysj.java.board;

import com.ysj.java.board.article.Article;
import com.ysj.java.board.global.Rq;
import com.ysj.java.board.test.Test;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
  static List<Article> makeTestArticles()
  {
    List<Article> testArticleList = new ArrayList<>();

    IntStream.rangeClosed(1, 100).forEach(
        a -> testArticleList.add(new Article("title" + a, "content" + a)));

    System.out.printf("test data %d개\n", testArticleList.size());

    return testArticleList;
  }

  public static void main(String[] args) {
    Test test = new Test();

    System.out.println("--> 자바 게시판 시작 <--");
    Scanner sc = new Scanner(System.in);
    String input;
    List<Article> articleList = makeTestArticles();

    while(true)
    {
      System.out.print("명령) "); input = sc.nextLine();
      Rq rq = new Rq(input);

      if(rq.getUrlPath().equals("exit")) // exit
      {
        System.out.println("실행 종료");
        break;
      }
      else if(rq.getUrlPath().equals("/usr/article/write")) // write
      {
        System.out.println("> 게시물 생성");
        String title; String content;

        System.out.print("제목: "); title = sc.nextLine();
        if(title.trim().isEmpty())
        {
          System.out.println("제목을 입력해주세요.");
          continue;
        }

        System.out.print("내용: "); content = sc.nextLine();
        if(content.trim().isEmpty())
        {
          System.out.println("내용을 입력해주세요.");
          continue;
        }

        Article article = new Article(title, content);
        articleList.add(article);

        System.out.println(article + " 게시물이 생성");
      }
      else if(rq.getUrlPath().equals("/usr/article/detail")) // detail
      {
        if( !(rq.getParams().containsKey("id")) )
        {
          System.out.println("id를 입력해주세요.");
          continue;
        }

        int id;
        try
        {
          id = Integer.parseInt(rq.getParams().get("id"));
        }
        catch(NumberFormatException e)
        {
          System.out.println("id에 정수를 입력해주세요.");
          continue;
        }

        if(articleList.isEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        int index = -1;
        for(int a = 0; a < articleList.size(); a++)
        {
          if(articleList.get(a).id == id)
          {
            index = a;
            break;
          }
        }

        if(index == -1)
        {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        }
        else
        {
          System.out.println("> 게시물 상세");

          Article detailArticle = articleList.get(index);

          System.out.printf("id: %d\n", detailArticle.id);
          System.out.printf("title: %s\n", detailArticle.title);
          System.out.printf("content: %s\n", detailArticle.content);
        }
      }
      else if(rq.getUrlPath().equals("/usr/article/list")) // list
      {
        Map<String, String> params = rq.getParams();
        String order = "idDesc";

        if(params.containsKey("orderBy"))
        {
          order = params.get("orderBy");
        }

        if( !(order.equals("idAsc") || order.equals("idDesc")) )
        {
          System.out.println("잘못된 정렬방식입니다.");
          continue;
        }

        String searchKeyword = "";

        if(params.containsKey("searchKeyword"))
        {
          searchKeyword = params.get("searchKeyword");
        }

        System.out.println("> 게시물 리스트");

        if(articleList.isEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        List<Article> filteredList = new ArrayList<>();

         for(Article article : articleList)
         {
           if(article.title.contains(searchKeyword) || article.content.contains(searchKeyword))
           {
             filteredList.add(article);
           }
         }

        if(order.equals("idAsc"))
        {
          System.out.println("오래된순\n(id | title)");

          for(Article article : filteredList)
          {
            System.out.printf("%d | %s\n", article.id, article.title);
          }
//          filteredList.forEach(article -> System.out.printf("%d | %s\n", article.id, article.title));
        }
        else
        {
          System.out.println("최신순\n(id | title)");

          for(int a = filteredList.size() - 1; a >= 0; a--)
          {
            Article article = filteredList.get(a);
            System.out.printf("%d | %s\n", article.id, article.title);
          }
        }
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")) // modify
      {
        Map<String, String> params = rq.getParams();

        if( !(params.containsKey("id")) )
        {
          System.out.println("id를 입력해주세요.");
          continue;
        }

        int id;
        try
        {
          id = Integer.parseInt(params.get("id"));
        }
        catch(NumberFormatException e)
        {
          System.out.println("id에 정수를 입력해주세요.");
          continue;
        }

        if(articleList.isEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        int index = -1;
        for(int a = 0; a < articleList.size(); a++)
        {
          if(articleList.get(a).id == id)
          {
            index = a;
            break;
          }
        }

        if(index == -1)
        {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        }
        else
        {
          System.out.printf("> %d번 게시물 수정\n", id);

          String title; String content;

          System.out.print("제목: "); title = sc.nextLine();
          if(title.trim().isEmpty())
          {
            System.out.println("제목을 입력해주세요.");
            continue;
          }

          System.out.print("내용: "); content = sc.nextLine();
          if(content.trim().isEmpty())
          {
            System.out.println("내용을 입력해주세요.");
            continue;
          }

          articleList.get(index).title = title;
          articleList.get(index).content = content;

          System.out.println(articleList.get(index) + " 게시물이 수정됨");
        }
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")) // delete
      {
        Map<String, String> params = rq.getParams();

        if( !(params.containsKey("id")) )
        {
          System.out.println("id를 입력해주세요.");
          continue;
        }

        int id;
        try
        {
          id = Integer.parseInt(params.get("id"));
        }
        catch(NumberFormatException e)
        {
          System.out.println("id에 정수를 입력해주세요.");
          continue;
        }

        if(articleList.isEmpty())
        {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        int index = -1;
        for(int a = 0; a < articleList.size(); a++)
        {
          if(articleList.get(a).id == id)
          {
            index = a;
            break;
          }
        }

        if(index == -1)
        {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        }
        else
        {
          articleList.remove(index);
          System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
        }
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