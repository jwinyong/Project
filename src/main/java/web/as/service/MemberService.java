package web.as.service;

import java.util.List;

import web.as.vo.memberVO;
import web.as.vo.asinfoVO;

public interface MemberService {
	
	public List<memberVO> selectMemberList(memberVO vo) throws Exception;	
	public memberVO selectMemberOne(memberVO vo) throws Exception;
	
	
	public int selectMemberCount(memberVO vo) throws Exception;
	public int insertMember(memberVO vo) throws Exception;
}
