package bit.com.a.dto;

import java.io.Serializable;

public class CalendarEventDto implements Serializable {
	
	
	/*
	 * CREATE TABLE CALENDAR_EVENT( 
	 * SEQ NUMBER(8) PRIMARY KEY, 
	 * TITLE VARCHAR2(200)NOT NULL, 
	 * START_DATE DATE NOT NULL, 
	 * END_DATE DATE,
	 * COLOR VARCHAR2(10), 
	 * USERNO NUMBER(4, 0) NOT NULL 
	 * );
	 * 
	 * 
	 * CREATE SEQUENCE SEQ_CALENDAR_EVENT
		START WITH 1
		INCREMENT BY 1;
	 */
	
	
	private Integer seq;
	private String title;
	private String start;
	private String end;
	private int userno;
	private String color;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "CalendarEventDto [seq=" + seq + ", title=" + title + ", start=" + start + ", end=" + end + ", userno="
				+ userno + ", color=" + color + "]";
	}
	
}
