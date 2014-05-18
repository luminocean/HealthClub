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
			<a class="navbar-brand" href="servant/servant-home">Health Club</a>
		</div>

		<!-- 导航栏正体 -->
		<nav class="collapse navbar-collapse" id="nav-body">
			<!--搜索表单（测试）-->
			<form class="navbar-form navbar-left" action="">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

			<!-- $导航栏的组件如果要垂直对齐往往需要navbar的特殊版本！-->

			<!--右侧栏 用户名与登出按钮-->
			<ul class="nav navbar-nav navbar-right">
				<li><form method="post" action="logout.action">
						<input type="submit" class="btn btn-default navbar-btn" value="登出" />
					</form></li>
			</ul>
		</nav>

	</div>
	<!--End Container-->
</header>