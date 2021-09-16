package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.CalendarEventDto;
import bit.com.a.dto.CalendarPlugDto;

public interface CalendarPlugService {

	List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto);
	
	List<CalendarEventDto> getCalendarEventList(Integer userno);
	boolean addCalendarEvent(CalendarEventDto dto);
	boolean modifyCalendarEvent(CalendarEventDto dto);
	boolean removeCalendarEvent(CalendarEventDto dto);
}
