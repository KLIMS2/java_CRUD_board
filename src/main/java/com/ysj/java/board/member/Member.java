package com.ysj.java.board.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member
{
  String loginId;
  String password;
  String name;
}
