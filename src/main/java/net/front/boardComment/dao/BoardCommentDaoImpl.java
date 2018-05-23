package net.front.boardComment.dao;

import java.util.ArrayList;

import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;
import net.front.boardComment.vo.BoardCommentVo;

public class BoardCommentDaoImpl implements BoardCommentDao {

	private final String queryPath = "/net/front/boardComment/query/BoardCommentQuery.xml";
	
	@Override
	public ArrayList<Object> list(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/list");
		return new CommonDao(query, boardCommentVo).getResult(BoardCommentVo.class.getName());
	}

	@Override
	public ArrayList<Object> selectOne(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/selectOne");
		return new CommonDao(query, boardCommentVo).getResult(BoardCommentVo.class.getName());
	}
	
	@Override
	public int insert(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/insert");
		return new CommonDao(query, boardCommentVo).getResultUpdate();
	}

	@Override
	public int update(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/update");
		return new CommonDao(query, boardCommentVo).getResultUpdate();
	}

	@Override
	public int delete(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/delete");
		return new CommonDao(query, boardCommentVo).getResultUpdate();
	}
}
