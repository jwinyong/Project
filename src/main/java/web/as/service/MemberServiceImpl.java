package web.as.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.as.dao.MemberDao;
import web.as.vo.asinfoVO;
import web.as.vo.memberVO;

// 구현체는 루트컨테이너에 들어가 있어야한다!

@Service // 어노테이션을 기준으로 이 클래스를 Bean으로 만든다(객체화)
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDao memberdao; // SRP! 실제적으로 데이터를 왔다갔다 하기 때문에!

	
	@Override
	public List<memberVO> selectMemberList(memberVO vo) throws Exception {
		return memberdao.selectMemberList(vo);
	}

	@Override
	public memberVO selectMemberOne(memberVO vo) throws Exception {
		return memberdao.selectMemberOne(vo);
	}

	@Override
	public int selectMemberCount(memberVO vo) throws Exception {
		return memberdao.selectMemberCount(vo);
	}

	@Override
	public int insertMember(memberVO vo) throws Exception {
		return memberdao.insertMember(vo);
	}

	
	
}
