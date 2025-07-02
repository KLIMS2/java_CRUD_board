package com.ysj.java.board.section.article.service;

import com.ysj.java.board.section.article.Article;
import com.ysj.java.board.section.article.repository.ArticleRepository;
import com.ysj.java.board.global.container.Container;

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

  public int findIndexById(int id)
  {
    return articleRepository.findIndexById(id);
  }

  public Article findArticleById(int id)
  {
    return articleRepository.findArticleById(id);
  }

  public void addArticle(String title, String content)
  {
    articleRepository.add(title, content);
  }

  public boolean isArticleEmpty()
  {
    return articleRepository.isArticleEmpty();
  }

  public Article getArticle(int id)
  {
    return articleRepository.get(id);
  }

  public List<Article> findAll(String searchKeword, String order)
  {
    return articleRepository.filteredArticleList(searchKeword, order);
  }

  public void setArticle(int id, String title, String content)
  {
    articleRepository.set(id, title, content);
  }

  public void removeArticle(int id)
  {
    articleRepository.remove(id);
  }
}
