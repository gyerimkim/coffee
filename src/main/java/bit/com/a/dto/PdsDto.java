package bit.com.a.dto;

public class PdsDto {
		private int seq;
		private String username;
		private String title;
		private String content;
		private String filename;		// 원본 파일명	abc.txt
		private String newfilename;		// 변환 파일명 3242423423.txt	
		private int readcount;
		private int downcount;
		private String regdate;	
		
	public PdsDto() {
		
	}

	public PdsDto(int seq, String username, String title, String content, String filename, String newfilename,
			int readcount, int downcount, String regdate) {
		super();
		this.seq = seq;
		this.username = username;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
	}

	public PdsDto(int seq, String username, String title, String content, String filename, String newfilename) {
		super();
		this.seq = seq;
		this.username = username;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", username=" + username + ", title=" + title + ", content=" + content
				+ ", filename=" + filename + ", newfilename=" + newfilename + ", readcount=" + readcount
				+ ", downcount=" + downcount + ", regdate=" + regdate + "]";
	}
	
	
}
