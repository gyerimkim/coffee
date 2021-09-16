package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.NoticeDto;
import bit.com.a.dto.SearchParam;
import bit.com.a.dto.UserDto2;

public interface NoticeDao {
	
	List<NoticeDto> getNoticeList();
	
	boolean uploadNotice(NoticeDto dto);
	boolean updateNotice(NoticeDto dto);
	void deleteNotice(int seq);
	
	// datail 보기
	NoticeDto getNotice(int seq);	
	
	// 페이지 수
	int getNoticeCount(SearchParam notice);	
	
	void readcount(int seq);

}
