<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>
<c:if test="${msg!=null }">
alert("${msg }");
</c:if>
	function fileDelete() {
		if(confirm('파일을 삭제하시겠습니까? \n 삭제된 파일은 복구가 불가능합니다.'))
		{
			var frm = document.frm;
			frm.action="FileDelete";
			frm.submit();
		}
	}
</script>
<form name="frm" action="ModifyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name ="id" value="${data.id }">
<input type="hidden" name ="page" value="${param.page }">
	<table border="">
		<tr>
			${data.seq }
			<td>제목</td>
			<td><input type="text" name="title" value="${data.title}" ></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name ="pname" value="${data.pname }" ></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name = "pw"></td>
		</tr>
		<tr>
<c:choose>
	<c:when test="${data.seq==0 }">
				<td>파일</td>
				<td>
				<c:choose>
					<c:when test="${data.upfile!='' }">
						${data.upfile }<input type="button" value="파일삭제" onclick="fileDelete()">
						<input type="hidden" name = "file" value="${data.upfile }">
						<input type="hidden" name ="seq" value="${data.seq }">
					</c:when>
					<c:otherwise>
						<input type="file" name="file">
					</c:otherwise>
				</c:choose>
				</td>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="file" value="">
	</c:otherwise>
</c:choose>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="5" cols="30" name ="content">${data.content}</textarea></td>
		</tr>
		<tr>
			<td>
				<a href="ModifyForm?id=${data.id }">
					<input type="button" value="초기화">
				</a>
			</td>
			<td><input type="submit" value="수정"></td>
		</tr>
		<tr>
			<td colspan="2"><a href="Detail?id=${param.id }&page=${param.page }">이전으로</a></td>
		</tr>
	</table>
</form>
