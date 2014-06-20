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

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/exporting.js"></script>
<script type="text/javascript" src="js/highcharts-3d.js"></script>
<script type="text/javascript" src="js/age-analysis.js"></script>
<script type="text/javascript" src="js/gender-analysis.js"></script>
<script type="text/javascript" src="js/address-analysis.js"></script>
<script type="text/javascript" src="js/status-analysis.js"></script>

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
								<span class="glyphicon glyphicon-list" /> 会员信息统计
							</p>
						</div>

						
						<!-- 图表 -->
						<div id="age-analysis" style="min-width:700px;height:400px"></div><br><br>
						<div id="gender-analysis" style="min-width:700px;height:400px"></div><br><br>
						<div id="address-analysis" style="min-width:700px;height:400px"></div><br><br>
						<div id="status-analysis" style="min-width:700px;height:400px"></div>
					</div>

				</div>


			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#user-statistic-flag").attr("class", "active");
	</script>
	

</body>


</html>
