package com.ysj.java.board;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
  static List<Article> makeTestArticles()
  {
    List<Article> testArticleList = new ArrayList<>();

    IntStream.rangeClosed(1, 5).forEach(
        a -> testArticleList.add(new Article("title" + a, "content" + a)));

    System.out.println("test data 5개");

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

      if(input.equals("exit"))
      {
        System.out.println("실행 종료");
        break;
      }
      else if(input.equals("/usr/article/write"))
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
      else if(input.equals("/usr/article/detail"))
      {
        System.out.println("> 게시물 상세");

        Article lastArticle = articleList.get(articleList.size() - 1);

        if(lastArticle == null)
        {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.printf("id: %d\n", lastArticle.id);
        System.out.printf("title: %s\n", lastArticle.title);
        System.out.printf("content: %s\n", lastArticle.content);
      }
      else if(input.equals("/usr/article/list"))
      {
        System.out.println("> 게시물 리스트");

        if(articleList.isEmpty())
        {
          System.out.println("게시물이 없습니다.");
          continue;
        }

        System.out.println("최신순: 0\n오래된순: 1");
        System.out.print("입력: ");
        input = sc.nextLine();

        if(input.equals("0"))
        {
          System.out.println("최신순\n(id | title)");

          for(int a = articleList.size() - 1; a >= 0; a--)
          {
            Article article = articleList.get(a);
            System.out.printf("%d | %s\n", article.id, article.title);
          }
        }
        else if(input.equals("1"))
        {
          System.out.println("오래된순\n(id | title)");

          for(Article article : articleList)
          {
            System.out.printf("%d | %s\n", article.id, article.title);
          }
//          articleList.forEach(article -> System.out.printf("%d | %s\n", article.id, article.title));
        }
        else
        {
          System.out.println("잘못된 입력입니다.");
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

class Article
{
  static int lastID = 0;
  int id;
  String title;
  String content;

  Article(String title, String content)
  {
    this.id = ++lastID;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString()
  {
    return "{id: %d, 제목: %s, 내용: %s}".formatted(id, title, content);
  }
}

class Test
{
  Test()
  {
    String URL = "/usr/article/list?page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";
    Map<String, String> params = Util.getParamsFromURL(URL);
    System.out.println(params);
  }
}

class Util
{
  static Map<String, String> getParamsFromURL(String URL)
  {
    Map<String, String> params = new LinkedHashMap<>();

    String[] queryString = URL.trim().split("\\?", 2);
    if(queryString.length == 1)
    {
      return params;
    }
    String[] queryParts = queryString[1].trim().split("&");

    for(String queryPart : queryParts)
    {
      String[] querys = queryPart.trim().split("=", 2);
      if(querys.length == 1)
      {
        continue;
      }
      params.put(querys[0], querys[1]);
    }

    return params;
  }
}