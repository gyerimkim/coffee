package bit.com.a.util;

import java.util.Date;

public class PdsUtil {

	public static String getNewFileName(String f) {
		String filename = "";
		String fpost = "";
		
		if(f.indexOf('.') >= 0) {	// 확장자명이 있음			
			fpost = f.substring( f.indexOf('.') );		//	abc.txt -> .txt
			filename = new Date().getTime() + fpost;	//  32342342.txt
		}else {
			filename = new Date().getTime() + ".back";
		}
		
		return filename;
	}
	
}




