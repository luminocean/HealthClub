<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!-- 续费模态框 -->
<div class="modal fade" id='${param.modalId}'
	aria-labelledby="eventModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="eventModalLabel">${param.type}<s:text name="repay" /></h4>
			</div>

			<form class="form-horizontal" method="post" action="user/recover-membership">
				<div class="modal-body">

					<div class="form-group">
						<label class="col-md-3 control-label">${param.type}<s:text name="repayPayment" /></label>
						<div class="col-md-6">
							<input type="text" class="form-control" placeholder="<s:text name="creditcardNumber" />"
								name="creditCardAccount">
						</div>
						<div class="col-md-3"></div>
					</div>

					<!--<div class="form-group">
						 <div class="col-md-offset-1 col-md-2">
							<span>缴费月数</span>
						</div> 

						<div class="col-md-3">
							<input type="number" placeholder="输入月数" name="months" />
						</div>
					</div>-->


					<p>资费标准：会员资格费用为75美元/人，或100美元/家庭；
						每月收费，40美元/人，或55美元/夫妻（每个10~18岁孩子另加10美元）</p>

				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="close" /></button>
					<button type="submit" class="btn btn-primary"><s:text name="confirmPayment" /></button>
				</div>
			</form>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
<div></div>
<!-- /.modal -->