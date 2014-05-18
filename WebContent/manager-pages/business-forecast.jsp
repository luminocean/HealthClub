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
<link rel="stylesheet" href="css/banner.css">
<link rel="stylesheet" href="css/event.css">

<title>Health Club</title>

</head>

<body>

	<s:include value="navbar.jsp" />


	<!-- 页面主体 -->
	<div class="container">
		<div class="row">
			<!-- 左侧栏 -->
			<div class="col-md-3">

				<s:include value="sidenav.jsp" />

			</div>
			<!-- 左侧栏End -->


			<!-- 右侧主体 -->
			<div class="col-md-9">
				<!-- 广告 -->
				<div class="row">
					<div class="banner">
						<p class="banner-word">自分の限界をしりたいのか？</p>
					</div>
				</div>

				<!-- 正文 -->
				<div class="row">
					<div class="col-md-10">
						<div class="page-header">
							<p class="page-title">
								<span class="glyphicon glyphicon-list" /> 业务预测
							</p>
						</div>


						<div class="alert alert-success">
							根据之前几个月的数据显示，下月预计参与活动的会员数为： <strong><s:property
									value="#request.peopleInNextMonth" />人</strong>
						</div>

						<div class="alert alert-danger">
							当前最热门的活动类型为：<strong> <s:property value="#request.hotTag"></s:property></strong>
							建议增加此类活动
						</div>
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

	<script src="http://code.highcharts.com/highcharts.js"
		type="text/javascript"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"
		type="text/javascript"></script>



	<script type="text/javascript">
		$("#business-forecast-flag").attr("class", "active");
	</script>

</body>


</html>
