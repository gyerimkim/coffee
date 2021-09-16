package bit.com.a.dto;

/*CREATE TABLE BOOKMARK(
SEQ NUMBER(8),
USERNO NUMBER(4),
NOTICE_SEQ NUMBER(8)
)               

SELECT NVL(COUNT(*), 0) FROM BOOKMARK
   WHERE USERNO=1000 AND NOTICE_SEQ=22
   
CREATE SEQUENCE SEQ_BOOKMARK
INCREMENT BY 1
START WITH 1;

SELECT * FROM BOOKMARK;*/

public class BookmarkDto {
	private int seq; 
	private int userno;
	private int notice_seq;
	
	private String username;
	private String title;
	private String content;
	private int readcount;
	private String createdate;
	
	public BookmarkDto() {
		
	}


	public BookmarkDto(int seq, int userno, int notice_seq) {
		super();
		this.seq = seq;
		this.userno = userno;
		this.notice_seq = notice_seq;
	}

	

	
	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getUserno() {
		return userno;
	}


	public void setUserno(int userno) {
		this.userno = userno;
	}


	public int getNotice_seq() {
		return notice_seq;
	}


	public void setNotice_seq(int notice_seq) {
		this.notice_seq = notice_seq;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	public String getCreatedate() {
		return createdate;
	}


	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


	@Override
	public String toString() {
		return "BookmarkDto [seq=" + seq + ", userno=" + userno + ", notice_seq=" + notice_seq + ", username="
				+ username + ", title=" + title + ", content=" + content + ", readcount=" + readcount + ", createdate="
				+ createdate + "]";
	}
	
}
