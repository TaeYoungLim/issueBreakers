package net.admin.board.vo;

import net.common.util.page.PageVo;

public class BoardVo extends PageVo {
	private int boardId;
	private String boardCategoryId;
	private String boardTitle;
	private String boardContent;
	private int boardWriter;
	private String boardDate;
	
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
}
