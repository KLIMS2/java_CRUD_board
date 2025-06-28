package com.ysj.java.board.article.repository;

import com.ysj.java.board.article.Article;

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
    List<Article> testArticleList = new ArrayList<>();

    IntStream.rangeClosed(1, 100).forEach(
        a -> testArticleList.add(new Article("title" + a, "content" + a)));

    System.out.printf("test data %d개\n", testArticleList.size());

    articleList = testArticleList;
  }

  public void add(String title, String content)
  {
    Article newArticle = new Article(title, content);
    articleList.add(newArticle);
  }

  public Article get(int index)
  {
    if(articleList.isEmpty() || index >= articleList.size() || index < 0)
    {
      return null;
    }

    return articleList.get(index);
  }

  public void set(int index, int id, String title, String content)
  {
    if(articleList.isEmpty() || index >= articleList.size() || index < 0)
    {
      return;
    }

    Article article = new Article(title, content);
    article.setId(id); Article.setLastID(Article.getLastID() - 1);
    articleList.set(index, article);
  }

  public void remove(int index)
  {
    if(articleList.isEmpty() || index >= articleList.size() || index < 0)
    {
      return;
    }

    articleList.remove(index);
  }

  public boolean isArticleEmpty()
  {
    return articleList.isEmpty();
  }

  public int findById(int id)
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

  public int getLastId()
  {
    return Article.getLastID();
  }
}
