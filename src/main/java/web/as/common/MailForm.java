package web.as.common;

import web.as.vo.MailMstVO;

public class MailForm {
                  // 메일을 쓴 내용을 받기 위해 MailMstVO를 받는다
	public String getHtml(MailMstVO vo){
		// 스트링에 html 태그를 넣고 있다!
		// 즉, html 데이터를 만들고 있다
		String html="";
		
		html+="<html>";
		html+="<head>";
		html+="<meta charset=\"utf-8\">";
		html+="</head>";
		html+="<body style=\"padding: 0px; margin: 0px; background-color:#eff3f8;\">";
		
		html+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\"><tr><td align=\"center\" bgcolor=\"#eff3f8\">";

		html+="<table style=\"height: 40px;\">&nbsp;</table>";

		html+="<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"max-width: 750px; min-width: 300px; border:1px solid #61a0c9\">";
		html+="	<tr>";
		html+="        <td align=\"center\" bgcolor=\"#ffffff\">";
		html+="        	<table style=\"height: 10px;padding-left:10px;\">&nbsp;</table>";
		
		        	  
		html+="            <table border=\"1\" style=\"width:90%; border-collapse:collapse; border-spacing:0; font-size:12px;\">";
		
		html+="              <tr>";
		html+="								<td width=\"100px\" height=\"30px\" style=\"background: #f5f5f5;text-align:center; \">내용</td>";
		html+="                <td >"+vo.getContent()+"</td>";
		html+="              </tr>";
		html+="            </table>";
		html+="            <table style=\"height: 10px;\">&nbsp;</table>	";
		html+="        </td>";
		html+="    </tr>";
		

		html+="</table>";
		 
		html+="</td></tr>";
		html+="</table>";
		
		html+="</body>";
		html+="</html>";
		return html;
	}
	
	private String getBlank(int idx){
		String rtn="";
		for(int i = 0; i < idx; i++){
			rtn+="&nbsp;";
		}
		return rtn;
	}
}
