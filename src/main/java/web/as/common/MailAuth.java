package web.as.common;

import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;
 

// 구글 권한을 얻어오는 클래스
public class MailAuth extends Authenticator{
    
    PasswordAuthentication pa;
    
    public MailAuth() 
    {
        String mail_id = Constant.MAIL_ID;
        String mail_pw = Constant.MAIL_PWD;
                                        // 내 메일 id, pw
        pa = new PasswordAuthentication(mail_id, mail_pw);
        // 그냥 id와 password를 전송하면 되지,
        // 왜 클래스를 만들어서 보낼까?
        // - 그 이유는 바로 보안문제 때문에! → 나의 pw를 암호화하여 감추기 위해서이다!
        // PasswordAuthentication 즉, 나의 패스워드를 암호화
    }
    
    public PasswordAuthentication getPasswordAuthentication() 
    {
        return pa;
    }

}
