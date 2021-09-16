package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchParam;

public interface PdsDao {

	List<PdsDto> getPdsList();
	
	boolean uploadPds(PdsDto dto);
	
	PdsDto getPds(int seq);
	
	void readcount(int seq);
	
	boolean updatePds(PdsDto dto);
	
	void downcount(int seq);
	void deletePds(int seq);
	
	int getPdsCount(SearchParam pds);
}
