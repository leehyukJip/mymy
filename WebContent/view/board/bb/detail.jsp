<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table border="">
	<tr>
		<td>id</td>
		<td>${data.id }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${data.pname }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${data.title }</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${data.cnt }</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><fmt:formatDate value="${data.reg_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<c:if test="${data.upfile!='' }">
	<tr>
		<td>파일</td>
		<td>
		<c:choose>
			<c:when test="${data.img }">
				<img src="../upload/${data.upfile }"></a>
			</c:when>
			<c:otherwise>
				<a href="FileDown?file=${data.upfile }">${data.upfile }</a>
			</c:otherwise>
		
		</c:choose>
		</td>
	</tr>
	</c:if>
	<tr>
		<td>내용</td>
		<td><ct:conBr>${data.content }</ct:conBr></td>
	</tr>
	<tr>
		<td><a href="List?page=${param.page }">목록으로</a></td>
		<td><a href="DeleteForm?id=${data.id}&page=${param.page }">삭제하기</a></td>
	</tr>
	<tr>
		<td><a href="ModifyForm?id=${data.id}&page=${param.page }">수정하기</a></td>
		<td><a href="ReplyForm?id=${data.id}&page=${param.page }">댓글</a></td>
	</tr>
</table>