package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.BookmarkDto;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {

	@Autowired
	SqlSession session;
	
	String ns = "Notice.";

	@Override
	public int bookmarkAdd(BookmarkDto bk) {
		return session.insert(ns+"bookmarkAdd", bk);
	}

	@Override
	public int bookmarkDelete(BookmarkDto bk) {
		return session.delete(ns+"bookmarkDelete", bk);
	}

	@Override
	public int getBookmarkCount(BookmarkDto bk) {
		return session.selectOne(ns+"getBookmarkCount", bk);
	}

	@Override
	public List<BookmarkDto> getBookmarkList(int userno) {
		return session.selectList(ns+"getBookmarkList", userno);
	}

	
}
