package com.ysj.java.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("--> 자바 게시판 시작 <--");
    Scanner sc = new Scanner(System.in);
    String input; int id = 0;

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

        Article article = new Article(++id, title, content);

        System.out.println(article + " 게시물이 생성");
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
  int id;
  String title;
  String content;

  Article(int id, String title, String content)
  {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString()
  {
    return "{id: %d, 제목: %s, 내용: %s}".formatted(id, title, content);
  }
}