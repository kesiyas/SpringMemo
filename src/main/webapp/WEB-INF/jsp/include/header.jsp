<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<header class="d-flex align-items-center justify-content-between">
			<h1 class="ml-3">메모</h1>
			<c:choose>
				<c:when test="${not empty userId }">
					<div class="mr-3"> ${userName }님 <a href="/user/signout">로그아웃</a></div>
				</c:when>
				<c:otherwise>
					<div class="mr-3"> <a href="/user/signout">로그인</a></div>
				</c:otherwise>
			</c:choose>
		</header>