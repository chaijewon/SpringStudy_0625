<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto;
   width:700px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
       <h1 class="text-center">내용보기</h1>
       <table class="table table-striped">
        <tr>
         <th width=20% class="text-center danger">번호</th>
         <td width=30% class="text-center">${vo.no }</td>
         <th width=20% class="text-center danger">작성일</th>
         <td width=30% class="text-center">
           <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
         </td>
        </tr>
        <tr>
         <th width=20% class="text-center danger">이름</th>
         <td width=30% class="text-center">${vo.name }</td>
         <th width=20% class="text-center danger">조회수</th>
         <td width=30% class="text-center">${vo.hit }</td>
        </tr>
        <tr>
         <th width=20% class="text-center danger">제목</th>
         <td colspan="3">${vo.subject }</td>
        </tr>
        <tr>
          <td colspan="4" class="text-left" valign="top" height="200">${vo.content }</td>
        </tr>
        <tr>
          <td colspan="4" class="text-right">
           <c:if test="${sessionScope.id!=null }">
            <a href="#" class="btn btn-xs btn-danger">답변</a>
            <a href="#" class="btn btn-xs btn-success">수정</a>
            <a href="#" class="btn btn-xs btn-info">삭제</a>
           </c:if>
            <a href="../board/list.do" class="btn btn-xs btn-warning">목록</a>
          </td>
        </tr>
       </table>
     </div>
   </div>
</body>
</html>








