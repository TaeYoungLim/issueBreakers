package net.admin.board.vo;

import net.common.util.page.PageVo;

public class BoardVo extends PageVo {
	private int boardId;
	private String boardCategoryId;
	private String boardTitle;
	private String boardContent;
	private int boardWriter;
	private String boardDate;
	private int boardVote;
	private String boardSelection;
	private int boardSelectionCommentId;
	
	private String memberName;
	private int commentCount; // 댓글 갯수
	
	public BoardVo() {}
	
	public BoardVo(String boardCategoryId) {
		super();
		this.boardCategoryId = boardCategoryId;
	}

	public BoardVo(String boardCategoryId, String boardTitle, String boardContent, int boardWriter,
			String boardDate) {
		super();
		this.boardCategoryId = boardCategoryId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardDate = boardDate;
	}
	
	public BoardVo(int boardId, String boardCategoryId, String boardTitle, String boardContent, int boardWriter) {
		super();
		this.boardId = boardId;
		this.boardCategoryId = boardCategoryId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
	}

	public BoardVo(int boardId, String boardCategoryId, int boardWriter) {
		super();
		this.boardId = boardId;
		this.boardCategoryId = boardCategoryId;
		this.boardWriter = boardWriter;
	}
	
	public BoardVo(int boardId, String boardCategoryId) {
		super();
		this.boardId = boardId;
		this.boardCategoryId = boardCategoryId;
	}

	public BoardVo(int boardId, String boardCategoryId, int boardSelectionCommentId, int boardWriter) {
		super();
		this.boardId = boardId;
		this.boardCategoryId = boardCategoryId;
		this.boardSelectionCommentId = boardSelectionCommentId;
		this.boardWriter = boardWriter;
	}
	
	
	
	public BoardVo(String boardCategoryId, int startRow, int endRow) {
		super(startRow, endRow);
		this.boardCategoryId = boardCategoryId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardCategoryId() {
		return boardCategoryId;
	}

	public void setBoardCategoryId(String boardCategoryId) {
		this.boardCategoryId = boardCategoryId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(int boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardSelection() {
		return boardSelection;
	}

	public void setBoardSelection(String boardSelection) {
		this.boardSelection = boardSelection;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getBoardVote() {
		return boardVote;
	}

	public void setBoardVote(int boardVote) {
		this.boardVote = boardVote;
	}

	public int getBoardSelectionCommentId() {
		return boardSelectionCommentId;
	}

	public void setBoardSelectionCommentId(int boardSelectionCommentId) {
		this.boardSelectionCommentId = boardSelectionCommentId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
