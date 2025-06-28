package com.ysj.java.board.global;

import java.util.Map;

public class Rq {
  private String URL;
  private Map<String, String> params;
  private String urlPath;

  public Rq(String URL) {
    this.URL = URL;
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
    else
    {
      return null;
    }
  }
}