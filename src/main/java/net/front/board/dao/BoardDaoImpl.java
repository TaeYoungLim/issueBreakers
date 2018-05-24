package net.front.board.dao;

import java.util.ArrayList;

import net.admin.board.vo.BoardVo;
import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;

public class BoardDaoImpl implements BoardDao {

	private final String queryPath = "/net/front/board/query/BoardQuery.xml";
	
	private CommonDao commonDao;
	
	public BoardDaoImpl() {
		commonDao = CommonDao.getInstance();
	}
	
	@Override
	public ArrayList<Object> list(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/list");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResult(BoardVo.class.getName());
	}
	
	@Override
	public int listCount(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listCount");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultOneInt("count");
	}

	@Override
	public ArrayList<Object> selectOne(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/selectOne");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResult(BoardVo.class.getName());
	}
	
	@Override
	public int insert(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/insert");
		commonDao.setPreparedStatement(query, boardVo, "board_id");
		return commonDao.getResultUpdateForKey();
	}

	@Override
	public int update(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/update");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultUpdate();
	}

	@Override
	public int delete(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/delete");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultUpdate();
	}
	
	@Override
	public int updateSelection(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/updateSelection");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultUpdate();
	}
	
	@Override
	public int updateVote(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/updateVote");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultUpdate();
	}
	
	@Override
	public ArrayList<Object> listByWriter(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listByWriter");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResult(BoardVo.class.getName());
	}
	
	@Override
	public int listCountByWriter(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listCountByWriter");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultOneInt("count");
	}
	
	@Override
	public ArrayList<Object> listSearch(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listSearch");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResult(BoardVo.class.getName());
	}
	
	@Override
	public int listCountSearch(BoardVo boardVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listCountSearch");
		commonDao.setPreparedStatement(query, boardVo);
		return commonDao.getResultOneInt("count");
	}
}
