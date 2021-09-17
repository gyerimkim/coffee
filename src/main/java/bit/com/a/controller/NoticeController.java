package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import bit.com.a.dto.BookmarkDto;
import bit.com.a.dto.NoticeDto;
import bit.com.a.dto.SearchParam;
import bit.com.a.dto.UserDto;
import bit.com.a.dto.UserDto2;
import bit.com.a.service.BookmarkServiceImpl;
import bit.com.a.service.NoticeService;
import bit.com.a.util.NoticeUtil;

@Controller
public class NoticeController {
	
	static Logger log = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	NoticeService service;
	
	@Autowired
	BookmarkServiceImpl bservice;
	
	@ResponseBody
	@RequestMapping(value="bookmark.do", method=RequestMethod.POST)
	public String bookmark(int notice_seq, int userno ) {
		
		BookmarkDto bk = new BookmarkDto(0, userno, notice_seq); //xml 파라미터 값
		System.out.println(bk.toString());
		int count = bservice.getBookmarkCount(bk);
		if(count == 0) {
			bservice.bookmarkAdd(bk);
			return "add";
		}else {
			bservice.bookmarkDelete(bk);
			return "del";
		}
	}
	
	// 즐겨찾기 메소드
	public int[] bcount(UserDto user, List<NoticeDto> nlist) {
	    int[] count = new int[nlist.size()];
	      if (user == null) {
	         for (int i = 0; i < nlist.size(); i++) {
	            count[i] = 0;
	         }
	      } else if (user != null) {
	         int userno = user.getUserNo();
	         int[] notice_Seq = new int[nlist.size()];
	         for (int i = 0; i < nlist.size(); i++) {
	        	 notice_Seq[i] = nlist.get(i).getSeq();
	        		BookmarkDto bk = new BookmarkDto(0, userno, notice_Seq[i]);
	            count[i] = bservice.getBookmarkCount(bk);
	         }
	      }
	      return count;
	   }
	
	
	// 즐겨찾기 리스트
	@RequestMapping(value = "bookmarklist.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bookmarklist(Model model, SearchParam param,  HttpServletRequest req) {
		model.addAttribute("doc_title", "즐겨찾기");
		
		HttpSession session = req.getSession();
		UserDto temp = (UserDto)session.getAttribute("login");
		System.out.println("로그인유저 " + temp);
		int userno = temp.getUserNo();
		System.out.println("로그인유저넘버 " + userno);	
		
		//페이징 처리
		int sn = param.getPageNumber();	 // 0 1 2 3 4
		int start = 1 + sn * 10;	 // 1 11 21 31
		int end = (sn + 1) * 10;	 // 10 20 30 40
		
		param.setStart(start);
		param.setEnd(end);

		List<BookmarkDto> blist = bservice.getBookmarkList(userno);
		
		for(int i=0; i<blist.size(); i++) {
			BookmarkDto bk = blist.get(i);
			System.out.println(bk.toString());
		}
				
		model.addAttribute("blist", blist);
	
		int totalCount = service.getNoticeCount(param);
		model.addAttribute("totalCount", totalCount); 
		
		model.addAttribute("pageNumber", param.getPageNumber() + 1);

		return "bookmarklist.tiles";		
	}
	
	
	@RequestMapping(value = "noticelist.do", method = RequestMethod.GET)
	public String noticelist(Model model, SearchParam param) {
		model.addAttribute("doc_title", "공지사항");
		
		//페이징 처리
		int sn = param.getPageNumber();	 // 0 1 2 3 4
		int start = 1 + sn * 10;	 // 1 11 21 31
		int end = (sn + 1) * 10;	 // 10 20 30 40
		
		param.setStart(start);
		param.setEnd(end);
		
		List<NoticeDto> list = service.getNoticeList();
		model.addAttribute("noticelist", list);
		
		int totalCount = service.getNoticeCount(param);
		model.addAttribute("totalCount", totalCount);
		
		model.addAttribute("pageNumber", param.getPageNumber() + 1);
		
		model.addAttribute("search", param.getSearch());
		model.addAttribute("category", param.getCategory());
		
		return "noticelist.tiles";		
	}
	
	
	@RequestMapping(value = "noticewrite.do", method = RequestMethod.GET)
	public String noticewrite(Model model) {
		model.addAttribute("doc_title", "글쓰기");
		
		return "noticewrite.tiles";
	}
	
	
	@RequestMapping(value = "noticeupload.do", method = RequestMethod.POST)
	public String noticeupload(NoticeDto noticedto,
							@RequestParam(value = "fileload", required = false)
							MultipartFile fileload,
							HttpServletRequest req) {
		
		// filename(원본) 취득
		String filename = fileload.getOriginalFilename();
		noticedto.setFilename(filename);	
		
		String fupload = req.getServletContext().getRealPath("/upload");
		System.out.println("fupload:" + fupload);
		
		// 파일명 변경
		String newfilename = NoticeUtil.getNewFileName(noticedto.getFilename());
		noticedto.setNewfilename(newfilename);
		
		File file = new File(fupload + "/" + newfilename);
		
		try {
			// 실제로 업로드
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			// DB에 저장
			service.uploadNotice(noticedto);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/noticelist.do";
	}
	
	
	
	@RequestMapping(value = "fileDownload.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String fileDownload(String newfilename, String filename, int seq, 
				HttpServletRequest req, Model model) {
		
		// 경로
		// server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		File downloadFile = new File(fupload + "/" + newfilename);
		
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("originalFile", filename);
		model.addAttribute("seq", seq);
				
		return "downloadView";
	}
	
	@RequestMapping(value = "noticedetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String noticedetail(int seq, Model model) {
		
		service.readcount(seq);
		
		// dto 취득
		NoticeDto noticedto = service.getNotice(seq);
		model.addAttribute("notice", noticedto);
		
		return "noticedetail.tiles";
	}
	
	
	
	@RequestMapping(value = "noticeupdate.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String noticeupdate(int seq, Model model) {
		model.addAttribute("doc_title", "글 수정");
		
		// dto 취득
		NoticeDto noticedto = service.getNotice(seq);
		model.addAttribute("notice", noticedto);
		
		return "noticeupdate.tiles";
	}
	
	@RequestMapping(value = "noticeupdateAf.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String noticeupdateAf(NoticeDto noticedto, 
								String namefile,	// 기존의 파일 명,
								HttpServletRequest req,
								@RequestParam(value = "fileload", required = false)MultipartFile fileload) {
		
		noticedto.setFilename(fileload.getOriginalFilename());
		
		// 파일 경로
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 수정할 파일이 있음
		if(noticedto.getFilename() != null && !noticedto.getFilename().equals("")) {
			
			String f = noticedto.getFilename();
			String newfilename = NoticeUtil.getNewFileName(f);
			
			noticedto.setFilename(f);
			noticedto.setNewfilename(newfilename);
			
			File file = new File(fupload + "/" + newfilename);			
			
			try {
				// 실제 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				// db 경신
				service.updateNotice(noticedto);		
				
			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}
		else {	// 수정할 파일 없음
			
			// 기존의 파일명으로 설정
			noticedto.setFilename(namefile);
			
			// DB
			service.updateNotice(noticedto);
		}
		
		
		return "redirect:/noticelist.do";
	}
	@RequestMapping(value = "noticedelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteNotice(int seq, Model model) {		
		service.deleteNotice(seq);
		return "redirect:/noticelist.do";		
	}
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject;
	}

	

}








