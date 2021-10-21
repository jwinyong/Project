<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>AS 관리- Register</title>


    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">


    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">관리자 등록</h1>
                            </div>
                            <form id='ff' name='ff'>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="id" name="id"
                                            placeholder="아이디">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user" id="name" name="name"
                                            placeholder="성명">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="addr" name="addr"
                                        placeholder="주소">
                                </div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="phone" name="phone"
                                            placeholder="전화번호">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user" id="email" name="email"
                                            placeholder="이메일">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="passwd" name="passwd" placeholder="비밀번호">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            id="repasswd" placeholder="비밀번호 확인">
                                    </div>
                                </div>
                                <a href="#" onclick="join()" class="btn btn-primary btn-user btn-block">
                                    관리자 등록
                                </a>
                                
                                
                            </form>
                            <hr>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    
    <script src="js/sb-admin-2.min.js"></script>

	<script>
	
		function join(){
			if($('#id').val()==''){
				alert("아이디를 입력하세요");
				return;
			}
			
			if($('#name').val()==''){
				alert("성명을 입력하세요");
				return;
			}
			
			if($('#passwd').val()==''){
				alert("패스워드를 입력하세요");
				return;
			}
			
			if($('#passwd').val()!=$('#repasswd').val()){
				alert("패스워드가 일치하지 않습니다.");
				return;
			}
			
			if(confirm('관리자 등록을 진행하시겠습니까?')){
			
				var formData = $("#ff").serialize();
				// 포스트 http로 메시지 파트
				$.ajax({
						data :formData,
						type:"POST",
						url : "/member/join",
						cache : false,
						success : join_Handler // 리턴값을 이리로 보내라!
				});
			}
		}
		
		function join_Handler(data){
			
			// true가 리턴되면
			if(data){
				alert("회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
				location.href="/";
			}
			else // false가 리턴되면
			{
				alert("중복된 아이디 입니다. ");
			}
		}
	
	</script>

</body>

</html>