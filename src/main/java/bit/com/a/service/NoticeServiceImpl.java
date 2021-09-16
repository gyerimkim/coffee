package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.NoticeDao;
import bit.com.a.dto.NoticeDto;
import bit.com.a.dto.SearchParam;
import bit.com.a.dto.UserDto2;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeDao dao;

	@Override
	public List<NoticeDto> getNoticeList() {		
		return dao.getNoticeList();
	}

	@Override
	public boolean uploadNotice(NoticeDto dto) {		
		return dao.uploadNotice(dto);
	}
	
	@Override
	public boolean updateNotice(NoticeDto dto) {		
		return dao.updateNotice(dto);
	}
	
	@Override
	public void readcount(int seq) {
		dao.readcount(seq);
	}

	@Override
	public NoticeDto getNotice(int seq) {		
		return dao.getNotice(seq);
	}
	

	@Override
	public void deleteNotice(int seq) {
		dao.deleteNotice(seq);
		
	}

	@Override
	public int getNoticeCount(SearchParam notice) {
		return dao.getNoticeCount(notice);
	}

	@Override
	public List<UserDto2> allUserList(SearchParam param) {
		return dao.allUserList(param);
	}
}
