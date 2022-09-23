<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모</title>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>
		
		<section class="contents d-flex justify-content-center align-items-center">
			<div class="col-8 my-5">
				<h2 class="text-center">메모 입력</h2>
				
				<div class="d-flex mt-3">
					<label class="font-weight-bold col-2">제목 : </label> 
					<input type="text" class="form-control col-10" id="titleInput" value="${post.subject }">
				</div>
				
				<textarea rows="7" class="form-control mt-3" id="contentInput">${post.content }</textarea>
				
				<div>
					<img src="${post.imgPath }" class="w-100" alt="메모 이미지">
				</div>
				
				<div class="d-flex justify-content-between mt-4">
					<div>
						<a href="/post/list/view" class="btn btn-primary mr-2">목록으로</a>
						<button type="button" class="btn btn-danger">삭제</button>
					</div>
					<button type="button" class="btn btn-primary" id="updateBtn" data-post-id="${post.id }">수정</button>
				</div>
			</div>
		</section>
	
		<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
	</div>
	
	<script>
		$(document).ready(function(){
			
			$("#updateBtn").on("click", function(){
				let title = $("#titleInput").val();
				let content = $("#contentInput").val();
				
				let postId = $(this).data("post-id");
				
				// 유효성 검사
				if(title == "") {
					alert("수정할 제목을 입력하세요.");
					return;
				}			
				
				if(content == "") {
					alert("수정할 내용을 입력하세요.");
					return;
				}					
				
				$.ajax({
					type:"post"
					, url:"/post/update"
					, data:{"postId":postId, "title":title, "content":content}
					, success:function(data){
						if(data.result == "success") {
							location.href="/post/list/view";
						}else {
							alert("게시물 수정 실패");
						}
					}
					, error:function(){
						alert("게시물 수정 에러");
					}	
				});			
			});						
			
		});
	</script>
</body>
</html>