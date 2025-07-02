package com.ysj.java.board.section.article;

import lombok.Data;

@Data
public class Article {
  static private int lastID;
  private int id;
  private String title;
  private String content;
  private String writerID;
  private String writerName;
  private String writeDate;
  private String modifyDate;

  static
  {
    lastID = 0;
  }

  public Article(String title, String content, String writerID, String writerName, String writeDate, String modifyDate) {
    this.id = ++lastID;
    this.title = title;
    this.content = content;
    this.writerID = writerID;
    this.writerName = writerName;
    this.writeDate = writeDate;
    this.modifyDate = modifyDate;
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
