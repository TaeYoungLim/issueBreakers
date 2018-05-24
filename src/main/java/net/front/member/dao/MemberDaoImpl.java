package net.front.member.dao;

import java.util.ArrayList;

import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;
import net.front.member.vo.MemberVo;
import net.front.tag.vo.TagVo;

public class MemberDaoImpl implements MemberDao {

	private CommonDao commonDao;
	
	public MemberDaoImpl() {
		commonDao = CommonDao.getInstance();
	}
	
	@Override
	public ArrayList<Object> login(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/login");
		commonDao.setPreparedStatement(query, memberVo);
		return commonDao.getResult(MemberVo.class.getName());
	}
	
	@Override
	public int insert(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/insert");
		commonDao.setPreparedStatement(query, memberVo);
		return commonDao.getResultUpdate();
	}
	
	@Override
	public ArrayList<Object> findEmail(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/findEmail");
		commonDao.setPreparedStatement(query, memberVo);
		return commonDao.getResult(MemberVo.class.getName());
	}
	
	@Override
	public ArrayList<Object> findPassword(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/findPassword");
		commonDao.setPreparedStatement(query, memberVo);
		return commonDao.getResult(MemberVo.class.getName());
	}
}
