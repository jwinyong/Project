package web.as.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.as.vo.memberVO;

// 순수모델
@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;  // 스프링프레임워크 컨테이너에 실려있는 외부 빈을 통해 의존성 주입 가능
	
	public List<memberVO> selectMemberList(memberVO vo) throws Exception{
		
		return sqlSession.selectList("member.selectMemberList", vo);
	
		//1. select : 0~1 : selectOne, 0,1,여러개 : selectList.
		//2.update < int
		//3. delete : int
		//4. insert : int
	}
	
	public memberVO selectMemberOne(memberVO vo) throws Exception{
		
		return sqlSession.selectOne("member.selectMember", vo); // 데이터를 담는 VO와 문자열을 던진다
		                         // "member.selectMember" 문자열로 어떤 쿼리를 돌려야하는지 파악!
	}

	// return값이 int
	public int selectMemberCount(memberVO vo) throws Exception{
		
		return sqlSession.selectOne("member.selectMemberCount", vo);
		
	}
	
	public int insertMember(memberVO vo) throws Exception{
		return sqlSession.insert("member.insertMember", vo); // VO에는 사용자의 모든 가입정보가 들어있다
	}

	
	
}
