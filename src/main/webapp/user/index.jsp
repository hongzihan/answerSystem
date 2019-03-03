<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>

<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<title>在线答题</title>
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/paper.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap4.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap4.min.js"/> --%>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<%-- <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/bootstrap.js"></script> --%>
	<SCRIPT language=javascript>
		function to_page(page){
			if(page){
				$("#page").val(page);
			}
			document.paperForm.submit();
			
		}
	</SCRIPT>
</head>

<body>
	<%-- <s:debug></s:debug> --%>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">在线考试系统</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		      	<a class="nav-link" href="${pageContext.request.contextPath}/paper_findAllPaper.action">试题列表</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/studentPaper_allErrorSubjectPage.action?userid=${existUser.userid}">查看错题</a>
		      </li>
		    </ul>
		    <ul class="navbar-nav my-2 my-lg-0">
		    	<c:choose>
					<c:when test="${existUser.userid!=null}">
						<li>
							<a class="nav-link">
								${existUser.usertruename}
							</a>
						</li>
						<li>
							<a class="nav-link" href="${pageContext.request.contextPath}/user_logout.action">注销</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/user_login.action">登录</a></li>
					</c:otherwise>
				</c:choose>
		    </ul>
		  </div>
		</nav>
						
  	<form id="paperForm" name="paperForm"
		action="${pageContext.request.contextPath }/paper_findAllPaper.action"
		method=post>
		
		<div class="container">
			<h3>查看试题 <span class="badge badge-secondary">View Paper</span></h3>
			<!-- 搜索部分 -->
			<nav class="navbar-light bg-light">
			    <input class="mr-sm-2" type="search" placeholder="搜索试题……" aria-label="Search" name="pname" value="${pname}">
			    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</nav>
			<!-- 数据展示部分 -->
			<table class="table text-center">
			  <thead>
			    <tr>
			      <th scope="col">试题名称</th>
			      <th scope="col">题目数量</th>
			      <th scope="col">操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  <!-- 主数据 -->
			  	<s:iterator value="list">
			  		<tr>
				      <td><s:property value="pname"/></td>
				      <td><s:property value="scount"/></td>
				      <td><a href="${pageContext.request.contextPath}/paper_searchPaper.action?pid=<s:property value="pid"/>&pname=${pname}">开始答题</a></td>
				    </tr>
				</s:iterator>
				<!-- 分页 -->
				<tr>
					<td>
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-end">
						  	<li class="page-item disabled">
						  		<a class="page-link">共 <B><s:property value="totalCount"/></B> 条记录, <B><s:property value="totalPage"/></B> 页</a>
						  	</li>
						  	<s:if test="currPage != 1">
						    <li class="page-item">
						      <a class="page-link" href="javascript:to_page(<s:property value="1" />)" tabindex="-1">首页</a>
						    </li>
						    <li class="page-item">
						      <a class="page-link" href="javascript:to_page(<s:property value="currPage - 1" />)" tabindex="-1">前一页</a>
						    </li>
						    </s:if>
						    
						    <s:iterator var="i" begin="1" end="totalPage">
								<s:if test="#i == currPage">
									<li class="page-item disabled"><a class="page-link"><s:property value="#i"/></a></li>
								</s:if>
								<s:else>
									<li class="page-item"><a class="page-link" href="javascript:to_page('<s:property value="#i"/>')"><s:property value="#i"/></a></li>
								</s:else>
							</s:iterator>
						    
						    <s:if test="currPage != totalPage">
							    <li class="page-item">
							      <a class="page-link" href="javascript:to_page(<s:property value="currPage + 1" />)" tabindex="-1">后一页</a>
							    </li>
							    <li class="page-item">
							      <a class="page-link" href="javascript:to_page(<s:property value="totalPage" />)" tabindex="-1">尾页</a>
							    </li>
						    </s:if>
							<li class="page-item">
								到
								<input class="btn btn-outline-success my-2 my-sm-0" type="text" size="1" id="page" name="currPage" value="${currPage}"/>
								页
							</li>	
							<li class="page-item">
								<input class="btn btn-outline-success my-2 my-sm-0" type="button" value="Go" onclick="to_page()"/>
							</li>
						  </ul>
						</nav>
					</td>
				</tr>
			  </tbody>
			</table>
		</div>
	</form>
	</div>
</body>

</html>