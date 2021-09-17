package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.CalendarEventDto;
import bit.com.a.dto.UserDto;
import bit.com.a.service.CalendarPlugService;

@Controller
public class CalendarPlugController {

	@Autowired
	CalendarPlugService service;
	
	@RequestMapping(value = "calendaralllist.do", method = RequestMethod.GET)
	public String calendaralllist(Model model, HttpSession session) {
		model.addAttribute("doc_title", "전체일정");
		return "calendaralllist.tiles";
	}
	
	@RequestMapping(value = "calendarmylist.do", method = RequestMethod.GET)
	public String calendarmylist(Model model, HttpSession session) {
		model.addAttribute("doc_title", "나의일정");
		return "calendarmylist.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "caleventlist.do", method = RequestMethod.GET)
	public List<CalendarEventDto> caleventlist(
			HttpSession session,
			@RequestParam(value = "myEvent", required = false) String myEvent) {
		UserDto dto = (UserDto) session.getAttribute("login");
		List<CalendarEventDto> eventList = service.getCalendarEventList(myEvent != null ? dto.getUserNo() : null);
		return eventList;
	}
	
	@ResponseBody
	@RequestMapping(value = "caleventwrite.do", method = RequestMethod.POST)
	public String caleventwrite(
			HttpSession session,
			CalendarEventDto dto) {
		
		UserDto userdto = (UserDto) session.getAttribute("login");
		dto.setUserno(userdto.getUserNo());
		if (dto.getSeq() == null) {
			service.addCalendarEvent(dto);
			return "add";
		} else {
			service.modifyCalendarEvent(dto);
			return "modify";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "caleventremove.do", method = RequestMethod.POST)
	public String caleventremove(
			HttpSession session,
			CalendarEventDto dto) {
		UserDto userdto = (UserDto) session.getAttribute("login");
		dto.setUserno(userdto.getUserNo());
		service.removeCalendarEvent(dto);
		return "remove";
	}
	
}






