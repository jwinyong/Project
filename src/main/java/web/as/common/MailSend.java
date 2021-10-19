package web.as.common;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import web.as.vo.MailMstVO;




public class MailSend { 
	static Properties mailServerProperties; 
	static Session getMailSession; 
	static MimeMessage generateMailMessage; 
	
	// 세팅 완료 데이터를 넘김
	public void send(MailMstVO vo) throws Exception{
        String[] emailList = vo.getRecipients();			// 메일 받는사람 리스트 
        String emailMsgTxt = vo.getContent(); 				// 내용
        String emailSubjectTxt = vo.getTitle();				// 제목
        
        // 메일보내기 
        postMail(emailList, emailSubjectTxt, emailMsgTxt);
	}

	// SMTP 프로토콜
	private void postMail(String recipients[], String subject, String msgText) throws MessagingException, UnsupportedEncodingException, Exception {
		
		// 메일을 보내기 위핸 로컬 호스트 인증 정보
		// 서버 정보를 리턴해준다
		Properties prop = System.getProperties();       // 시스템정보를 가져온다
        prop.put("mail.smtp.starttls.enable", "true");  
        prop.put("mail.smtp.host", "smtp.gmail.com");   // 호스트 정보
        prop.put("mail.smtp.auth", "true");             // 보안
        prop.put("mail.smtp.port", "587");              // 포트
        // --------------------------------------------- 1
        
        // 무언가 객체를 만들었다!
        // 메일을 보내기 위한 로컬 호스트 보안정보
        Authenticator auth = new MailAuth();		//구글 권한 가져오기
        
        // 세션 정보 생성 이 세션이 갖고 있는 정보는 세션이 메일을 주고 받는 것이 아니라! G-mail 서버와 통신하기 위한 정보를 들고있다! 직원개념
        Session session = Session.getDefaultInstance(prop, auth);		//메일 세션 생성
        
        // 구글 메일 세션 가져오는 것
     	// MimeMessage : 메일을 보내는데 있어서 가장 전통적인, 표준적인 방법
        MimeMessage msg = new MimeMessage(session);	//메일 발송 메시지 클래스
    
        try {
            msg.setSentDate(new Date());	//보내는 날짜
            
            InternetAddress[] to = new InternetAddress[recipients.length];	//받는 사람 목록
            
            // 보내는 주소를 RFC822 형태로 만들어준다
            msg.setFrom(new InternetAddress(Constant.MAIL_SENDER, Constant.MAIL_SENDER_NM));
            
            // 받는 사람이 여럿인 경우 for문을
            for (int i = 0; i < recipients.length; i++) {
            	if(recipients[i]!=null && !"".equals(recipients[i])) {
            		to[i] = new InternetAddress(recipients[i]);
            	}
	        }
	        msg.setRecipients(Message.RecipientType.TO, to);
	        
            //제목            
            msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(msgText, "text/html;charset=utf-8");

	        //creates multi-part
	        Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	        
            Transport.send(msg);
            
            //--------------------------------------------------------- 가장 전형적인 메일전송 구조
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
        
        // 정리
    	// 메일을 보내기 위해
    	// 폼을 우선 만들고,
    	// 메일정보와 보안정보!
    	// 이 정보를 들고 실제로 전송하기 위해 세션도 만듬
    	// 실제로 메일을 보내기 위해서!
                
    }
	
}

