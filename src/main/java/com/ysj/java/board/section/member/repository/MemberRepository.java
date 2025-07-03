package com.ysj.java.board.section.member.repository;

import com.ysj.java.board.section.member.Member;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class MemberRepository
{
  private List<Member> memberList;

  public MemberRepository()
  {
    memberList = new LinkedList<>();
  }

  public void join(String id, String password, String name)
  {
    Member newMember = new Member(id, password, name, "normal");
    memberList.add(newMember);
  }

  public boolean isIdDuplicated(String id)
  {
    boolean rs = false;

    for(Member member : memberList)
    {
      if(member.getId().equals(id))
      {
        rs = true;
      }
    }

    return rs;
  }

  public Member findById(String id)
  {
    Member findMember = null;

    for(Member member : memberList)
    {
      if(member.getId().equals(id))
      {
        findMember = member;
      }
    }

    return findMember;
  }

  public void makeTestData(int num)
  {
    memberList.add(new Member("admin", "admin", "admin", "admin"));

    IntStream.rangeClosed(1, num).forEach(
        a -> memberList.add(new Member(
            "test" + a, "asdf" + a, "usr" + a, "normal")));

    System.out.printf("member test data %d개\n", memberList.size());
  }
}
