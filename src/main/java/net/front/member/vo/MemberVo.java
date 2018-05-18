package net.front.member.vo;

public class MemberVo {
	private int memberId;
	private String memberType;
	private String memberEmail;
	private String memberPassword;
	private String memberNick;
	private String memberDate;
	
	public MemberVo() {
		super();
	}
	public MemberVo(String memberEmail, String memberPassword) {
		super();
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
	}
	public MemberVo(String memberEmail, String memberPassword, String memberNick, String memberDate) {
		super();
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberNick = memberNick;
		this.memberDate = memberDate;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(String memberDate) {
		this.memberDate = memberDate;
	}
	
	@Override
	public String toString() {
		return "MemberVo [memberId=" + memberId + ", memberType=" + memberType + ", memberEmail=" + memberEmail
				+ ", memberPassword=" + memberPassword + ", memberNick=" + memberNick + ", memberDate=" + memberDate
				+ "]";
	}
}
