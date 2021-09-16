package bit.com.a.dto;

public class NoticeDto {
	private int seq;
	private int userno;
	private String username;
	
	private String category;
	private String title;
	private String content;
	private String createdate;
	private String updatedate;
	private String filename;
	private String newfilename;
	private int readcount;
	private int readcheck;
	
	private int imt;
	private int del;
	

	public NoticeDto() {
		
	}


	public NoticeDto(int seq, int userno, String username, String category, String title, String content,
			String createdate, String updatedate, String filename, String newfilename, int readcount, int readcheck,
			int imt, int del) {
		super();
		this.seq = seq;
		this.userno = userno;
		this.username = username;
		this.category = category;
		this.title = title;
		this.content = content;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.filename = filename;
		this.newfilename = newfilename;
		this.readcount = readcount;
		this.readcheck = readcheck;
		this.imt = imt;
		this.del = del;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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


	public String getCreatedate() {
		return createdate;
	}


	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


	public String getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
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


	public int getReadcheck() {
		return readcheck;
	}


	public void setReadcheck(int readcheck) {
		this.readcheck = readcheck;
	}


	public int getImt() {
		return imt;
	}


	public void setImt(int imt) {
		this.imt = imt;
	}


	public int getDel() {
		return del;
	}


	public void setDel(int del) {
		this.del = del;
	}


	@Override
	public String toString() {
		return "noticeDto [seq=" + seq + ", userno=" + userno + ", username=" + username + ", category="
				+ category + ", title=" + title + ", content=" + content + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + ", filename=" + filename + ", newfilename=" + newfilename
				+ ", readcount=" + readcount + ", readcheck=" + readcheck + ", imt=" + imt + ", del=" + del + "]";
	}
	
	
}
