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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/paper.css">
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
<s:debug></s:debug>
<s:property value="list.size"/>
	<form id="paperForm" name="errorPaperForm"
		action="${pageContext.request.contextPath }/studentPaper_errorSubjectPage.action?userid=<s:property value="userid"/>&spid=<s:property value="spid"/>"
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
    </main>
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