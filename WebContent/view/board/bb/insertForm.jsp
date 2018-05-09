<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="InsertReg" method="post" enctype="multipart/form-data">
	<table border="">
		<tr>
			<td>제목</td>
			<td><input type="text" name ="title"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname" /></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pw" /></td>
		</tr>
		<tr>
			<td>파일</td>
			<td><input type="file" name ="file"></td>
		</tr>
		<tr>
			<td></td>
			<td><textarea rows="5" cols="30" name ="content"> 이잉?</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송">
				<input type="reset" value="초기화">
				<a href ="List?page=${param.page }">목록으로</a>
			</td>
		</tr>
	</table>
</form>