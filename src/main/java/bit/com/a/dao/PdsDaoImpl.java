package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchParam;

@Repository
public class PdsDaoImpl implements PdsDao{

	@Autowired
	SqlSession session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> getPdsList() {		
		return session.selectList(ns + "getPdsList");
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		int n = session.insert(ns + "uploadPds", dto);
		return n>0?true:false;
	}
	
	@Override
	public PdsDto getPds(int seq) {		
		return session.selectOne(ns + "getPds", seq);		
	}
	
	@Override
	public boolean updatePds(PdsDto dto) {
		int n = session.update(ns + "updatepds", dto);
		return n>0?true:false;
	}

	@Override
	public void readcount(int seq) {
		session.update(ns + "readcount", seq);
	}

	@Override
	public void downcount(int seq) {
		session.update(ns + "downcount", seq);
	}

	@Override
	public void deletePds(int seq) {
		session.delete(ns+"deletePds", seq);		
	}

	@Override
	public int getPdsCount(SearchParam pds) {
		return session.selectOne(ns + "getPdsCount", pds);
	}
	
}
