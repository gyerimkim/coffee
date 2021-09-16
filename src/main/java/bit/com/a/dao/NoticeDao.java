package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.NoticeDto;
import bit.com.a.dto.SearchParam;
import bit.com.a.dto.UserDto2;

public interface NoticeDao {

	List<NoticeDto> getNoticeList();
	
	boolean uploadNotice(NoticeDto dto);
	
	NoticeDto getNotice(int seq);
	
	int getNoticeCount(SearchParam notice);
	
	void readcount(int seq);
	
	boolean updateNotice(NoticeDto dto);
	void deleteNotice(int seq);
	
	List<UserDto2> allUserList(SearchParam param);
}
