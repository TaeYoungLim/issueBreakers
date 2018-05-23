package net.front.board.dao;

import java.util.ArrayList;

import net.admin.board.vo.BoardVo;

public interface BoardDao {
	public ArrayList<Object> list(BoardVo boardVo);
	public int listCount(BoardVo boardVo);
	public ArrayList<Object> selectOne(BoardVo boardVo);
	public int insert(BoardVo boardVo);
	public int update(BoardVo boardVo);
	public int delete(BoardVo boardVo);
}
