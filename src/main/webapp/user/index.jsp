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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/paper.css">
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/bootstrap.js"></script>
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
	<s:debug></s:debug>
	<form id="paperForm" name="paperForm"
		action="${pageContext.request.contextPath }/paper_findAllPaper.action"
		method=post>
		<nav class="navbar navbar-default" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">在线考试系统</a>
			</div>
	
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/paper_findAllPaper.action">试题列表</a></li>
                	<li class="active"><a href="${pageContext.request.contextPath}/studentPaper_allErrorSubjectPage.action?userid=<s:property value="userid"/>">查看错题</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${existUser.userid!=null}">
							<li>
								<a>
									<s:property value="#session.existUser.usertruename"/>
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user_logout.action">注销</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/user_login.action">登录</a></li>
						</c:otherwise>
					</c:choose>
	
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	
		<main class="container">
			<div class="panel panel-default">
				<div class="panel-heading text-center">
					<h3 class="panel-title">查看试题</h3>
				</div>
				<div class="panel-body">
					<div class="well">
						<table class="table">
							<thead>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>试题名称：</TD>
													<TD>
														<INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="pname" value="${pname}">
													</TD>
													<TD>
														<INPUT class=button id=sButton2 type=submit
															value=" 筛选 " name=sButton2>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<tr>
									<th>
										试题名称
									</th>
									<th>
										题目数量
									</th>
	
									<th style="width: 90px;">
										操作
									</th>
								</tr>
							</thead>
							<tbody>
							
								<s:iterator value="list">
									<tr>
										<td>
											<s:property value="pname"/>
										</td>
										<td>
											<s:property value="scount"/>
										</td>
	
										<td>
											<a href="${pageContext.request.contextPath}/paper_searchPaper?pid=<s:property value="pid"/>">开始答题</a>
										</td>
									</tr>
								</s:iterator>
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="totalCount"/></B>]条记录,[<B><s:property value="totalPage"/></B>]页
												,每页显示
												<select name="pageSize" onchange="to_page()">
													<option value="3" <s:if test="pageSize == 3">selected</s:if>>3</option>
													<option value="5" <s:if test="pageSize == 5">selected</s:if>>5</option>
													<option value="10" <s:if test="pageSize == 10">selected</s:if>>10</option>
												</select>
												条
												<s:if test="currPage != 1">
												[<A href="javascript:to_page(<s:property value="1" />)">首页</A>]
												[<A href="javascript:to_page(<s:property value="currPage - 1" />)">前一页</A>]
												</s:if>
												<B>
													<s:iterator var="i" begin="1" end="totalPage">
														<s:if test="#i == currPage">
															<s:property value="#i"/>
														</s:if>
														<s:else>
															<a href="javascript:to_page('<s:property value="#i"/>')"><s:property value="#i"/></a> 
														</s:else>
													</s:iterator>
												</B>
												<s:if test="currPage != totalPage">
												[<A href="javascript:to_page(<s:property value="currPage + 1" />)">后一页</A>] 
												[<A href="javascript:to_page(<s:property value="totalPage" />)">尾页</A>]
												</s:if>
												到
												<input type="text" size="3" id="page" name="currPage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</form>
</body>

</html>