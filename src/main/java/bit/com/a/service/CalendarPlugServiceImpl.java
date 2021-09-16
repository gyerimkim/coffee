package bit.com.a.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.CalendarPlugDao;
import bit.com.a.dto.CalendarEventDto;
import bit.com.a.dto.CalendarPlugDto;

@Service
public class CalendarPlugServiceImpl implements CalendarPlugService {

	@Autowired
	CalendarPlugDao dao;

	@Override
	public List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto) {		
		return dao.getCalendarPlugList(dto);
	}

	@Override
	public List<CalendarEventDto> getCalendarEventList(Integer userno) {
		Map<String, Object> param = new HashMap<>();
		param.put("userno", userno);
		return dao.getCalendarEventList(param);
	}

	@Override
	public boolean addCalendarEvent(CalendarEventDto dto) {
		dto.setEnd(dto.getEnd() + " 23:59:59"); 
		int result = dao.insertCalendarEvent(dto);
		return result > 0;
	}

	@Override
	public boolean modifyCalendarEvent(CalendarEventDto dto) {
		dto.setEnd(dto.getEnd() + " 23:59:59");
		int result = dao.updateCalendarEvent(dto);
		return result > 0;
	}

	@Override
	public boolean removeCalendarEvent(CalendarEventDto dto) {
		int result = dao.deleteCalendarEvent(dto);
		return result > 0;
	}
	
}
