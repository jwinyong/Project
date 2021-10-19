package web.as.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.as.common.MailForm;
import web.as.common.MailSend;
import web.as.common.SessionUtil;
import web.as.service.AsinfoService;
import web.as.service.MemberService;
import web.as.vo.MailMstVO;
import web.as.vo.asinfoVO;
import web.as.vo.memberVO;

//사용자가 요청하면 디스팩처 서블릿이 받아서 첫번째로 전달하는 컨트롤러
@Controller
public class ViewController {

	@Autowired  // 외부에서 의존성 주입으로 구현체가 들어오게끔 하는 어노테이션!
	MemberService service; // 인터페이스에 종속적
	// MemberService는 인터페이스인데 왜 구현체 없이 혼자 떠있을까?
	// 그 이유는 바로 의존성 주입 DIP!
	
	// MemberService service = new MemberServiceIMPL();
	// 이렇게 구현하지 않은 이유는?
	// - 의존성 주입을 안하면, 변경하는 대상에 의존을 하면 변경을 하는 대상이 변하기 때문에 나까지 변한다!
	// - ex) 자동차와 타이어의 관계

	// 변하지 않는 것에 의존하자!
	
	@Autowired
	AsinfoService asservice;
	
	
	// 핸들러 맵핑을 통해서, 컨트롤러에게 전달
	@RequestMapping("/") // 맵핑 정보를 어노테이션으로 선언
	public String main(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception
	{
		// 사용자 세션.
		// VO는 클라이언트와 서버 사이에서 쿼리를 저장해서 전송...?
		// 사용자 정보를 유지시켜주고싶다
		memberVO membervo = (memberVO)SessionUtil.getAttribute("member");
		
		// 로그인 세션 체크
		if(membervo == null) 
		{
			return "login"; // DB에 로그인정보가 없다면 뷰 리솔버가 가서 로그인 페이지를 찾는?
		}
		else 
		{
			return "index"; // DB에 로그인정보가 없다면 인덱스 뷰 리솔버로 가서 인덱스 페이지를 찾는?
		}
	
	}
	
	
	@ResponseBody // 로그인 정보에 대한 true, false를 받길 원한다! 응답에 맞는 데이터만 받으면 된다! 뷰생성을 하지 않는 어노테이션!
	@RequestMapping("/member/login")
	public boolean memberlogin(memberVO vo, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception
	{
		// VO를 통해서 id, passwd 전달 받는다.
		// 쿼리를 날려서 사용자가 존재하는지 알아본다.

		// 1) service는 쿼리를 처리한다
		// 2) 그런데 service는 인터페이스!
		// 3) service는 실제 구현체를 new로 할당한 적이 없다
		// 3-1) 왜 외부에서 구현체를 할당해서 넣어줬을까?
		// - DIP의 원리에 의해 스프링프레임워크에서 @Service, @Autowired로 구현한다
		// 스프링프레임워크는 어떤 메카니즘으로 되어 있길래 외부에서 넣는 것이 가능할까?
		// - Bean으로 관리하는데 3개의 서로 다른 컨테이너로 Bean을 저장하고 이를 생명주기로 관리한다
		// - 서블릿 컨테이너, 루트 컨테이너, 외부 컨테이너

		// 할당은 스프링프레임워크에서 DI해준다
		vo = service.selectMemberOne(vo);
		
		// 로그인 정보에 대한 true, false를 받길 원함! 뷰생성은 하지 않는다!
		// true, false 던지고 백엔드단 종료
		if(vo != null) {
			// VO를 세션에 등록해줌.
			SessionUtil.setAttribute("member", vo);
			
			return true;	// 로그인 완료
		}else {
			return false;	//로그인 실패
		}
		
	}
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		return "index";
	}

	@RequestMapping("/register")
	public String register(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		return "register";
	}
	
	@ResponseBody // 응답에 맞는 데이터만 받으면 됨! 뷰생성을 안한다!
	@RequestMapping("/member/join")
	                    // 모든 정보를 VO로 담아 주세요!
	public boolean join(@ModelAttribute("memberVO") memberVO vo ) throws Exception{
		
		// 조회를 해본다!
		int cnt = service.selectMemberCount(vo);
		
		// 조회결과가 나온다면 이것은 기존에 등록된 사용자이다
		if (cnt > 0) {
			return false; // DB에 정보가 있다
		}
		else // 조회가 안되었기 때문에 회원가입을 해준다 
		{
			// 새로운 정보, 이 VO를 DB에 넣어준다
			service.insertMember(vo);
		}
		
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/asinfo/search")
	public List<asinfoVO> getList(@ModelAttribute("asinfoVO") asinfoVO vo) throws Exception{
		
		// 멤버 서비스
		List<asinfoVO> list = asservice.selectAsInfoList(vo) ;
		
		return list;
		
	}
	
	@ResponseBody
	@RequestMapping("/asinfo/view")
	public asinfoVO getView(@ModelAttribute("asinfoVO") asinfoVO vo) throws Exception{
		
		vo = asservice.selectAsInfo(vo) ;
		
		return vo;
		
	}
	
	// 메일 과정 후 이 핸들러 호출
	@ResponseBody
	@RequestMapping("/asinfo/save")
	public boolean save(@ModelAttribute("asinfoVO") asinfoVO vo) throws Exception{
		int c = 0;
		
		memberVO membervo = (memberVO)SessionUtil.getAttribute("member");
		
		vo.setId(membervo.getId());
		
		if(vo.getNum()!=null && !"".equals(vo.getNum()) ) {   //update
			c = asservice.updateAsinfo(vo);
			
		} else {  //insert
			c = asservice.insertAsinfo(vo);
			
		}
		
		
		// 메일 발송.
		// 메일과정후 이 핸들러 호출 일로 들어옴
		// 1. 메일 자체에 대한 내용을 만든다 - 수신자, 송신자 - 메일내용
		// 2. SMTP 설정정보를 만든다 - SMTP 관련정보 - 보안정보
		// 3. 쏜다
		if(vo.getMailYn()!=null && "Y".equals(vo.getMailYn())) {
			
			// 메일 마스터 VO : 1번의 송신자, 수신자 정보 즉 (메일 내용을 제외한) 메일을 전송하기 위한모든 자체 정보
			MailMstVO mailvo = new MailMstVO();
	         
	         mailvo.setTitle("AS 결과 메일 입니다.");      //제목
	         mailvo.setContent(vo.getAnswer());       //내용
	         // ------------------------------
	         
	         // 1번의 메일 내용
	         MailForm mform = new MailForm(); // 실제 메일내용, 사용자에게 보여지는 html 코드
	         // 메일 내용을 메일 VO에 싹 저장
	         mailvo.setContent(mform.getHtml(mailvo));
	         
	         String[] recipients = new String[1];		//수신자 메일
	         
	         // 수신자 정보를 넣어준다
	         recipients[0] = vo.getEmail();
	         
	         mailvo.setRecipients(recipients);
	         // ------------------------------
	         
	         MailSend mail = new MailSend();
	         // 실제로 메일을 보낸다!
	         mail.send(mailvo);
			
		}
		
		if(c > 0) {
			return true;
		}else {
			
			return false;
		}
		
		
		
	}
	
	@ResponseBody
	@RequestMapping("/asinfo/delete")
	public boolean delete(@ModelAttribute("asinfoVO") asinfoVO vo) throws Exception{
		int	c = asservice.deleteAsinfo(vo);
		
		if(c > 0) {
			return true;
		}else {
			
			return false;
		}
		

		// http 요청에 응답해주는 것이 웹서버
		// SMTP 메일 요청이 들어오면 웹서버는 아무런 반응을 하지 않는다
		// 내가 작성한 메일내용이 HTTP 패킷으로 만들어져서 웹서버로 날라감

		// 메일서버 간에 응답과 요청은 SMTP를 사용
		
		
	}
}
