package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BookmarkDao;
import bit.com.a.dto.BookmarkDto;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	BookmarkDao dao;

	@Override
	public int bookmarkAdd(BookmarkDto bk) {
		return dao.bookmarkAdd(bk);
	}

	@Override
	public int bookmarkDelete(BookmarkDto bk) {
		return dao.bookmarkDelete(bk);
	}

	@Override
	public int getBookmarkCount(BookmarkDto bk) {
		return dao.getBookmarkCount(bk);
	}

	@Override
	public List<BookmarkDto> getBookmarkList(int userno) {
		return dao.getBookmarkList(userno);
	}
}
