package net.front.boardComment.dao;

import java.util.ArrayList;

import net.front.boardComment.vo.BoardCommentVo;

public interface BoardCommentDao {
	public ArrayList<Object> list(BoardCommentVo boardCommentVo);
	public ArrayList<Object> selectOne(BoardCommentVo boardCommentVo);
	public int insert(BoardCommentVo boardCommentVo);
	public int update(BoardCommentVo boardCommentVo);
	public int delete(BoardCommentVo boardCommentVo);
}
