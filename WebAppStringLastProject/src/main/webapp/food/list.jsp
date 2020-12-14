<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row1{
   margin: 0px auto;
   width:900px;
}
</style>
</head>
<body>
  <div style="height:30px"></div>
  <div class="container-fluid">
    <div class="row row1">
      <a href="list.do?no=1" class="btn btn-sm btn-danger">믿고 보는 맛집 리스트</a>
      <a href="list.do?no=2" class="btn btn-sm btn-success">지역별 인기 맛집</a>
      <a href="list.do?no=3" class="btn btn-sm btn-primary">메뉴별 인기 맛집</a>
    </div>
    <div style="height:30px"></div>
    <div class="row">
      <c:forEach var="vo" items="${list }">
       <div class="col-md-4">
	      <div class="thumbnail">
	        <a href="food.do?cateno=${vo.no }">
	          <img src="${vo.poster }" alt="Lights" style="width:100%">
	          <div class="caption">
	            <p>${vo.title }</p>
	            <p><sup>${vo.subject }</sup></p>
	          </div>
	        </a>
	      </div>
	    </div>
      </c:forEach>
    </div>
  </div>
</body>
</html>















