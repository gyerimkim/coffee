package bit.com.a.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.CalendarEventDto;
import bit.com.a.dto.CalendarPlugDto;

@Repository
public class CalendarPlugDaoImpl implements CalendarPlugDao{

	@Autowired
	SqlSessionTemplate session;
	
	String ns = "CalPlug.";

	@Override
	public List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto) {		
		return session.selectList(ns + "getCalendarPlugList", dto);		
	}

	@Override
	public List<CalendarEventDto> getCalendarEventList(Map<String, Object> param) {
		return session.selectList(ns + "getCalendarEventList", param);
	}

	@Override
	public int insertCalendarEvent(CalendarEventDto dto) {
		return session.insert(ns + "insertCalendarEvent", dto);
	}

	@Override
	public int updateCalendarEvent(CalendarEventDto dto) {
		return session.update(ns + "updateCalendarEvent", dto);
	}

	@Override
	public int deleteCalendarEvent(CalendarEventDto dto) {
		return session.delete(ns + "deleteCalendarEvent", dto);
	}
	
	
}






