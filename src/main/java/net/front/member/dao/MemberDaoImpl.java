package net.front.member.dao;

import java.util.ArrayList;

import net.common.util.dao.CommonDao;
import net.common.util.query.webListener.QueryMakerListener;
import net.front.member.vo.MemberVo;

public class MemberDaoImpl implements MemberDao {

	@Override
	public ArrayList<Object> login(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/login");
		return new CommonDao(query, memberVo).getResult(MemberVo.class.getName());
	}
	
	@Override
	public int insert(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/insert");
		return new CommonDao(query, memberVo).getResultUpdate();
	}
	
	@Override
	public ArrayList<Object> findEmail(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/findEmail");
		return new CommonDao(query, memberVo).getResult(MemberVo.class.getName());
	}
	
	@Override
	public ArrayList<Object> findPassword(MemberVo memberVo) {
		// TODO Auto-generated method stub
		String query = QueryMakerListener._QueryMap.get("/net/front/member/query/MemberQuery.xml/findPassword");
		return new CommonDao(query, memberVo).getResult(MemberVo.class.getName());
	}
}
