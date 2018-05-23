package net.front.board.dao;

import java.util.ArrayList;

import net.admin.board.vo.BoardVo;
import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;
import net.front.member.vo.MemberVo;

public class BoardDaoImpl implements BoardDao {

	private final String queryPath = "/net/front/board/query/BoardQuery.xml";
	
	@Override
	public ArrayList<Object> list(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/list");
		return new CommonDao(query, boardVo).getResult(BoardVo.class.getName());
	}
	
	@Override
	public int listCount(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listCount");
		return new CommonDao(query, boardVo).getResultOneInt("count");
	}

	@Override
	public ArrayList<Object> selectOne(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/selectOne");
		return new CommonDao(query, boardVo).getResult(BoardVo.class.getName());
	}
	
	@Override
	public int insert(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/insert");
		return new CommonDao(query, boardVo).getResultUpdate();
	}

	@Override
	public int update(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/update");
		return new CommonDao(query, boardVo).getResultUpdate();
	}

	@Override
	public int delete(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/delete");
		return new CommonDao(query, boardVo).getResultUpdate();
	}
}
