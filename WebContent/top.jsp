<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">

<div class="header">
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
		<a href="signup">登録する</a>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<a href="newMessage">新規投稿</a>
		<a href="manageUser">ユーザー管理</a>
		<a href="settings">設定</a>
		<a href="logout">ログアウト</a>
		<a href="signup">ユーザー登録</a>
	</c:if>
</div>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
		<div class="account">
			@<c:out value="${loginUser.account}" /><br><br><br>
		</div>
	</div>


<div class="messages">
	<c:forEach items="${messages}" var="message">
		<div class="message-icon">
			<div class="message">

				<div class="account-name">
					<span class="name"><c:out value="${message.name}" /></span><br>
					<span class="title"><c:out value="${message.title}" /></span><br>
					<span class="category"><c:out value="${message.category}" /></span>
				</div>

				<div class="text"><c:out value="${message.text}" /></div>
				<div class="date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
				=============================================
			</div>
		</div>
	</c:forEach>
</div>

</c:if>

<div class="copyright">Copyright(c)Satoshi Kimura</div>
</div>
</body>
</html>

