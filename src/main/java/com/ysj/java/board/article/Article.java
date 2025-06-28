package com.ysj.java.board.article;

import lombok.Data;

@Data
public class Article {
  static private int lastID;
  private int id;
  private String title;
  private String content;

  static
  {
    lastID = 0;
  }

  public Article(String title, String content) {
    this.id = ++lastID;
    this.title = title;
    this.content = content;
  }

  public static void setLastID(int lastID)
  {
    Article.lastID = lastID;
  }

  public static int getLastID()
  {
    return lastID;
  }

  @Override
  public String toString() {
    return "{id: %d, 제목: %s, 내용: %s}".formatted(id, title, content);
  }
}
