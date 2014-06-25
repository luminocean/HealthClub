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
						<p class="banner-word">
							<s:text name="bannerMsg" />
						</p>
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

						<!-- 面板本体 -->
						<div class="info-tab">
							<ul id="myTab" class="nav nav-tabs info-tab-header">
								<li class="active"><a href="#normal-card-info"
									data-toggle="tab"><s:text name="memberInfo" /></a></li>
								<li><a href="#event-history" data-toggle="tab"><s:text
											name="closedEvents" /></a></li>
								<li><a href="#payment-history" data-toggle="tab"><s:text
											name="paymentInfo" /></a></li>
							</ul>

							<div id="myTabContent" class="tab-content">
								<!-- 会员子面板 -->
								<div class="tab-pane fade in active" id="normal-card-info">

									<s:push value="#session.user">
										<s:if test="stringState=='unactivated'">
											<s:include value="activate-membership.jsp">
												<s:param name="type">
													<s:property value="stringType" />
												</s:param>
											</s:include>
										</s:if>

										<p>
											<span class="glyphicon glyphicon-tasks"></span>
											<s:text name="%{stringType}" />
											<!-- <s:property value="stringType" /> -->
											<s:text name="membershipNumber" />
											：
											<s:property value="code" />


										</p>
										<p>
											<span class="glyphicon glyphicon-flag"></span>
											<s:text name="%{stringType}" />
											<!--<s:property value="stringType" /> -->
											<s:text name="userName" />
											：
											<s:property value="name" />

											<!-- <a class="btn btn-default" data-toggle="modal"
												data-target="#modifyModal"><s:text name="modifyInfo" /></a> -->

											&nbsp;<a class="glyphicon glyphicon-edit" data-toggle="modal"
												data-target="#modifyModal"></a>

										</p>
										<p>
											<span class="glyphicon glyphicon-inbox"></span>
											<s:text name="accountState" />
											：
											<s:text name="%{stringState}"></s:text>
											<!-- <s:property value="stringState" /> -->


											<!-- 如果状态是未激活那么就不应该有续费按钮 -->
											<s:if test="stringState=='unpaid'">
												<a id="recover-btn" class="btn btn-default"
													data-toggle="modal" data-target="#recoverModal"><s:text
														name="repay" /></a>
											</s:if>

										</p>


										<!-- 顾客详情列表 -->
										<div class="table-responsive">
											<span class="glyphicon glyphicon-list-alt"></span>
											<s:text name="guestDetail" />
											：
											<table class="table">
												<thead>
													<tr>
														<th></th>
														<th><s:text name="guestName" /></th>
														<th><s:text name="age" /></th>
														<th><s:text name="sex" /></th>
														<th><s:text name="livingPlace" /></th>
													</tr>
												</thead>
												<tbody>
													<!-- 迭代显示详细信息  -->
													<s:iterator var="detail" value="details" status="status">
														<tr>
															<td><span class="glyphicon glyphicon-user" /></td>
															<td><s:property value="#detail.realName" /></td>
															<td><s:property value="#detail.age" /></td>
															<td><s:text name="%{#detail.stringSex}"></s:text></td>
															<!-- <s:property value="#detail.stringSex" /> -->
															<td><s:property value="#detail.livingPlace" /></td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>

										<s:if test="stringState=='normal'">
											<form action="user/cancel-membership" method="post">
												<input type="hidden" value='<s:property value="id"/>' />
												<button class="btn btn-default" type="submit">
													<s:text name="closeMembership" />
												</button>
											</form>
										</s:if>


									</s:push>
								</div>

								<!-- 活动记录子面板 -->
								<div class="tab-pane fade" id="event-history">

									<!-- 迭代显示所有内容 -->
									<s:iterator var="item" value="#request['closedEvents']"
										status="status">
										<s:include value="event-item-history.jsp"></s:include>
									</s:iterator>

								</div>

								<!-- 缴费记录子面板 -->
								<div class="tab-pane fade" id="payment-history">
									<table class="table table-responsive">
										<thead>
											<tr>
												<th><s:text name="paymentNumber" /></th>
												<th><s:text name="paymentTime" /></th>
												<th><s:text name="paymentAmount" /></th>
											</tr>
										</thead>
										<tbody>
											<s:iterator var="item" value="#session.user.payments"
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

					</div>

					<div class="col-md-2"></div>


					<!-- 缴费支援面板 -->
					<s:include value="recover-membership.jsp">
						<s:param name="modalId">recoverModal</s:param>
						<s:param name="type">
							<s:text name="personal" />
						</s:param>
					</s:include>



					<!-- 资料修改模态框 -->
					<div class="modal fade" id='modifyModal'
						aria-labelledby="eventModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="eventModalLabel">
										<s:text name="modifyInfo" />
									</h4>
								</div>


								<!-- 压入User -->
								<s:push value="#session.user">

									<form class="form-horizontal" method="post"
										action="user/modify-info">

										<div class="modal-body">

											<div class="form-group">
												<label class="col-md-3 control-label"><s:text
														name="userName" /></label>
												<div class="col-md-6">
													<input type="text" class="form-control"
														name="modifiedUser.newUserName"
														value='<s:property value="name"/>'>
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-3 control-label"><s:text
														name="oldPassword" /></label>
												<div class="col-md-6">
													<input type="text" class="form-control"
														name="modifiedUser.oldPassword">
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-3 control-label"><s:text
														name="newPassword" /></label>
												<div class="col-md-6">
													<input type="text" class="form-control"
														name="modifiedUser.newPassword">
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-3 control-label"><s:text
														name="newPasswordConfirm" /></label>
												<div class="col-md-6">
													<input type="text" class="form-control"
														name="modifiedUser.confirmPassword">
												</div>
											</div>

										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">
												<s:text name="close" />
											</button>
											<button type="submit" class="btn btn-primary">
												<s:text name="modifyConfim" />
											</button>
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

				</div>
			</div>
		</div>
	</div>


	<!--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"
		type="text/javascript"></script>-->

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


	<script type="text/javascript">
		$("#info-flag").attr("class", "active");
		$("#popover").popover();
	</script>

</body>
</html>
