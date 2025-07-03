package com.ysj.java.board.section.article.repository;

import com.ysj.java.board.section.article.Article;
import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.utility.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository
{
  private List<Article> articleList;

  public ArticleRepository()
  {
    articleList = new ArrayList<>();
  }

  public void makeTestArticles(int num)
  {
    IntStream.rangeClosed(1, num).forEach(
        a -> {
          String writeDate = Util.getNowDateStr();
          String modifyDate = writeDate;
          int boardID = Util.mapping(1, num, 3, a);

          articleList.add(new Article(
              "title" + a,
              "content" + a,
              "test" + a,
              "usr" + a,
              writeDate, modifyDate, boardID, "board" + boardID));
        });

    System.out.printf("article test data %d개\n", articleList.size());
  }

  public void add(String title, String content, int boardId, String boardName)
  {
    String writerID = Container.rq.getLoginedMember().getId();
    String writerName = Container.rq.getLoginedMember().getName();
    String writeDate = Util.getNowDateStr();
    String modifyDate = writeDate;

    Article newArticle = new Article(title, content, writerID, writerName,
        writeDate, modifyDate, boardId, boardName);
    articleList.add(newArticle);
  }

  public Article get(int id)
  {
    int index = findIndexById(id);

    if(articleList.isEmpty() || index >= articleList.size() || index < 0)
    {
      return null;
    }

    return articleList.get(index);
  }

  public void set(int id, String title, String content)
  {
    int index = findIndexById(id);

    if(articleList.isEmpty() || index == -1)
    {
      return;
    }

    Article findArticle = findArticleById(id);
    String writerID = Container.rq.getLoginedMember().getId();
    String writerName = Container.rq.getLoginedMember().getName();
    String writeDate = findArticle.getWriteDate();
    String modifyDate = Util.getNowDateStr();
    int boardID = findArticle.getBoardID();
    String boardName = findArticle.getBoardName();


    Article article = new Article(title, content, writerID, writerName,
        writeDate, modifyDate, boardID, boardName);
    article.setId(id); Article.setLastID(Article.getLastID() - 1);
    articleList.set(index, article);
  }

  public void remove(int id)
  {
    Article findArticle = findArticleById(id);

    if(articleList.isEmpty() || findArticle == null)
    {
      return;
    }

    articleList.remove(findArticle);
  }

  public boolean isArticleEmpty()
  {
    return articleList.isEmpty();
  }

  public int findIndexById(int id)
  {
    int index = -1;

    for(int a = 0; a < articleList.size(); a++)
    {
      if(articleList.get(a).getId() == id)
      {
        index = a;
        break;
      }
    }

    return index;
  }

  public Article findArticleById(int id)
  {
    int index = findIndexById(id);

    if(index == -1)
    {
      return null;
    }

    return articleList.get(index);
  }

  public List<Article> filteredArticleList(String searchKeyword, String order)
  {
    // search
    List<Article> filteredArticleList = articleList.stream().
        filter(article -> article.getTitle().contains(searchKeyword) ||
        article.getContent().contains(searchKeyword)).
        collect(Collectors.toList());

    // sorting
    if(order.equals("idAsc"))
    {
      filteredArticleList.sort((a, b) -> a.getId() - b.getId());
    }
    else if(order.equals("idDesc"))
    {
      filteredArticleList.sort((a, b) -> b.getId() - a.getId());
    }

    return filteredArticleList;
  }
}
