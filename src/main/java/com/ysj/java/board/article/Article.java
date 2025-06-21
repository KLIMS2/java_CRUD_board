package com.ysj.java.board.article;

public class Article {
  static int lastID = 0;
  public int id;
  public String title;
  public String content;

  public Article(String title, String content) {
    this.id = ++lastID;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, 제목: %s, 내용: %s}".formatted(id, title, content);
  }
}
