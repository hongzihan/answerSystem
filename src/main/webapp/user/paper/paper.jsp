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
	<script src="<%=basePath%>layer/layer.js"></script>
</head>

<body>
	<input type="hidden" value="<s:property value="#session.existUser.userid"/>" name="userid" id="userid">
	<s:debug></s:debug>
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
				<li><a href="${pageContext.request.contextPath}/studentPaper_allErrorSubjectPage.action?userid=<s:property value="userid"/>">查看错题</a></li>
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
					<s:property value="pname"/>
				</h3>
			</div>
			<div class="panel-body">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#Radio">单选题（共 <s:property value="totalCount"/> 题，每题2分）</a>
						</h4>
					</div>
					<form action="" method="POST" role="form">
						<div id="Radio" class="panel-collapse collapse in">
							<div class="panel-body">
								<ol>
									<s:iterator value="list" status="status">
										<div class="subject" data-i="${status.index}" data-answer="false" data-sid="<s:property value="sid"/>" data-key="<s:property value="skey"/>" data-state="0"
											data-skey>
											<li>
												<s:property value="scontent"/>
											</li>
											
											<ol>
												<li><label><input type="radio" value="A" name="<s:property value="sid"/>"><s:property value="sa"/></label></li>
												<li><label><input type="radio" value="B" name="<s:property value="sid"/>"><s:property value="sb"/></label></li>
												<li><label><input type="radio" value="C" name="<s:property value="sid"/>"><s:property value="sc"/></label></li>
												<li><label><input type="radio" value="D" name="<s:property value="sid"/>"><s:property value="sd"/></label></li>
											</ol>
										</div>
									</s:iterator>
								</ol>
								<button class="btn btn-success" type="submit">交卷</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<aside class="processor">
		<section class="time" id="time">
			00时00分00秒
		</section>
		<section class="timu">
			<s:iterator value="list" status="status">
				<div data-i="${ status.index}">${ status.index + 1}</div>
			</s:iterator>
		</section>
	</aside>

	<script>
		// 获取题目数量
		var len = $('.subject').length;
		var unanswer = len;
		
		// 获取basePath 
		var basePath = '<%=basePath%>'
					
		// 监听点击选项
		$('.subject ol li label').click(function () {
			// 获得本题的div
			var sub = $(this).parent().parent().parent()
			var indexs = sub.data('i')
			// 判断此题是否回答过
			if(sub.data('answer') == false){
				// 没有回答过给processor下的指定题号添加answered样式
				$('.timu').children().eq(indexs).addClass('answered');
				// 未答题目－1
				unanswer--
				sub.data('answer',true)
			}
			// 判断选项是否正确
			if($(this).find(':input').val() == sub.data('key')){
				// 正确给data-state赋值1
				sub.data('state',1)
			}else{
				// 不正确给data-state赋值0
				sub.data('state',0)
			}
			sub.data('skey',$(this).find(':input').val())
			// siblings() 获得匹配集合中每个元素的同胞，通过选择器进行筛选是可选的。
			$(this).parent().addClass('checked').siblings().removeClass('checked')
		})
		


		// 交卷功能
		// (1)判断是否有未答题目
		function unAnswer(){
			if(unanswer != 0){
				layer.open({
				title:'警告', 
				content: "还有"+unanswer+"道题目未做！",
				icon:2,
				end:function(){
					
				}
				});
			}else{
				postAnswer();
			}
		}
		// (2)计算得分
		function getScore(){
			//var spid = n
			var userid = $("#userid").val();
			var spid = now.getTime();
			$.post({
		        url: basePath + 'studentPaper_caculateScore.action?userid='+userid+'&spid='+ spid,
		        contentType: false,
		        processData: false,
		        success: function(res) {
		            console.log(res)
		            layer.open({
						title:'得分', 
						content: res,
						icon:1,
						end:function(){
							location.href = basePath+'studentPaper_errorSubjectPage.action?userid=' + userid + '&spid=' + spid;
						}
					})
		        },
		        error: function(res) {
		            console.log('error');
		            
		        }
		    })
			
		}
		// (3)提交答案的post请求
		function postAnswer(){
			var index = layer.load(0, {shade: false});
			var pname = '${pname}';
			var userid = $("#userid").val();
			$('.subject').each(function(index){
				var i = index
				var self = $(this)
				var data = {userid:userid,sid:self.data('sid'),studentkey:self.data('skey'),studentstate:self.data('state'),pname:pname,spid:now.getTime()}
				var datas = $.param(data)
				console.log(datas)
				$.post({
					url: basePath+'/studentPaper_answer?'+ datas,
					contentType: false,
					processData: false,
					success:function(){
						// 在最后一次提交完成后跳转到错题列表
						if(i == len - 1){
							layer.close(index);
							//location.href = basePath+'user/studentPaper?cmd=list';
							getScore();
						}
					}
				}) 
			})
		}
		// (4)点击交卷
		$('form').submit(function(ev) {
			ev.preventDefault()
			unAnswer();
		})
		
		// 倒计时功能
		//小于10的数字前面补0
		function p(n){
			return n<10?'0'+n:n;
		}
		//获取当前时间
		var now=new Date();
		//获取结束时间
		var endDate=new Date();
		//设置考试时间（单位分钟）
		endDate.setMinutes(now.getMinutes()+20)
		function getTime(){           
			var startDate=new Date();
			var countDown=(endDate.getTime()-startDate.getTime())/1000;
			var h=parseInt(countDown/(60*60)%24);
			var m=parseInt(countDown/60%60);
			var s=parseInt(countDown%60);                
			$('.time').html(p(h)+'时'+p(m)+'分'+p(s)+'秒');
			if(countDown<=0){
			document.getElementById('time').innerHTML='考试结束';
			layer.open({
				title:'警告', 
				content: '考试时间到，试卷已经提交！',
				icon:5,
				end:function(){
					unAnswer();
				}
			})
			}else{
				setTimeout('getTime()',500);
			}              
		}
		getTime()
	</script>
</body>

</html>