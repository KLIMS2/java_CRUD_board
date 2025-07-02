package com.ysj.java.board.global.session;

import java.util.HashMap;
import java.util.Map;

public class Session
{
  private Map<String, Object> sessionData;

  public Session()
  {
    sessionData = new HashMap<>();
  }

  public void setAttribute(String key, Object object)
  {
    sessionData.put(key, object);
  }

  public Object getAttribute(String key)
  {
    return sessionData.get(key);
  }

  public void removeAttribute(String key)
  {
    sessionData.remove(key);
  }

  public boolean hasAttribute(String key)
  {
    return sessionData.containsKey(key);
  }
}
