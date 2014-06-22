<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- 未激活用户会出现的激活面板 -->
<div class="panel-group" id="active-panel">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">

				<a data-toggle="collapse" data-toggle="collapse"
					data-parent="#accordion" href="#collapseOne"> <s:text name="activate" />${param.type}<s:text name="membership" />
				</a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse">
			<div class="panel-body">

				<form class="form-horizontal" action="user/activate-membership" method="post">
					<div class="form-group">
						<label class="col-md-3 control-label">${param.type}<s:text name="activatePayment" /></label>
						<div class="col-md-6">
							<input type="text" class="form-control" placeholder="<s:text name="creditcardNumber" />"
								name="creditCardAccount">
						</div>
						<div class="col-md-3"></div>
					</div>

					<div class="form-group">
						<div class="col-md-offset-7 col-md-5">
							<button type="submit" class="btn btn-default"><s:text name="confirm" /></button>
						</div>
					</div>
				</form>

				<button id="popover" class="btn btn-lg" data-placement="left"
					data-content="会员资格费用为75美元/人，或100美元/家庭；
每月收费，40美元/人，或55美元/夫妻（每个10~18岁孩子另加10美元）"
					title="" data-original-title="详细资费标准"><s:text name="paymentStandard" /></button>

			</div>
		</div>
	</div>
</div>