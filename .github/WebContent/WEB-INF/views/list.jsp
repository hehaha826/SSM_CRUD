<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-3.5.0.min.js"></script>
</head>
<body>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary btn-sm">新增</button>
				<button class="btn btn-danger btn-sm">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<th>${emp.empId }</th>
							<th>${emp.empName }</th>
							<th>${emp.gender=="M"?"男":"女" }</th>
							<th>${emp.email }</th>
							<th>${emp.department.deptName }</th>
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 分页 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">当前${pageInfo.pageNum }页，共${pageInfo.pages }页，总记录数：${pageInfo.total }
			</div>
			<!-- 分页条 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
						<c:if test="${pageInfo.hasPreviousPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>


						<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
							<c:if test="${page_Num ==pageInfo.pageNum }">
								<li class="active"><a href="#">${page_Num }</a></li>
							</c:if>
							<c:if test="${page_Num !=pageInfo.pageNum }">
								<li><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${pageInfo.hasNextPage }">
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1 }" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
						</c:if>
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages }">末页</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>