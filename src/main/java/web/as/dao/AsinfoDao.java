package web.as.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.as.vo.asinfoVO;

@Repository
public class AsinfoDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<asinfoVO> selectAsInfoList(asinfoVO vo) throws Exception{
		
		return sqlSession.selectList("asinfo.selectAsInfoList", vo);
		
	}
	
	public asinfoVO selectAsInfo(asinfoVO vo) throws Exception{
		
		return sqlSession.selectOne("asinfo.selectAsInfo", vo);
		
	}
	
	public int updateAsinfo(asinfoVO vo) throws Exception{
		
		return sqlSession.update("asinfo.updateAsinfo", vo);
		
	}
	
	public int insertAsinfo(asinfoVO vo) throws Exception{
		
		return sqlSession.insert("asinfo.insertAsinfo", vo);
		
	}
	
	
	public int deleteAsinfo(asinfoVO vo) throws Exception{
		
		return sqlSession.delete("asinfo.deleteAsinfo", vo);
		
	}
}
