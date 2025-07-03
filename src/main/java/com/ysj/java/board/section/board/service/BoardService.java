package com.ysj.java.board.section.board.service;

import com.ysj.java.board.global.container.Container;
import com.ysj.java.board.section.board.Board;
import com.ysj.java.board.section.board.repository.BoardRepository;

import java.util.List;

public class BoardService
{
  private BoardRepository boardRepository;

  public BoardService()
  {
    boardRepository = Container.boardRepository;
  }

  public void addBoard(String name, String type)
  {
    boardRepository.add(name, type);
  }

  public Board findById(int id)
  {
    return boardRepository.findById(id);
  }

  public boolean isBoardEmpty()
  {
    return boardRepository.isBoardEmpty();
  }

  public List<Board> findBoardList()
  {
    return boardRepository.findBoardList();
  }

  public void makeTestData(int num)
  {
    boardRepository.makeTestData(num);
  }
}
