<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<img src="/resources/images/xxxx.png" />

<table id="asTable">
	<tr><td>번호</td>
		<td>제목</td>
		<td>제품명</td>
	</tr>
</table>


<script>

	//Ajax call
	//as/getlist
	
	function getList(){
		

		
		var formData = $("#ff").serialize();
		
		$.ajax({
				data :formData,
				type:"POST",
				url : "/as/getList",
				cache : false,
				success : dataSet
		});
	}
	
	function dataSet(data, status){
		for(var i=0;i<data.length;i++){
			
			$('#asTable').append("<tr><td>"+(i+1)+"</td><td>"+data[i].title+"</td></tr>");
			
		}
	}
	
</script>
</body>
</html>