<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
내가 bb 의 list 임
<table border="">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
<c:choose>
	<c:when test="${data.size()==0 }">	
	<tr>
		<td colspan="4" align="center">내용이 없습니다.</td>
	</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${data }" var="ee" varStatus="no">
			<tr>
				<td>${no.index+start }</td>
				
				<td>
				<c:if test="${ee.lev >0 }">
					<c:forEach begin="1" end="${ee.lev }">
						　
					</c:forEach>
					└	
				</c:if>
				<a a href="Detail?id=${ee.id }&page=${page}">${ee.title }</a>
				</td>
				<td>${ee.pname }</td>
				<td>
					<fmt:formatDate value="${ee.reg_date }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
	<tr>
		<td colspan="4" align="center">
			<a href="List?page=1">처음</a>
			<c:if test="${startPage>4 }">
				<a href="List?page=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="k">
				<c:choose>
					<c:when test="${k==page }">[${k }]</c:when>
					<c:otherwise><a href="List?page=${k }">${k }</a></c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${endPage!=total }">
				<a href="List?page=${endPage+1 }">다음</a>
				<a href="List?page=${total}">끝</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="right"><a href="InsertForm?page=${page }">글쓰기</a></td>
	</tr>
</table>