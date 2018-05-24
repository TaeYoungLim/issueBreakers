package net.front.tag.dao;

import java.util.ArrayList;

import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;
import net.front.boardComment.vo.BoardCommentVo;
import net.front.tag.vo.TagVo;

public class TagDaoImpl implements TagDao {

	private final String queryPath = "/net/front/tag/query/TagQuery.xml";
	
	private CommonDao commonDao;
	
	public TagDaoImpl() {
		commonDao = CommonDao.getInstance();
	}
	
	@Override
	public ArrayList<Object> list() {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/list");
		commonDao.setPreparedStatement(query);
		return commonDao.getResult(TagVo.class.getName());
	}
	
	@Override
	public ArrayList<Object> listByBoardId(TagVo tagVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/listByBoardId");
		commonDao.setPreparedStatement(query, tagVo);
		return commonDao.getResult(TagVo.class.getName());
	}
	
	@Override
	public ArrayList<Object> selectOne(TagVo tagVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/selectOne");
		commonDao.setPreparedStatement(query, tagVo);
		return commonDao.getResult(TagVo.class.getName());
	}

	@Override
	public int insert(TagVo tagVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/insert");
		commonDao.setPreparedStatement(query, tagVo);
		return commonDao.getResultUpdate();
	}

	@Override
	public int update(TagVo tagVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/update");
		commonDao.setPreparedStatement(query, tagVo);
		return commonDao.getResultUpdate();
	}
	
	@Override
	public int delete(TagVo tagVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get(queryPath + "/delete");
		commonDao.setPreparedStatement(query, tagVo);
		return commonDao.getResultUpdate();
	}
}
