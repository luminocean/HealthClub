<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<header class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<!-- 导航条内部容器，保证内部内容居中 -->
	<div class="container">

		<!-- 导航头，放置logo -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#nav-body">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="user/user-home">Health Club</a>
		</div>

		<!-- 导航栏正体 -->
		<nav class="collapse navbar-collapse" id="nav-body">
			<!--搜索表单（测试）-->
			<form class="navbar-form navbar-left" action="">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="<s:text name="keyword"></s:text>">
				</div>
				<button type="submit" class="btn btn-default">
					<s:text name="search"></s:text>
				</button>
			</form>

			<!-- $导航栏的组件如果要垂直对齐往往需要navbar的特殊版本！-->

			<!--右侧栏 用户名与登出按钮-->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login.jsp"><s:property value="#session['user'].name" default="UserX"></s:property></a></li>
				<li><form method="post" action="logout.action">
						<input type="submit" class="btn btn-default navbar-btn" value="<s:text name="logout"></s:text>" />
					</form></li>
			</ul>
		</nav>

	</div>
	<!--End Container-->
</header>