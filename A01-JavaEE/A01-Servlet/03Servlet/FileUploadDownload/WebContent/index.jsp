<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>文件上传和下载</title>
</head>
<body>
	<h4>单个文件上传</h4>
	<form action="<%=request.getContextPath() %>/upload/up01" method="post"  enctype="multipart/form-data">
    	<input type="file" name="uploadFile">
   		<input type="submit" value="上传单个文件"> <br/>
   		
   	</form>
	
	<h4>批量文件上传</h4>
	<form action="<%=request.getContextPath() %>/upload/up02" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFileMultiple01">
		<input type="file" name="uploadFileMultiple02">
		<input type="file" name="uploadFileMultiple03">
		<input type="submit" value="批量上传文件"> <br/>
	</form>
	
	<h4>文件下载</h4>
	<form action="<%=request.getContextPath() %>/download/down01" method="post">
		测试文件：<input type="submit" value="点击下载">
	</form>
	
</body>
</html>