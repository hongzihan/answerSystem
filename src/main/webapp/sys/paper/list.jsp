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

		<title>试卷管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
		<SCRIPT language=javascript>
		function to_page(page){
			if(page){
				$("#page").val(page);
			}
			document.paperForm.submit();
			
		}
	</SCRIPT>
	</head>

	<body class="content1">
		<div class="container-fluid">
			<div class="row-fluid">
				<form class="form-inline" method="post" name="paperForm" id="paperForm"
					action="paper_list.action">
					<input class="input-xlarge" placeholder="用户名..." name="pname"
						type="text" value="${pname}">
					<input class="btn icon-search" type="submit" value="查询" />
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/paper_add.action"> <i
						class="icon-plus"></i> 新增 </a>

				<div class="well">
					<table class="table">
						<thead>
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
							<c:forEach items="${list}" var="item">
								<tr>
									<td>
										${item.pname}
									</td>
									<td>
										${item.scount}
									</td>

									<td>
										<a href="${pageContext.request.contextPath}/paper_viewPaper?pid=${item.pid}">查看试题</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagination pagination-right">
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
					
					
						<%-- <ul>
							<li>
								<a>共计：${totalPage}页/${totalCount}条记录</a>
							</li>
							
							<li>
								<c:if test="${currPage==1}" var="fp">
									<a style="disabled:true">上一页</a>
								</c:if>
								<c:if test="${!fp}">
									<a href="<%=basePath%>sys/paper?cmd=list&index=${pager.pagectrl.currentindex-1}">上一页</a>
								</c:if>
							</li>						
							
							<c:forEach begin="${pager.pagectrl.minpage}" step="1" end="${pager.pagectrl.maxpage}" var="index">
							<li>
								<c:if test="${pager.pagectrl.currentindex==index}" var="t">
									<a style="color:red;background-color:#bbb">${index}</a>
								</c:if>
								<c:if test="${!t}">
								<a href="<%=basePath%>sys/paper?cmd=list&index=${index}">${index}</a>
								</c:if>
							</li>
							</c:forEach>
							
							<li>
								<c:if test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}" var="fp">
									<a style="disabled:true">下一页</a>
								</c:if>
								<c:if test="${!fp}">
									<a href="<%=basePath%>sys/paper?cmd=list&index=${pager.pagectrl.currentindex+1}">下一页</a>
								</c:if>
							</li>
						</ul> --%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
