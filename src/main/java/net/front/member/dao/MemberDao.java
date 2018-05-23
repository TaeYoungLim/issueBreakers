package net.front.member.dao;

import java.util.ArrayList;

import net.front.member.vo.MemberVo;

public interface MemberDao {
	public ArrayList<Object> login(MemberVo memberVo);
	public int insert(MemberVo memberVo);
	public ArrayList<Object> findEmail(MemberVo memberVo);
	public ArrayList<Object> findPassword(MemberVo memberVo);
}
