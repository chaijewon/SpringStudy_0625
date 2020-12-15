<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
      *****1. core : JSP를 태그형으로 제작 (자바자체를 사용하지 않는다)
                                태그형으로 제어문 , URL , 변수 설정 
      *****2. fmt : 날짜,숫자변환 
      3. fn : String,Collection => 메소드 이용시 사용 
 --%>
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
<!-- 
   주소	서울특별시 강남구 논현로153길 24 끌레르빌 1F
지번 서울시 강남구 신사동 557-25 끌레르빌 1F
전화번호	02-540-0934
음식 종류	이자카야 / 오뎅 / 꼬치
가격대	3만원-4만원
영업시간	18:00 - 01:00
휴일	일
웹 사이트	식당 홈페이지로 가기
메뉴	
사시미8종 (2인)
41,000원
마구로치즈아에
19,000원
양갈비 스미비 야끼
23,000원
노도구로 시오 야끼
35,000원
규 스테이크
41,000원
 -->
<body>
  <div style="height: 30px"></div>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <%--
                StringTokenizer st=new StringTokenizer(vo.getPoster(),"^")
           --%>
          <c:forTokens var="img" items="${vo.poster }" delims="^">
            <td>
              <img src="${img }" width="100%">
            </td>
          </c:forTokens>
        </tr>
        <tr>
          <td colspan="5">
           <font size="12px">${vo.title }&nbsp;<span style="color:orange">${vo.score }</span></font>
           <p>
           <sub style="color:gray">
                        맛있다(${vo.good })&nbsp;&nbsp;괜찮다(${vo.soso })&nbsp;&nbsp;별로(${vo.bad })
           </sub>
           </p>
          </td>
        </tr>
      </table>
      <table class="table">
        <tr>
         <td width="10%" class="text-right">주소</td>
         <td width="90%">${vo.addr }</td>
        </tr>
        <tr>
         <td width="10%" class="text-right">전화</td>
         <td width="90%">${vo.tel }</td>
        </tr>
        <tr>
         <td width="10%" class="text-right">음식종류</td>
         <td width="90%">${vo.type }</td>
        </tr>
        <tr>
         <td width="10%" class="text-right">가격대</td>
         <td width="90%">${vo.price }</td>
        </tr>
        <tr>
         <td width="10%" class="text-right">메뉴</td>
         <td width="90%">
           <c:if test="${vo.menu!='no' }">
            <ul>
             <c:forTokens var="s" items="${vo.menu }" delims="^">
              <li>${s }</li>
             </c:forTokens>
            </ul>
           </c:if>
         </td>
        </tr>
        <tr>
          <td colspan="2" class="text-right">
           <a href="food.do?cateno=${vo.cateno }" class="btn btn-sm btn-primary">목록</a>
           <a href="recommand.do" class="btn btn-sm btn-success">추천</a>
           <a href="news.do" class="btn btn-sm btn-danger">뉴스</a>
          </td>
        </tr>
      </table>
    </div> 
  </div>
</body>
</html>









