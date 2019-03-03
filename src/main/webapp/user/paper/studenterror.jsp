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
    <title>在线答题</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/paper.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/bootstrap.js"></script>
    <SCRIPT language=javascript>
		function to_page(page){
			if(page){
				$("#page").val(page);
			}
			document.errorPaperForm.submit();
			
		}
	</SCRIPT>
</head>

<body>

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
	
	<div class="container">
		<h3>错题库 <span class="badge badge-secondary">Error Subject</span></h3>
		<!-- 数据展示部分 -->
		  <!-- 主数据 -->
		  	<s:iterator value="list">
		  		<div class="subject" data-sid="<s:property value="sid"/>" data-key="<s:property value="skey"/>" data-skey="<s:property value="studentkey"/>">
                    <li> <s:property value="scontent"/></li>
                    <ol>
                        <li><label data-value="A"><s:property value="sa"/></label></li>
                        <li><label data-value="B"><s:property value="sb"/></label></li>
                        <li><label data-value="C"><s:property value="sc"/></label></li>
                        <li><label data-value="D"><s:property value="sd"/></label></li>
                    </ol>
                </div>
			</s:iterator>
			<!-- 分页 -->
		<table>
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
	</div>





<%-- <s:property value="list.size"/>
	<form id="paperForm" name="errorPaperForm"
		action="${pageContext.request.contextPath }/studentPaper_errorSubjectPage.action?userid=${existUser.userid}&spid=<s:property value="spid"/>"
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
                <li class="active"><a href="${pageContext.request.contextPath}/studentPaper_allErrorSubjectPage.action?userid=${existUser.userid}">查看错题</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${existUser.userid!=null}">
							<li>
								<a>
									${existUser.usertruename}
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
    </nav> --%>

    <%-- <main class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">
                    <c:out value="${pname}"></c:out>
                </h3>
            </div>
            <div class="panel-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#Radio">错题库</a>
                        </h4>
                    </div>
                    <s:if test="list.size>0">
                    <div id="Radio" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ol>
                            	<s:iterator value="list">
                            		<div class="subject" data-sid="<s:property value="sid"/>" data-key="<s:property value="skey"/>" data-skey="<s:property value="studentkey"/>">
                                        <li> <s:property value="scontent"/></li>
                                        <ol>
                                            <li><label data-value="A"><s:property value="sa"/></label></li>
                                            <li><label data-value="B"><s:property value="sb"/></label></li>
                                            <li><label data-value="C"><s:property value="sc"/></label></li>
                                            <li><label data-value="D"><s:property value="sd"/></label></li>
                                        </ol>
                                    </div>
                            	</s:iterator>
                            </ol>
                            <div>
                            <table>
	                                <TR>
										<TD>
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
										</TD>
									</TR>
								</table>
								</s:if>
								<s:else>
									<h1>您暂时还没有错题哦</h1>
								</s:else>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </main> --%>
    </form>
    <script>
    // 获取basePath 
    var basePath = '<%=basePath%>'
    $(function(ev) {
        var len = $('.subject').length;
        $('.subject').each(function(index){
        var i = index
        var self = $(this)
        
        self.find('label').each(function(){
        	var label = $(this)
        	if(self.data('key')==label.data('value')){
        		label.parent().addClass('correct')
        	}
        	if(self.data('skey')==label.data('value')){
        		label.parent().addClass('error')
        	}
        })
        
        //var data = {userid:userid,sid:self.data('sid'),studentkey:self.data('skey'),studentstate:self.data('state'),pname:pname}
        })

    })
    </script>
</body>

</html>