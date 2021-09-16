package bit.com.a.dto;

public class SearchParam extends UserDto{
	private String search;
	private String search1;
	private String category;
	private int pageNumber;
	
	private int start;
	private int end;
	
	
	public SearchParam() {
	}

	// 일반적인 검색 시 사용 - toString 처리 가능
	public SearchParam(String search, String category, int pageNumber, int start, int end) {
		super();
		this.search = search;
		this.category = category;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}
	

	// 한슬 - 기간별 검색을 위해 SEARCH, SEARCH1 이라는 변수 넣음, toString처리 ㄴㄴ
	public SearchParam(String search, String search1, String category, int pageNumber, int start, int end) {
		super();
		this.search = search;
		this.search1 = search1;
		this.category = category;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}


	public String getSearch() {
		return search;
	}	

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearch1() {
		return search1;
	}


	public void setSearch1(String search1) {
		this.search1 = search1;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "searchParam [search=" + search + ", category=" + category + ", pageNumber=" + pageNumber + ", start="
				+ start + ", end=" + end + "]";
	}

	
}
