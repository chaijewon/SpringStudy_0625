<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
  margin: 0px auto;
  width:350px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
     <h1>Login</h1>
     <form:form method="post" action="login.do" commandName="loginForm">
     <table class="table">
       <tr>
         <td width="20%" class="text-right">ID</td>
         <td width="80%" class="text-left">
           <form:input path="id" size="15"/>
         </td>
       </tr>
       <tr>
         <td colspan="2" class="text-center">
           <span style="color:red"><form:errors path="id"/></span>
         </td>
       </tr>
       <tr>
         <td width="20%" class="text-right">PW</td>
         <td width="80%" class="text-left">
           <form:input path="pwd" size="15"/>
         </td>
       </tr>
       <tr>
         <td colspan="2" class="text-center">
           <span style="color:red"><form:errors path="pwd"/></span>
         </td>
       </tr>
       <tr>
         <td colspan=2 class="text-center">
           <input type=submit value="로그인" class="btn btn-sm btn-danger">
           <input type=button value="취소" class="btn btn-sm btn-info">
         </td>
       </tr>
       
     </table>
     </form:form>
   </div>
   </div>
</body>
</html>




