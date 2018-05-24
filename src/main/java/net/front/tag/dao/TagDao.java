package net.front.tag.dao;

import java.util.ArrayList;

import net.front.boardComment.vo.BoardCommentVo;
import net.front.tag.vo.TagVo;

public interface TagDao {
	public ArrayList<Object> list();
	public ArrayList<Object> listByBoardId(TagVo tagVo);
	public ArrayList<Object> selectOne(TagVo tagVo);
	public int insert(TagVo tagVo);
	public int update(TagVo tagVo);
	public int delete(TagVo tagVo);
}
