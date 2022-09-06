<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모-메모입력</title>
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
					<input type="text" class="form-control col-10" id="titleInput">
				</div>
				
				<textarea rows="7" class="form-control mt-3" placeholder="내용을 입력해주세요" id="contentInput"></textarea>
				
				<input type="file" class="mt-3" id="fileInput">
				
				<div class="d-flex justify-content-between mt-3">
					<a href="/post/list/view" class="btn btn-primary">목록으로</a>
					<button type="button" class="btn btn-primary" id="saveBtn">저장</button>
				</div>
			</div>
		</section>
	
		<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
	</div>
	
	<script>
		$(document).ready(function(){
			$("#saveBtn").on("click", function(){
				let title = $("#titleInput").val();
				let content = $("#contentInput").val();
				
				if(title == "") {
					alert("제목을 입력하세요.");
					return;
				}
				
				if(content == "") {
					alert("내용을 입력하세요.");
					return;
				}
				
				var formData = new FormData();
				formData.append("title", title);
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]);
				
				$.ajax({
					type:"post"
					, url:"/post/create"
					, data:formData
					, enctype:"multipart/form-data"
					, processData:false
					, contentType:false
					, success:function(data){
						if(data.result == "success"){
							location.href = "/post/list/view";
						} else {
							alert("메모 입력 실패");
						}
					}
					, error:function(){
						alert("메모 작성 에러");
					}
							
				});
			});
			
			
		});
	</script>
</body>
</html>