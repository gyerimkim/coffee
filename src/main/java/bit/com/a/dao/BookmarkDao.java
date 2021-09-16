package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.BookmarkDto;

public interface BookmarkDao {
	// 줄겨찾기 하기
	public int bookmarkAdd(BookmarkDto bk);
	
	// 즐겨찾기 해제
	public int bookmarkDelete(BookmarkDto bk);
	
	// 즐겨찾기 여부 확인용
	public int getBookmarkCount(BookmarkDto bk);
	
	// 즐겨찾기 view list
	public List<BookmarkDto> getBookmarkList(int userno);
}
