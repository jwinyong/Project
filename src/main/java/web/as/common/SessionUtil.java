package web.as.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

// 일반적인 어노테이션을 사용하지 않는다
public class SessionUtil {

	/**
     * attribute 값을 가져 오기 위한 method
     *
     * @param String  attribute key name
     * @return Object attribute obj
     */
    public static Object getAttribute(String name) throws Exception {
                                            // 정보를 가져올 수 있는 펑션            
    	return (Object)RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
 
    /**
     * attribute 설정 method
     *
     * @param String  attribute key name
     * @param Object  attribute obj
     * @return void
     */
    public static void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }
 
    /**
     * 설정한 attribute 삭제
     *
     * @param String  attribute key name
     * @return void
     */
    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
 
    /**
     * session id
     *
     * @param void
     * @return String SessionId 값
     */
    public static String getSessionId() throws Exception  {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
    

	/**
     * 세션값을 String으로 반환
     * @param request
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(HttpServletRequest req, String key, String defaultValue) {
        HttpSession session = req.getSession();
        String sessionValue = defaultValue ;
        
        if(session == null) {
            return sessionValue;
        }

        try{
            if( session.getAttribute(key) == null ){
                sessionValue = defaultValue;
            }else{
                sessionValue = (String)session.getAttribute( key );
            }
        }catch(Exception e){
            sessionValue = defaultValue;
        }
        
        return sessionValue;
    }

    public static String getString(HttpServletRequest req, String key) {
        return SessionUtil.getString(req, key, "");
    }
    
    /**
     * 세션값을 Object로 반환
     * @param req
     * @param key
     * @return
     */
    public static Object getObject(HttpServletRequest req, String key) {
        HttpSession session = req.getSession();
        return session.getAttribute(key); 
    }
    
    /**
     * 세션값 설정
     * @param req
     * @param key
     * @param value
     */
    public static void set(HttpServletRequest req, String key, Object value) {
        HttpSession session = req.getSession();
        session.setAttribute(key, value);
    }
    
    /**
     * 세션값 제거
     * @param req
     * @param key
     */
    public static void remove(HttpServletRequest req, String key) {
        HttpSession session = req.getSession();
        session.removeAttribute(key);
    }
    
    /**
     * 세션전체 무효화
     * @param req
     */
    public static void removeAll(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
    }
    
    
    /**
     *  UNIQ 갑 
     * */
    public static String getUuid() {
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
	 //세션에 설정된 UTC 시간
	 public static int getUtcHour() {
		 return 0;
	 }
	 
	 //세션에 설정된 UTC 분
	 public static int getUtcMinute() {
		 return 0;
	 }
	 
}
