<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="DeleteReg" method="post">
<input type="hidden" name="id" value="${param.id }"/>
<input type="hidden" name="page" value="${param.page }">
	<table border="">
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="확인하기">
			</td>
			<td>
				<a href="Detail?id=${param.id }&page=${param.page }">이전으로</a>
			</td>
		</tr>
	</table>
</form>
