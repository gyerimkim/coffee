package bit.com.a.dao;

import java.util.List;
import java.util.Map;

import bit.com.a.dto.CalendarEventDto;
import bit.com.a.dto.CalendarPlugDto;

public interface CalendarPlugDao {

	List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto);
	List<CalendarEventDto> getCalendarEventList(Map<String, Object> param);
	int insertCalendarEvent(CalendarEventDto dto);
	int updateCalendarEvent(CalendarEventDto dto);
	int deleteCalendarEvent(CalendarEventDto dto);
}
