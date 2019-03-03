<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/bootstrap.js"></script>
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
		<h3>做过的题 <span class="badge badge-secondary">Paper has been done</span></h3>
		
		<!-- 数据展示部分 -->
		<table class="table text-center">
		  <thead>
		    <tr>
		      <th scope="col">试题名称</th>
		      <th scope="col">错题数目</th>
		      <th scope="col">得分</th>
		      <th scope="col">做题时间</th>
		      <th scope="col">操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  <!-- 主数据 -->
		  	<s:iterator value="list">
		  		<c:forEach items="${list}" var="item">
					<tr>
						<td>
							${item.pname}
						</td>
						<td>
							${item.errorcount}
						</td>
						<td>
							${item.rightcount*2}
						</td>
						<td class="times" data-time = ${item.spid}>
							${item.spid}
						</td>

						<td>
							<a href="${pageContext.request.contextPath}/studentPaper_errorSubjectPage.action?userid=${existUser.userid}&spid=${item.spid}">查看详情</a>
						</td>
					</tr>
				</c:forEach>
			</s:iterator>
			
		  </tbody>
		</table>
	</div>
</div>



    <%-- <nav class="navbar navbar-default" role="navigation">
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
    </nav>

    <main class="container">
        <div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3 class="panel-title">查看错题</h3>
			</div>
			<div class="panel-body">
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>
									试题名称
								</th>
								<th>
									错题数目
								</th>
								<th>
									得分
								</th>
								<th>
									做题时间
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
										${item.errorcount}
									</td>
									<td>
										${item.rightcount*2}
									</td>
									<td class="times" data-time = ${item.spid}>
										${item.spid}
									</td>

									<td>
										<a href="${pageContext.request.contextPath}/studentPaper_errorSubjectPage.action?userid=${existUser.userid}&spid=${item.spid}">查看详情</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
    </main> --%>
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
    function p(n){
		return n<10?'0'+n:n;
	}
    $(function(){
    	$('.times').each(function(){
    		var self = $(this);
    		var date = new Date(self.data('time'))
    		var y=date.getFullYear()
    		var mon=date.getMonth()+1
    		var d =date.getDate()
    		var h=date.getHours()
			var m=date.getMinutes()
			self.html(y+'年'+mon+'月'+d+'日'+p(h)+'时'+p(m)+'分');
    	})
    })
    </script>
</body>

</html>