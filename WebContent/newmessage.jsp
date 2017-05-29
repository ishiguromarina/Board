<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<a href="logout">ログアウト</a>
<br />

<div class="name"><h3>---新規投稿画面---</h3></div>

<div class="main-contents">
	<c:if test="${not empty errorMessages}">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="messages">
				<li><c:out value="${messages}"/>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>

		<form action="newMessage" method="post">

		<label for="name">タイトル</label>
		<input name="title" id="title" /><br />

		<label for="name">カテゴリー</label>
		<input name="category" id="category" /><br /><br />

			投稿内容<br />
			<textarea name="message" cols="100" rows="5" class="tweet-box"></textarea>
			<br />
			<input type="submit" value="投稿する">（1000文字まで）
		</form>

</div>

<div class="copyright">Copyright(c)Satoshi Kimura</div>

</body>
</html>