package com.ysj.java.board.member.service;

import com.ysj.java.board.global.Container;
import com.ysj.java.board.member.Member;
import com.ysj.java.board.member.repository.MemberRepository;

public class MemberService
{
  private MemberRepository memberRepository;

  public MemberService()
  {
    memberRepository = Container.memberRepository;
  }

  public void memberJoin(String id, String password, String name)
  {
    memberRepository.join(id, password, name);
  }

  public boolean isIdDuplicated(String id)
  {
    return memberRepository.isIdDuplicated(id);
  }

  public Member findById(String id)
  {
    return memberRepository.findById(id);
  }

  public void makeTestData(int num)
  {
    memberRepository.makeTestData(num);
  }
}
