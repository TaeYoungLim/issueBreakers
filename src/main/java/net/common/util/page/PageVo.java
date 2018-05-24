package net.common.util.page;

public class PageVo {
	
	private String suffix; // 다중 페이지, 검색에 대한 함수 사용등 suffix를 부여하기 위한 변수
	
	public PageVo() {
		super();
	}
	
	public PageVo(int startRow, int endRow) {
		super();
		this.startRow = startRow;
		this.endRow = endRow;
	}

	/* page */
	int pageNum = 1;
    int startRow;
    int endRow;
    int count;
	int number;
	
	int pageCount;
    int startPage;
	int pageBlock;
    int endPage;
    
    int pageSize = 10;
    
	/**
	 * 페이지 데이터를 만든다.
	 * @return PageVo(다음메소드와 연결해서 쓰기위해)
	 */
	public PageVo setPageData() {
	    startRow = (pageNum - 1) * pageSize + 1;
	    endRow = pageNum * pageSize;
	    number = 0;
		
		pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
        startPage = (int)(pageNum/10)*10+1;
		pageBlock = 10;
        endPage = startPage + pageBlock-1;
		
		return this;
	}
	
	/**
	 * 페이지 html을 만든다.
	 * @return
	 */
	public String getPageHtml() {
		
		StringBuffer stringBuffer = new StringBuffer();
		
		if(suffix == null) 
			suffix = "";
		
		if (count > 0) {
			
			// 이전 페이지
			if(startPage > 10)
				stringBuffer.append("<a class='pageMove" + suffix + "' data-page-no='" + (startPage - 10) + "' href='javascript:void(0);'>&lt;</a>");
			
			// n.....m 페이지
			for(int i = this.startPage; i <= this.endPage; i++) {
				if(this.pageNum == i)
					stringBuffer.append("<a class='pageMove" + suffix + " on' data-page-no='" + i + "' href='javascript:void(0);'>" + i + "</a>");
				else
					stringBuffer.append("<a class='pageMove" + suffix + "' data-page-no='" + i + "' href='javascript:void(0);'>" + i + "</a>");
			}
			
			// 다음페이지
			if (endPage < pageCount)
				stringBuffer.append("<a class='pageMove" + suffix + "' data-page-no='" + (startPage + 10) + "' href='javascript:void(0);'>&gt;</a>");
			
		}
		
		return stringBuffer.toString();
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
