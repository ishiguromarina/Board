<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>
<a href="./">ホーム</a>
<a href="newMessage">新規投稿</a><br><br><br>

<table border = 1>
  <caption>ユーザー一覧</caption>
  <tr>
    <th>ログインID</th>
    <th>名前</th>
    <th>支店</th>
    <th>役職</th>
    <th>管理</th>
  </tr>


  <c:forEach items="${user}" var="user">
  	<tr>
    	<td><span class="account"><c:out value="${user.account}" /></span></td>
    	<td><span class="name"><c:out value="${user.name}" /></span></td>

    	<c:forEach items="${branch}" var="branch">
    		<c:if test="${user.branchId == branch.id}">
    			<td><span class="${branch.id}"><c:out value="${branch.name}" /></span></td>
   			</c:if>
    	</c:forEach>

    	<c:forEach items="${department}" var="department">
    		<c:if test="${user.departmentId == department.id}">
    			<td><span class="${department.id}"><c:out value="${department.name}" /></span></td>
   			</c:if>
   		</c:forEach>

   		<td><c:if test="${user.isStopped == 0}">
				<input type="button" value="停止" onClick=""><br>
			</c:if>

   			<c:if test="${user.isStopped != 0}">
				<input type="button" value="復活" onClick=""><br>
			</c:if>


			<form action="setting" method="get">
				<input type="hidden" name="id" value="${user.id}">
				<input type="button" value="編集"><br>
			</form>

			<input type="button" value="削除" onClick=""><br>

    	</td>
  	</tr>
  </c:forEach>

</table>

</body>
</html>