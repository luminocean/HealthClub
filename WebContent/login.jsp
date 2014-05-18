<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<base href="${pageContext.request.contextPath}/">

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/general.css">

<title>注册</title>

<style type="text/css">
.register-region,.login-region {
	padding-left: 80px;
	width: 400px;
}

#register-btn,#login-btn {
	margin-right: 30px;
}
</style>
</head>

<body>
	<header class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<!-- 导航条内部容器，保证内部内容居中 -->
		<div class="container">

			<!-- 导航头，放置logo -->
			<div class="navbar-header">
				<a class="navbar-brand" href="/index.html">Health Club</a>
			</div>

			<!-- 导航栏正体 -->
			<nav class="collapse navbar-collapse" id="nav-body"></nav>

		</div>
		<!--End Container-->
	</header>

	<!-- 页面本体 -->
	<div class="row">
		<div class="col-md-offset-1 col-md-5">
			<div class="register-region">

				<div class="page-header">
					<h3>
						注册 <small>如果还没有成为会员的话</small>
					</h3>
				</div>

				<!-- 注册表单 -->
				<form id="register-form" class="form-horizontal" action="register"
					method="post">
					<div class="form-group">

						<!-- 邮箱 -->
						<label for="inputEmail3" class="col-md-3 control-label">邮箱</label>
						<div class="col-md-9">
							<input type="email" class="form-control" id="inputEmail3"
								placeholder="Email" name="registerUser.email">
						</div>
					</div>

					<div class="form-group">

						<!-- 邮箱 -->
						<label for="inputEmail3" class="col-md-3 control-label">用户名</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="inputName"
								placeholder="Name" name="registerUser.name">
						</div>
					</div>

					<!-- 密码 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">密码</label>
						<div class="col-md-9">
							<input type="password" class="form-control" id="inputPassword3"
								placeholder="Password" name="registerUser.password">
						</div>
					</div>

					<!-- 密码确认 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">密码确认</label>
						<div class="col-md-9">
							<input type="password" class="form-control" id="inputPassword3"
								placeholder="Password" name="registerUser.passwordConfirm">
						</div>
					</div>



					<div id="register-btn-group" class="form-group">
						<div class="pull-right">
							<button id="register-btn" type="submit" class="btn btn-default">注册</button>
						</div>
					</div>

				</form>

				<p>
					<a id="add-new" class="glyphicon glyphicon-plus-sign"> 添加顾客详细信息</a>
				</p>
			</div>

		</div>

		<div class="col-md-6">
			<div class="login-region">
				<div class="page-header">
					<h3>
						登录 <small>已经拥有账户的话</small>
					</h3>
				</div>

				<!-- 输出返回信息 -->
				<s:if test="hasFieldErrors()">
					<s:iterator value="fieldErrors">
						<s:property value="value[0]" />
					</s:iterator>
				</s:if>
				
				<s:property value="#request.msg"/>

				<!-- 登录表单 -->
				<form class="form-horizontal" action="login" method="post">
					<div class="form-group">

						<!-- 邮箱 -->
						<label for="inputEmail3" class="col-md-3 control-label">邮箱</label>
						<div class="col-md-9">
							<input type="email" class="form-control" id="inputEmail3"
								placeholder="Email" name="email">
						</div>
					</div>

					<!-- 密码 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">密码</label>
						<div class="col-md-9">
							<input type="password" class="form-control" id="inputPassword3"
								placeholder="Password" name="password">
						</div>
					</div>


					<div class="form-group">
						<div class="pull-right">
							<button id="register-btn" type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
			</div>





			<!-- <a href="user/user-home">用户界面</a> <a href="servant/">服务员界面</a> <a
				href="manager/">经理界面</a> -->


			<div id="guest-backup" class="detail-block">
				<p>__________________________________________________</p>

				<div class="form-group">
					<label class="col-md-3 control-label">客户名</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="用户名"
							name="registerUser.guestNames" />
					</div>

					<label class="col-md-2 control-label">年龄</label>
					<div class="col-md-3">
						<input type="number" class="form-control" placeholder="年龄"
							name="registerUser.ages" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-offset-1 col-md-2 control-label">性别</label> <label
						class="col-md-1 control-label"></label> 
					
					<div class="col-md-offset-5 col-md-4">
						<select class="form-control col-md-1" name="registerUser.sexes">
							<option value="male">男</option>
							<option value="female">女</option>
						</select>
					</div>
					
					

					<!-- <input type="radio" name="registerUser.sexes" value="male"> 
					<label class="control-label">男</label>
					<input type="radio" name="registerUser.sexes" value="female">
        			<label class="control-label">女</label> -->
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">居住地</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="居住地"
							name="registerUser.livingPlaces" />
					</div>
				</div>

			</div>

		</div>
	</div>


	<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"
		type="text/javascript"></script>



	<script type="text/javascript">
		//加入新客户支援
		$(document).ready(function() {
			$("#guest-backup").hide();

			$("#add-new").click(replication);
		});

		//先复制一个
		replication();

		function replication() {
			var clone = $("#guest-backup").clone();
			clone.removeAttr("id");

			$("#register-btn-group").before(clone.show());
		}
	</script>

</body>
</html>
