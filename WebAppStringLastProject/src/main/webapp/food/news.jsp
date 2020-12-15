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
.row{
   margin: 0px auto;
   width:900px;
}
</style>
</head>
<body>
   <div style="height: 30px"></div>
   <div class="container">
     <div class="row">
      <table class="table">
        <tr>
          <td>
           <form method=post action="news.do">
            <input type=text name=fd size=15 class="input-sm">
            <input type=submit value="검색" class="btn btn-sm btn-success">
           </form>
          </td>
        </tr>
      </table>
     </div>
     <div style="height: 10px"></div>
     <div class="row">
      <table class="table">
       <tr>
         <td>
           <c:forEach var="vo" items="${list }">
             <table class="table">
               <tr>
                <td>
                 <span style="color:orange">${vo.title }</span>
                </td>
               </tr>
               <tr>
                 <td><a href="${vo.link }" target="_blank">${vo.description }</a></td>
                 <%-- kotlin => WebView --%>
               </tr>
               <tr>
                 <td class="text-right">${vo.author }</td>
               </tr>
             </table>
           </c:forEach>
         </td>
       </tr>
      </table>
     </div>
   </div>
</body>
</html>












