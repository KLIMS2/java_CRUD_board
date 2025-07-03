package com.ysj.java.board.global.request;

import com.ysj.java.board.global.session.Session;
import com.ysj.java.board.global.utility.Util;
import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.controller.Controller;
import com.ysj.java.board.section.member.Member;

import java.util.Map;

public class Rq {
  private String URL;
  private Map<String, String> params;
  private String urlPath;
  private Session session;

  public Rq()
  {
    session = Container.session;
  }

  public void setURL(String URL)
  {
    this.URL = URL;
  }

  public void run()
  {
    params = Util.getParamsFromURL(URL);
    urlPath = Util.getUrlPathFromURL(URL);
  }

  public String getUrlPath() {
    return urlPath;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public int getIntParam(String paramName, int defaultValue)
  {
    if( !(params.containsKey(paramName)) )
    {
      return defaultValue;
    }

    try
    {
      return Integer.parseInt(params.get(paramName));
    }
    catch(NumberFormatException e)
    {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue)
  {
    if(params.containsKey(paramName))
    {
      return params.get(paramName);
    }

    return defaultValue;
  }

  public Controller getController()
  {
    String[] urlPaths = urlPath.trim().split("/");

    if(urlPaths.length < 3)
    {
      return null;
    }

    if(urlPaths[2].equals("article"))
    {
      return Container.articleController;
    }
    else if(urlPaths[2].equals("member"))
    {
      return Container.memberController;
    }
    else if(urlPaths[2].equals("board"))
    {
      return Container.boardController;
    }
    else
    {
      return null;
    }
  }

  public void setSessionAttr(String attr, Object object)
  {
    session.setAttribute(attr, object);
  }

  public Object getSessionAttr(String attr)
  {
    return session.getAttribute(attr);
  }

  public void removeSessionAttr(String attr)
  {
    session.removeAttribute(attr);
  }

  public boolean hasSessionAttr(String attr)
  {
    return session.hasAttribute(attr);
  }

  public boolean isLogined()
  {
    return session.hasAttribute("logined");
  }

  public Member getLoginedMember()
  {
    String id = (String) getSessionAttr("logined");
    Member loginedMember = Container.memberService.findById(id);
    return loginedMember;
  }
}