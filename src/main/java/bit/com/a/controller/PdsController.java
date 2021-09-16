package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchParam;
import bit.com.a.service.PdsService;
import bit.com.a.util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@RequestMapping(value = "pdslist.do", method = RequestMethod.GET)
	public String pdslist(Model model, SearchParam param) {
		model.addAttribute("doc_title", "자료실");
		
		int sn = param.getPageNumber();	// 0 1 2 3 4
		int start = 1 + sn * 10;	// 1  11
		int end = (sn + 1) * 10;	// 10 20 
		
		param.setStart(start);
		param.setEnd(end);
		
		List<PdsDto> list = service.getPdsList();
		model.addAttribute("pdslist", list);
		
		int totalCount = service.getPdsCount(param);
		model.addAttribute("totalCount", totalCount);
		
		model.addAttribute("pageNumber", param.getPageNumber() + 1);
		
		model.addAttribute("search", param.getSearch());
		model.addAttribute("category", param.getCategory());
		
		
		return "pdslist.tiles";		
	}
	
	@RequestMapping(value = "pdswrite.do", method = RequestMethod.GET)
	public String pdswrite(Model model) {
		model.addAttribute("doc_title", "자료 올리기");
		
		return "pdswrite.tiles";
	}
	
	@RequestMapping(value = "pdsupload.do", method = RequestMethod.POST)
	public String pdsupload(PdsDto pdsdto,
							@RequestParam(value = "fileload", required = false)
							MultipartFile fileload,
							HttpServletRequest req) {
		
		// filename(원본) 취득
		String filename = fileload.getOriginalFilename();
		pdsdto.setFilename(filename);	// DB에 저장하기 위해서 원파일명을 넣어 준다
		
		// upload 경로설정
		// server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 폴더
	//	String fupload = "d:\\tmp";
		
		System.out.println("fupload:" + fupload);
		
		// 파일명 변경
		String newfilename = PdsUtil.getNewFileName(pdsdto.getFilename());
		pdsdto.setNewfilename(newfilename);
		
		File file = new File(fupload + "/" + newfilename);
		
		try {
			// 실제로 업로드
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			// DB에 저장
			service.uploadPds(pdsdto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
	}
	
	
	@RequestMapping(value = "pdsdetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String pdsdetail(int seq, Model model) {
		model.addAttribute("doc_title", "자료 보기");
		
		service.readcount(seq);

		// dto 취득
		PdsDto pdsdto = service.getPds(seq);
		model.addAttribute("pds", pdsdto);
		
		return "pdsdetail.tiles";
	}
	
	
	
	@RequestMapping(value = "pdsupdate.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String pdsupdate(int seq, Model model) {
		model.addAttribute("doc_title", "자료 수정");
		
		// dto 취득
		PdsDto pdsdto = service.getPds(seq);
		model.addAttribute("pds", pdsdto);
		
		return "pdsupdate.tiles";
	}
	
	@RequestMapping(value = "pdsupdateAf.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String pdsupdateAf(	PdsDto pdsdto, 
								String namefile,	// 기존의 파일 명,
								HttpServletRequest req,
								@RequestParam(value = "fileload", required = false)MultipartFile fileload) {
		
		pdsdto.setFilename(fileload.getOriginalFilename());
		
		// 파일 경로
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 수정할 파일이 있음
		if(pdsdto.getFilename() != null && !pdsdto.getFilename().equals("")) {
			
			String f = pdsdto.getFilename();
			String newfilename = PdsUtil.getNewFileName(f);
			
			pdsdto.setFilename(f);
			pdsdto.setNewfilename(newfilename);
			
			File file = new File(fupload + "/" + newfilename);			
			
			try {
				// 실제 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				// db 경신
				service.updatePds(pdsdto);		
				
			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}
		else {	// 수정할 파일 없음
			
			// 기존의 파일명으로 설정
			pdsdto.setFilename(namefile);
			
			// DB
			service.updatePds(pdsdto);	
		}
		
		return "redirect:/pdslist.do";
	}
	
	@RequestMapping(value = "pdsdelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteNotice(int seq, Model model) {		
		service.deletePds(seq);
		return "redirect:/pdslist.do";		
	}
}








