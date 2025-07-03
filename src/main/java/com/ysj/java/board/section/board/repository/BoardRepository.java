package com.ysj.java.board.section.board.repository;

import com.ysj.java.board.section.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BoardRepository
{
  private List<Board> boardList;

  public BoardRepository()
  {
    boardList = new ArrayList<>();
  }

  public void add(String name, String type)
  {
    Board newBoard = new Board(name, type);

    boardList.add(newBoard);
  }

  public Board findById(int id)
  {
    Board findBoard = null;

    for(Board board : boardList)
    {
      if(board.getId() == id)
      {
        findBoard = board;
      }
    }

    return findBoard;
  }

  public boolean isBoardEmpty()
  {
    return boardList.isEmpty();
  }

  public List<Board> findBoardList()
  {
    return boardList;
  }

  public void makeTestData(int num)
  {
    IntStream.rangeClosed(1, num).forEach(
        a -> boardList.add(new Board("board" + a, "type" + a)));
  }
}
