<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
   margin: 0px auto;
   width:900px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.recomm').click(function(){
		let fd=$(this).text()
		$.ajax({
			type:'POST',
			url:'recommand_result.do',
			data:{"fd":fd},
			success:function(result)
			{
				$('#print').html(result)
			}
		})
	})
})
</script>
</head>
<!-- 
     ==========================
      jquery.js => Ajax
      React.js(Redux)
      CSS => 약간 수정만 가능 
      ========================= Front-End
            자바 : IO(약간) => AI 데이터 수집 (파일) , 컬렉션 
      JSP : EL,JSTL,MVC
            => Cookie,Session
      Spring : DI , AOP , MVC 
      DB : DML,TCL(COMMIT,ROLLBACK)
      ========================= Back-End
             코틀린 : 사용법 (web연결)
      ========================= Mobile
 -->
<body>
  <div style="height:30px"></div>
  <div class="container">
    <div class="row">
      <span class="recomm btn btn-lg btn-danger">봄</span>
      <span class="recomm btn btn-lg btn-success">여름</span>
      <span class="recomm btn btn-lg btn-info">가을</span>
      <span class="recomm btn btn-lg btn-primary">겨울</span>
    </div>
    <div style="height: 10px"></div>
    <div class="row" id="print">
     
    </div>
  </div>
</body>
</html>













