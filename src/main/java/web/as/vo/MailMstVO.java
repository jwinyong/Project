package web.as.vo;

import java.io.Serializable;


public class MailMstVO implements Serializable {

	private String[] recipients;	//받는사람
	private int seq;
	private String rcvAddr;			//받는사람
	private String sendAddr;		//보내는사람
	private String refAddr;			//참조
	private String title;			//제목
	private String content;			//내용
	private String sendYn;			//발송여부
	
	public String[] getRecipients() {
		return recipients;
	}
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSendAddr() {
		return sendAddr;
	}
	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}
	
	public String getRefAddr() {
		return refAddr;
	}
	public void setRefAddr(String refAddr) {
		this.refAddr = refAddr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendYn() {
		return sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	public String getRcvAddr() {
		return rcvAddr;
	}
	public void setRcvAddr(String rcvAddr) {
		this.rcvAddr = rcvAddr;
	}
	
	
	
}