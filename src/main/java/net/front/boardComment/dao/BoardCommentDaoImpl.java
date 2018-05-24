package net.front.boardComment.dao;

import java.util.ArrayList;

import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;
import net.front.boardComment.vo.BoardCommentVo;

public class BoardCommentDaoImpl implements BoardCommentDao {

	private final String queryPath = "/net/front/boardComment/query/BoardCommentQuery.xml";
	
	private CommonDao commonDao;
	
	public BoardCommentDaoImpl() {
		commonDao = CommonDao.getInstance();
	}
	
	@Override
	public ArrayList<Object> list(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/list");
		commonDao.setPreparedStatement(query, boardCommentVo);
		return commonDao.getResult(BoardCommentVo.class.getName());
	}

	@Override
	public ArrayList<Object> selectOne(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/selectOne");
		commonDao.setPreparedStatement(query, boardCommentVo);
		return commonDao.getResult(BoardCommentVo.class.getName());
	}
	
	@Override
	public int insert(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/insert");
		commonDao.setPreparedStatement(query, boardCommentVo);
		return commonDao.getResultUpdate();
	}

	@Override
	public int update(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/update");
		commonDao.setPreparedStatement(query, boardCommentVo);
		return commonDao.getResultUpdate();
	}

	@Override
	public int delete(BoardCommentVo boardCommentVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/delete");
		commonDao.setPreparedStatement(query, boardCommentVo);
		return commonDao.getResultUpdate();
	}
}
