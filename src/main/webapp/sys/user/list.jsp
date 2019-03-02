<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
		<SCRIPT language=javascript>
			function to_page(page){
				if(page){
					$("#page").val(page);
				}
				document.userForm.submit();
				
			}
		</SCRIPT>
	</head>

	<body class="content1">
		<div class="container-fluid">
			<div class="row-fluid">
				<form class="form-inline" method="post" id="userForm" name="userForm"
					action="${pageContext.request.contextPath}/user_list.action">
					<input class="input-xlarge" placeholder="用户名..." name="username"
						type="text" value="${username}">
					<input class="btn icon-search" type="submit" value="查询" />
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/user_add.action"> <i
						class="icon-plus"></i> 新增 </a>
				

				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>
									用户名
								</th>
								<th>
									用户密码
								</th>
								<th>
									用户真实名字
								</th>
								<th>
									用户状态
								</th>
								<th style="width: 90px;">
									编辑
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>
										${item.username}
									</td>
									<td>
										${item.userpwd}
									</td>
									<td>
										${item.usertruename}
									</td>
									<td>
										<c:choose>
											<c:when test="${item.userstate==\"1\"}">
												正常		
											</c:when>
											<c:otherwise>锁定</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/user_edit.action?userid=${item.userid}">编辑</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table>
						<TR>
							<TD><SPAN id=pagelink>
									<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
										共[<B><s:property value="totalCount"/></B>]条记录,[<B><s:property value="totalPage"/></B>]页
										,每页显示
										<select name="pageSize" onchange="to_page()">
											<option value="3" <s:if test="pageSize == 3">selected</s:if>>3</option>
											<option value="5" <s:if test="pageSize == 5">selected</s:if>>5</option>
											<option value="10" <s:if test="pageSize == 10">selected</s:if>>10</option>
										</select>
										条
										<s:if test="currPage != 1">
										[<A href="javascript:to_page(<s:property value="1" />)" style="disabled:true">首页</A>]
										[<A href="javascript:to_page(<s:property value="currPage - 1" />)" style="disabled:true">前一页</A>]
										</s:if>
										<B>
											<s:iterator var="i" begin="1" end="totalPage">
												<s:if test="#i == currPage">
													<s:property value="#i"/>
												</s:if>
												<s:else>
													<a href="javascript:to_page('<s:property value="#i"/>')" style="disabled:true"><s:property value="#i"/></a> 
												</s:else>
											</s:iterator>
										</B>
										<s:if test="currPage != totalPage">
										[<A href="javascript:to_page(<s:property value="currPage + 1" />)" style="disabled:true">后一页</A>] 
										[<A href="javascript:to_page(<s:property value="totalPage" />)" style="disabled:true">尾页</A>]
										</s:if>
										到
										<input type="text" size="3" id="page" name="currPage" />
										页
										
										<input type="button" value="Go" onclick="to_page()"/>
									</DIV>
							</SPAN></TD>
						</TR>
					</table>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
