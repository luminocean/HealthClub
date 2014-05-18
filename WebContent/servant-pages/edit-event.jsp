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

<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/ >



<style type="text/css">
#add-occasion {
	text-align: left;
}

.occasion-attr {
	margin-top: 6px;
}
</style>

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
					<div class="page-header">

						<p class="page-title">
							<span class="glyphicon glyphicon-list" /> 活动编辑
						</p>
					</div>

					<!-- 设置可能会传过来的Event对象访问 -->
					<s:set var="event" value="#request['event']" />
					

					<!-- 编辑表单 -->
					<form id="register-form" class="form-horizontal"
						action="servant/submit-event" method="post">
						<div class="form-group">
							<label class="col-md-1 control-label">主题</label>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="输入主题"
									name="title" value="<s:property value="#event.title"/>"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-1 control-label">描述</label>

							<div class="col-md-8">
								<textarea class="form-control" rows="3" cols="5"
									placeholder="此处输入活动详细描述" name="detail"><s:property value="#event.detail"/></textarea>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-md-1 control-label">标签</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="输入标签（空格分隔）"
									name="tags" 
									value="<s:iterator var="tag" value="#event.tags"><s:property value="#tag"/> </s:iterator>">
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-1 control-label">场次</label> <label
								id="add-occasion" class="col-md-1 control-label"><span
								class="glyphicon glyphicon-plus" /></label>
						</div>
						
		
						<!-- 输出已经保存过的occasions -->
						<s:iterator var="occasion" value="#event.occasions">
    						<s:include value="occasion-edit.jsp"/>
                		</s:iterator>


						<div id="confirm-btn-group" class="form-group">
							<s:if test="#event!=null">
								<input type="hidden" name="eventId" value="<s:property value="#event.id"/>">
							</s:if>
							
							<div class="col-md-offset-8">
								<button id="register-btn" type="submit" class="btn btn-default">确认</button>
							</div>
						</div>

					</form>


					<div id="occasion-backup">
						<s:include value="occasion-edit.jsp"/>
					</div>


				</div>

			</div>
		</div>

	</div>


	<!--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>-->

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
		
	<script src="js/jquery.datetimepicker.js"></script>


	<script type="text/javascript">
		$("#edit-event-flag").attr("class", "active");
		
		//$('.datetimepicker').datetimepicker();
	</script>

	<script type="text/javascript">
		var counter = 1;
		
		//加入新客户支援
		$(document).ready(function() {
			$("#occasion-backup").hide();

			$("#add-occasion").click(replication);
		});

		//先复制一个
		//replication();

		function replication() {
			var clone = $("#occasion-backup").clone();
			clone.removeAttr("id");

			
			clone.find(".datetimepicker").attr("id", "datetimepicker"+counter);
			

			$("#confirm-btn-group").before(clone.show());
			
			$('#datetimepicker'+counter).datetimepicker();
			
			counter = counter + 1;
		}
	</script>


</body>
</html>