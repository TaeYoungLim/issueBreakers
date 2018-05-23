package net.front.comment.vo;

public class BoardCommentVo {
	private int boardCommentId;
	private String boardCategoryId;
	private int boardId;
	private String boardCommentTitle;
	private String boardCommentContent;
	private String boardCommentWriter;
	private String boardCommentDate;
	
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
	public String getBoardCommentTitle() {
		return boardCommentTitle;
	}
	public void setBoardCommentTitle(String boardCommentTitle) {
		this.boardCommentTitle = boardCommentTitle;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public String getBoardCommentWriter() {
		return boardCommentWriter;
	}
	public void setBoardCommentWriter(String boardCommentWriter) {
		this.boardCommentWriter = boardCommentWriter;
	}
	public String getBoardCommentDate() {
		return boardCommentDate;
	}
	public void setBoardCommentDate(String boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}
}
