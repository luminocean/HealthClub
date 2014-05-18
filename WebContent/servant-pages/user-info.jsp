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
<link rel="stylesheet" href="css/user.css">


<title>Health Club</title>

<style type="text/css">
#card-panel-group {
	margin-top: 50px;
}

/* 用于切换面板内的字体*/
.tab-content {
	font-size: 1.3em;
}

/* 激活面板 */
.panel {
	background-color: transparent;
}

.popover-title,.popover-content {
	color: black;
	font-size: 18px;
}

#active-panel {
	margin-bottom: 20px;
}

#recover-btn {
	color: white;
	background-color: #5CB85C;
	border-color: #5CB85C;
	margin-left: 15px;
}

#user-search-form {
	margin-top: 30px;
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

					<div class="col-md-10">

						<!-- 输出返回信息 -->
						<s:if test="hasFieldErrors()">
							<s:iterator value="fieldErrors">
								<s:property value="value[0]" />
							</s:iterator>
						</s:if>


						<!-- 用户检索 -->
						<form id="user-search-form" class="form-horizontal"
							action="servant/user-info" method="post">
							<div class="form-group">
								<label for="inputEmail3" class="col-md-1 control-label">检索</label>
								<div class="col-md-3">
									<input type="text" class="form-control" placeholder="输入用户编号"
										name="requestUserCode" />
								</div>
								<input class="btn btn-default" type="submit" value="检索用户" />
							</div>
						</form>


						<s:if test="#request.user!=null">


							<!-- 面板本体 -->
							<div class="info-tab">
								<ul id="myTab" class="nav nav-tabs info-tab-header">
									<li class="active"><a href="#normal-card-info"
										data-toggle="tab">会员信息</a></li>
									<li><a href="#reserved-events" data-toggle="tab">预约活动</a></li>
									<li><a href="#event-history" data-toggle="tab">历史活动</a></li>
									<li><a href="#payment-history" data-toggle="tab">缴费信息</a></li>
								</ul>

								<div id="myTabContent" class="tab-content">
									<!-- 会员子面板 -->
									<div class="tab-pane fade in active" id="normal-card-info">

										<s:push value="#request.user">
											<s:if test="stringState=='未激活'">
												<s:include value="activate-membership.jsp">
													<s:param name="type">
														<s:property value="stringType" />
													</s:param>
												</s:include>
											</s:if>

											<p>
												<span class="glyphicon glyphicon-tasks"></span>
												<s:property value="stringType" />
												会员编号：
												<s:property value="code" />
											</p>
											<p>
												<span class="glyphicon glyphicon-flag"></span>
												<s:property value="stringType" />
												会员名：
												<s:property value="name" />

												<a class="btn btn-default" data-toggle="modal"
													data-target="#modifyModal">资料修改</a>

											</p>

											<p>
												<span class="glyphicon glyphicon-inbox"></span> 账户状态：
												<s:property value="stringState" />


												<!-- 如果状态是未激活那么就不应该有续费按钮 -->
												<s:if test="stringState=='已欠费'">
													<a id="recover-btn" class="btn btn-default"
														data-toggle="modal" data-target="#recoverModal">续费</a>
												</s:if>

											</p>


											<!-- 顾客详情列表 -->
											<div class="table-responsive">
												<span class="glyphicon glyphicon-list-alt"></span> 顾客详细信息：
												<table class="table">
													<thead>
														<tr>
															<th></th>
															<th>姓名</th>
															<th>年龄</th>
															<th>居住地</th>
														</tr>
													</thead>
													<tbody>
														<!-- 迭代显示详细信息  -->
														<s:iterator var="detail" value="details" status="status">
															<tr>
																<td><span class="glyphicon glyphicon-user" /></td>
																<td><s:property value="#detail.realName" /></td>
																<td><s:property value="#detail.age" /></td>
																<td><s:property value="#detail.livingPlace" /></td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>

										</s:push>
									</div>


									<!-- 预约活动子面板 -->
									<div class="tab-pane fade" id="reserved-events">

										<!-- 迭代显示所有内容 -->
										<s:iterator var="item" value="#request['reservedEvents']"
											status="status">
											<s:include value="event-item.jsp"></s:include>
										</s:iterator>

									</div>



									<!-- 活动记录子面板 -->
									<div class="tab-pane fade" id="event-history">

										<!-- 迭代显示所有内容 -->
										<s:iterator var="item" value="#request['closedEvents']"
											status="status">
											<s:include value="event-item-history.jsp"/>
										</s:iterator>

									</div>

									<!-- 缴费记录子面板 -->
									<div class="tab-pane fade" id="payment-history">
										<table class="table table-responsive">
											<thead>
												<tr>
													<th>编号</th>
													<th>付费时间</th>
													<th>金额</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator var="item" value="#request.user.payments"
													status="status">
													<tr>
														<!-- 迭代显示所有内容 -->

														<td><s:property value="#status.index+1" /></td>
														<s:push value="#item">
															<td><s:property value="stringTime" /></td>
															<td><s:property value="amount" /></td>
														</s:push>

													</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>

								</div>
							</div>
							<!-- 面板本体 End -->

						</s:if>
						<!-- user存在性条件判断结束 -->
					</div>

					<div class="col-md-2"></div>

					<s:if test="#request.user!=null">

						<!-- 资料修改模态框 -->
						<div class="modal fade" id='modifyModal'
							aria-labelledby="eventModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="eventModalLabel">资料修改</h4>
									</div>


									<!-- 压入User -->
									<s:push value="#request.user">

										<form class="form-horizontal" method="post"
											action="user/modify-info">

											<div class="modal-body">

												<div class="form-group">
													<label class="col-md-3 control-label">会员名</label>
													<div class="col-md-6">
														<input type="text" class="form-control"
															name="modifiedUser.newUserName"
															value='<s:property value="name"/>'>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">原密码</label>
													<div class="col-md-6">
														<input type="text" class="form-control"
															name="modifiedUser.oldPassword">
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">新密码</label>
													<div class="col-md-6">
														<input type="text" class="form-control"
															name="modifiedUser.newPassword">
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">新密码确认</label>
													<div class="col-md-6">
														<input type="text" class="form-control"
															name="modifiedUser.confirmPassword">
													</div>
												</div>

											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="submit" class="btn btn-primary">确认修改</button>
											</div>
										</form>

									</s:push>


								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
						<div></div>
						<!-- /.modal -->
					</s:if>
					<!-- 再次判断结束 -->
				</div>
			</div>
		</div>
	</div>


	<!--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>-->

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$("#user-info-flag").attr("class", "active");
		$("#popover").popover();
	</script>

</body>
</html>
