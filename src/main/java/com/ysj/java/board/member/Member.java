package com.ysj.java.board.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member
{
  private String id;
  private String password;
  private String name;
}
