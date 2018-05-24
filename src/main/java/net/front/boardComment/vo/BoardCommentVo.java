package net.front.boardComment.vo;

import net.common.util.page.PageVo;

public class BoardCommentVo {
	private int boardCommentId;
	private String boardCategoryId;
	private int boardId;
	private String boardCommentContent;
	private int boardCommentWriter;
	private String boardCommentDate;
	
	private String memberName;
	
	public BoardCommentVo() {
		super();
	}
	
	public BoardCommentVo(String boardCategoryId, int boardId, String boardCommentContent, int boardCommentWriter,
			String boardCommentDate) {
		super();
		this.boardCategoryId = boardCategoryId;
		this.boardId = boardId;
		this.boardCommentContent = boardCommentContent;
		this.boardCommentWriter = boardCommentWriter;
		this.boardCommentDate = boardCommentDate;
	}

	public BoardCommentVo(String boardCategoryId, int boardId) {
		super();
		this.boardCategoryId = boardCategoryId;
		this.boardId = boardId;
	}
	
	public int getBoardCommentId() {
		return boardCommentId;
	}
	public void setBoardCommentId(int boardCommentId) {
		this.boardCommentId = boardCommentId;
	}
	public String getBoardCategoryId() {
		return boardCategoryId;
	}
	public void setBoardCategoryId(String boardCategoryId) {
		this.boardCategoryId = boardCategoryId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public int getBoardCommentWriter() {
		return boardCommentWriter;
	}
	public void setBoardCommentWriter(int boardCommentWriter) {
		this.boardCommentWriter = boardCommentWriter;
	}
	public String getBoardCommentDate() {
		return boardCommentDate;
	}
	public void setBoardCommentDate(String boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
