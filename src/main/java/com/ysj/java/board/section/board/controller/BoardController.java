package com.ysj.java.board.section.board.controller;

import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.global.controller.Controller;
import com.ysj.java.board.global.request.Rq;
import com.ysj.java.board.section.article.Article;
import com.ysj.java.board.section.board.Board;
import com.ysj.java.board.section.board.service.BoardService;

import java.util.List;
import java.util.Scanner;

public class BoardController implements Controller
{
  private BoardService boardService;

  public BoardController()
  {
    boardService = Container.boardService;

    boardService.makeTestData(3); //
  }

  @Override
  public void doAction()
  {
    Rq rq = Container.rq;
    Scanner sc = Container.sc;
    String urlPath = rq.getUrlPath();

    switch (urlPath)
    {
      case "/usr/board/write": // write
        doWrite(rq, sc);
        break;

      case "/usr/board/list": // list
        doList(rq, sc);
        break;

      default:
        System.out.println("잘못된 명령어입니다.");
        break;
    }
  }

  public void doWrite(Rq rq, Scanner sc)
  {
    System.out.println("> 게시판 생성");

    String name; String type;

    while(true)
    {
      System.out.print("게시판 이름: "); name = sc.nextLine().trim();

      if(name.isEmpty())
      {
        System.out.println("게시판 이름을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    while(true)
    {
      System.out.print("게시판 유형: "); type = sc.nextLine().trim();

      if(type.isEmpty())
      {
        System.out.println("게시판 유형을 입력해주세요.");
      }
      else
      {
        break;
      }
    }

    boardService.addBoard(name, type);

    System.out.printf("%s 게시판이 생성되었습니다.\n", name);
  }

  public void doList(Rq rq, Scanner sc)
  {
    if(boardService.isBoardEmpty())
    {
      System.out.println("게시판이 존재하지 않습니다.");
      return;
    }

    List<Board> findBoardList = boardService.findBoardList();

    System.out.printf("> 게시판 리스트 (총 %d개)\n", findBoardList.size());

    System.out.println("(id | name | type)");
    findBoardList.forEach(board ->
        System.out.printf("%d: %s (%s)\n", board.getId(), board.getName(), board.getType()));
  }
}
