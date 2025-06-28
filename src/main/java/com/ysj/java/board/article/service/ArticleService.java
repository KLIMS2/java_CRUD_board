package com.ysj.java.board.article.service;

import com.ysj.java.board.article.Article;
import com.ysj.java.board.article.repository.ArticleRepository;
import com.ysj.java.board.global.Container;

import java.util.List;

public class ArticleService
{
  private ArticleRepository articleRepository;

  public ArticleService()
  {
    articleRepository = Container.articleRepository;
  }

  public void makeTestArticles(int num)
  {
    articleRepository.makeTestArticles(num);
  }

  public int findById(int id)
  {
    return articleRepository.findById(id);
  }

  public void addArticle(String title, String content)
  {
    articleRepository.add(title, content);
  }

  public boolean isArticleEmpty()
  {
    return articleRepository.isArticleEmpty();
  }

  public Article getArticle(int index)
  {
    return articleRepository.get(index);
  }

  public List<Article> findAll(String searchKeword, String order)
  {
    return articleRepository.filteredArticleList(searchKeword, order);
  }

  public void setArticle(int index, int id, String title, String content)
  {
    articleRepository.set(index, id, title, content);
  }

  public void removeArticle(int index)
  {
    articleRepository.remove(index);
  }

  public int getLastId()
  {
    return articleRepository.getLastId();
  }
}
