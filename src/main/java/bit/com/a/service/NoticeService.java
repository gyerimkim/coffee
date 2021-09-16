package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.NoticeDto;
import bit.com.a.dto.SearchParam;
import bit.com.a.dto.UserDto2;

public interface NoticeService {

	List<NoticeDto> getNoticeList();
	
	boolean uploadNotice(NoticeDto dto);
	
	NoticeDto getNotice(int seq);
	
	void readcount(int seq);
	
	boolean updateNotice(NoticeDto dto);

	void deleteNotice(int seq);
	
	int getNoticeCount(SearchParam notice);
	
	List<UserDto2> allUserList(SearchParam param);
	
}
