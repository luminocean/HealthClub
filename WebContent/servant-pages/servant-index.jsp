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

<style type="text/css">
#carousel-area {
	margin-top: 20px;
	width: 400px;
	height: 250px;
	overflow: hidden;
	border-radius: 20px;
}
</style>
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
					<div class="col-md-5">
						<div class="page-header">
							<p class="page-title">
								<span class="glyphicon glyphicon-list" /> 今日活动
							</p>
						</div>
					</div>
				</div>




			</div>
		</div>
	</div>


<!--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>-->

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$('.carousel').carousel('next');
	</script>

	<div></div>
	<div></div>
	<div></div>
</body>
</html>


<!-- 
<div class="row">
                <div class="page-header">
                  <p class="page-title">推荐活动概览</p>
                </div>


            	<div class="event-item">
                	<p>
                       <span class="glyphicon glyphicon-hand-right"></span>
                       <span class="label label-warning">已预约</span>
                       <a class="title" data-toggle="modal" data-target="#myModal">肌肉撕裂之日！</a>
                       <a href="#">
                       		<span class="label label-success">健身</span>
                       </a>
                    </p>
                    <p>教练：Tomas, Rachel</p>
                    <p>预约人数：15/20</p>
                    <p>最近时间：4.15 2:30 pm</p>
 
                </div>

            	<div class="event-item">
                	<p>
                       <span class="glyphicon glyphicon-hand-right"></span>
                       <a href="#" class="title">我们不用很麻烦很累就可以放松</a>
                       <a href="#">
                       		<span class="label label-success">瑜伽</span>
                       </a>
                    </p>
                    <p>教练：Stepheny</p>
                    <p>预约人数：15/30</p>
                    <p>最近时间：4.15 2:30 pm</p>
                </div>
                
                <div class="event-item">
                	<p>
                       <span class="glyphicon glyphicon-hand-right"></span>
                       <span class="label label-danger">已满员</span>
                       <a href="#" class="title">我想起了那天夕阳下的奔跑</a>
                       <a href="#"><span class="label label-success">跑步</span></a>
                       <a href="#"><span class="label label-success">有氧</span></a>
                    </p>
                    <p>教练: Chandler</p>
                    <p>预约人数：30/30</p>
                    <p>最近时间：4.21 8:30 am</p>
                </div>
                
                
            </div>
 -->
