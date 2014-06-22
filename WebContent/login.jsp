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

<title>注册</title>

<style type="text/css">
.register-region,.login-region {
	padding-left: 80px;
	width: 400px;
}

#register-btn,#login-btn {
	margin-right: 30px;
}
</style>
</head>

<body>
	<header class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<!-- 导航条内部容器，保证内部内容居中 -->
		<div class="container">

			<!-- 导航头，放置logo -->
			<div class="navbar-header">
				<a class="navbar-brand" href="/index.html">Health Club</a>
			</div>

			<!-- 导航栏正体 -->
			<nav class="collapse navbar-collapse" id="nav-body"></nav>

		</div>
		<!--End Container-->
	</header>

	<!-- 页面本体 -->
	<div class="row">
		<div class="col-md-offset-1 col-md-5">
			<div class="register-region">

				<div class="page-header">
					<h3>
						注册 <small>如果还没有成为会员的话</small>
					</h3>
				</div>

				<!-- 注册表单 -->
				<form id="register-form" onsubmit="return chkRegister()" class="form-horizontal" action="register"
					method="post">
					<div class="form-group">

						<!-- 邮箱 -->
						<label for="inputEmail3" class="col-md-3 control-label">邮箱</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="EmailReg"
								placeholder="Email" name="registerUser.email" onblur="blurRegEmail()">
							<label id="reg-email-tips" style="color:#FF2D2D; display:none">邮箱格式应为***@***.***！</label>
						</div>
					</div>

					<div class="form-group">

						<!-- 邮箱 -->
						<label for="inputEmail3" class="col-md-3 control-label">用户名</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="NameReg"
								placeholder="Name" name="registerUser.name" onblur="blurRegNoInput(this)">
							<label id="reg-name-tips" style="color:#FF2D2D; display:none">未填写！</label>
						</div>
					</div>

					<!-- 密码 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">密码</label>
						<div class="col-md-9">
							<input type="password" class="form-control" id="PasswordReg"
								placeholder="Password" name="registerUser.password" onblur="blurRegNoInput(this)">
							<label id="reg-psw-tips" style="color:#FF2D2D; display:none">未填写！</label>
						</div>
					</div>

					<!-- 密码确认 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">密码确认</label>
						<div class="col-md-9">
							<input type="password" class="form-control" id="RePasswordReg"
								placeholder="Password" name="registerUser.passwordConfirm" onblur="blurRegRepsw()">
							<label id="reg-repsw-tips" style="color:#FF2D2D; display:none">两次密码不匹配！</label>
						</div>
					</div>

					<p>
						<a id="add-new" class="glyphicon glyphicon-plus-sign"> 添加顾客详细信息</a>
					</p>

					<div id="register-btn-group" class="form-group">
						<div class="pull-right">
							<button id="register-btn" type="submit" class="btn btn-default">注册</button>
						</div>
					</div>

				</form>

				
			</div>

		</div>

		<div class="col-md-6">
			<div class="login-region">
				<div class="page-header">
					<h3>
						登录 <small>已经拥有账户的话</small>
					</h3>
				</div>

				<!-- 输出返回信息 -->
				<s:if test="hasFieldErrors()">
					<s:iterator value="fieldErrors">
						<s:property value="value[0]" />
					</s:iterator>
				</s:if>
				
				<s:property value="#request.msg"/>

				<!-- 登录表单 -->
				<form class="form-horizontal" onsubmit="return chkLogin()" action="login" method="post">
					<div class="form-group">

						<!-- 邮箱 -->
						<label for="inputEmail3" class="col-md-3 control-label">邮箱</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="emailLogin"
								placeholder="Email" name="email" onblur="checkLoginEmail()">
							<label id="login-email-tips" style="color:#FF5151; display:none">邮箱格式应为***@***.***！</label>
						</div>
					</div>

					<!-- 密码 -->
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">密码</label>
						<div class="col-md-9">
							<input type="password" class="form-control" id="passwordLogin"
								placeholder="Password" name="password">
						</div>
					</div>


					<div class="form-group">
						<div class="pull-right">
							<button id="register-btn" type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
			</div>





			<!-- <a href="user/user-home">用户界面</a> <a href="servant/">服务员界面</a> <a
				href="manager/">经理界面</a> -->


			<div id="guest-backup" class="detail-block">
				<p>_____________________________________________________</p>

				<div class="form-group">
					<label class="col-md-3 control-label">客户名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="username" id="username"
							name="registerUser.guestNames" onblur="blurRegNoInput(this)" />
						<label id="reg-username-tips" style="color:#FF2D2D; display:none">未填写！</label>
					</div>
					
				</div>

				<div class="form-group">
				
					<label class="col-md-3 control-label">年龄</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="year" id="year"
							name="registerUser.ages" onblur="blurRegYear(this)" />
					</div>
					
					<label class="col-md-2 control-label">性别</label>
					<div class="col-md-4">
						<select class="form-control col-md-1" name="registerUser.sexes">
							<option value="male">男</option>
							<option value="female">女</option>
						</select>
					</div>

					<!-- <input type="radio" name="registerUser.sexes" value="male"> 
					<label class="control-label">男</label>
					<input type="radio" name="registerUser.sexes" value="female">
        			<label class="control-label">女</label> -->
				</div>
				
				<div class="form-group" id="reg-year-tips" style="display:none">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-9">
						<label  style="color:#FF2D2D;">年龄应输入正整数！</label>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">居住地</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="Residence" id="residence"
							name="registerUser.livingPlaces" onblur="blurRegNoInput(this)" />
						<label id="reg-residence-tips" style="color:#FF2D2D; display:none">未填写！</label>
					</div>
				</div>
				
				<a onclick="del(this)" class="glyphicon glyphicon-remove" style="float:right;" > 删除该顾客</a>
			</div>
			
			<div id="guest-backup-first" class="detail-block">
				<p>_____________________________________________________</p>

				<div class="form-group">
					<label class="col-md-3 control-label">客户名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="username" id="username"
							name="registerUser.guestNames" onblur="blurRegNoInput(this)"/>
						<label id="reg-username-tips" style="color:#FF2D2D; display:none">未填写！</label>
					</div>
					
				</div>

				<div class="form-group">
					
					<label class="col-md-3 control-label">年龄</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="year" id="year"
							name="registerUser.ages" onblur="blurRegYear(this)"/>
					</div>
					
					<label class="col-md-2 control-label">性别</label>
					<div class="col-md-4">
						<select class="form-control col-md-1" name="registerUser.sexes">
							<option value="male">男</option>
							<option value="female">女</option>
						</select>
					</div>
					
					<!-- <input type="radio" name="registerUser.sexes" value="male"> 
					<label class="control-label">男</label>
					<input type="radio" name="registerUser.sexes" value="female">
        			<label class="control-label">女</label> -->
				</div>
				<div class="form-group" id="reg-year-tips" style="display:none">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-9">
						<label  style="color:#FF2D2D;">年龄应输入正整数！</label>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">居住地</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="Residence" id="residence"
							name="registerUser.livingPlaces" onblur="blurRegNoInput(this)"/>
						<label id="reg-residence-tips" style="color:#FF2D2D; display:none">未填写！</label>
					</div>
				</div>
				
			</div>

		</div>
	</div>


	<script src="js/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="js/bootstrap.min.js"
		type="text/javascript"></script>



	<script type="text/javascript">
		//加入新客户支援
		$(document).ready(function() {
			$("#guest-backup").hide();
			$("#guest-backup-first").hide();

			$("#add-new").click(replication);
		});

		//先复制一个
		first();
		
		function first(){
			var clone = $("#guest-backup-first").clone();
			clone.removeAttr("id");

			$("#add-new").before(clone.show());
		}
		
		//find id
		function getById(oParent,sId){
			var aEle=oParent.getElementsByTagName('*');
		    var aResult=[];
		    var i=0;
		     
		    for(i=0;i<aEle.length;i++)
		    {
		        if(aEle[i].id==sId)
		        {
		            aResult.push(aEle[i]);
		        }
		    }
		     
		    return aResult;
		}

		function replication() {
			var clone = $("#guest-backup").clone();
			clone.removeAttr("id");
			
			$("#add-new").before(clone.show());
			
			var op = document.getElementById("register-form");
			var residences = getById(op,"residence");
			residences[residences.length-1].value = residences[0].value;
		}
		
		function del(obj){
			var div = obj.parentNode;
			div.parentNode.removeChild(div);
		}

		function checkLoginEmail(){
			var tips = document.getElementById("login-email-tips");
			var pattern = /^([a-zA-Z0-9])+@([a-zA-Z0-9])+\.([a-zA-Z0-9])+$/;
			var email = document.getElementById("emailLogin");
			
			if(!pattern.test(email.value)){
				tips.style.display = "block";
			}
			else{
				tips.style.display = "none";
			}

		}
		//验证 登陆输入
		function chkLogin(){
			var state = 1;

			var tips = document.getElementById("login-email-tips");
			var pattern = /^([a-zA-Z0-9])+@([a-zA-Z0-9])+\.([a-zA-Z0-9])+$/;
			var email = document.getElementById("emailLogin");
			var psw = document.getElementById("passwordLogin");
			
			if(email.value == "" || psw.value == ""){
				if(email.value == ""){
					tips.style.display = "block";
				}
				state =0;
			}
			
			if(!pattern.test(email.value)){
				tips.style.display = "block";
				state =0;
			}

			if(state == 0){
				return false;
			}
			alert("true");
			return false;
		}
		
		function blurRegEmail(){
			var tips = document.getElementById("reg-email-tips");
			var pattern = /^([a-zA-Z0-9])+@([a-zA-Z0-9])+\.([a-zA-Z0-9])+$/;
			var email = document.getElementById("EmailReg");
			
			if(!pattern.test(email.value)){
				tips.style.display = "block";
			}
			else{
				tips.style.display = "none";
			}
		}
		
		function blurRegNoInput(self){
			var tips = self.nextSibling.nextSibling;
			
			if(self.value == ""){
				tips.style.display = "block";
			}
			else{
				tips.style.display = "none";
			}
		}
		
		function blurRegRepsw(){
			var tips = document.getElementById("reg-repsw-tips");
			var psw = document.getElementById("PasswordReg");
			var repsw = document.getElementById("RePasswordReg");
			
			if(psw.value != repsw.value){
				tips.style.display = "block";
			}
			else{
				tips.style.display = "none";
			}
		}
		
		function blurRegYear(self){
			var tips = self.parentNode.parentNode.nextSibling.nextSibling;
			var pattern_year = /^[1-9]+[0-9]*$/;
			
			if(!pattern_year.test(self.value)){
				tips.style.display = "block";
			}
			else{
				tips.style.display = "none";
			}
		}
		
		//验证 注册输入
		function chkRegister(){
			var state = 1;
			
			var email_tips = document.getElementById("reg-email-tips");
			var name_tips = document.getElementById("reg-name-tips");
			var psw_tips = document.getElementById("reg-psw-tips");
			var repsw_tips = document.getElementById("reg-repsw-tips");
			
			var pattern = /^([a-zA-Z0-9])+@([a-zA-Z0-9])+\.([a-zA-Z0-9])+$/;
			var email = document.getElementById("EmailReg");
			var name = document.getElementById("NameReg");
			var psw = document.getElementById("PasswordReg");
			var repsw = document.getElementById("RePasswordReg");
			
			if(email.value == ""){
				email_tips.style.display = "block";
				state = 0;
			}
			else{
				email_tips.style.display = "none";
			}
			
			if(name.value == ""){
				name_tips.style.display = "block";
				state = 0;
			}
			else{
				name_tips.style.display = "none";
			}
			
			if(psw.value == ""){
				psw_tips.style.display = "block";
				state = 0;
			}
			else{
				psw_tips.style.display = "none";
			}
			if(repsw.value == ""){
				repsw_tips.style.display = "block";
				state = 0;
			}
			else{
				repsw_tips.style.display = "none";
			}
			
			if(!pattern.test(email.value)){
				email_tips.style.display = "block";
				state = 0;
			}
			else{
				email_tips.style.display = "none";
			}
			
			if(psw.value != repsw.value){
				repsw_tips.style.display = "block";
				state = 0;
			}
			else{
				repsw_tips.style.display = "none";
			}
			
			var op = document.getElementById("register-form");
			
			var usernames_tips = getById(op,"reg-username-tips");
			var years_tips = getById(op,"reg-year-tips");
			var residences_tips = getById(op,"reg-residence-tips");
			
			var pattern_year = /^[1-9]+[0-9]*$/;
			var usernames = getById(op,"username");
			var years = getById(op,"year");
			var residences = getById(op,"residence");
			for(var i=0;i<usernames.length;i++){
				if(usernames[i].value == ""){
					usernames_tips[i].style.display = "block";
					state = 0;
				}
				else{
					usernames_tips[i].style.display = "none";
				}

				if(!pattern_year.test(years[i].value)){
					years_tips[i].style.display = "block";
					state = 0;
				}
				else{
					years_tips[i].style.display = "none";
				}

				if(residences[i].value == ""){
					residences_tips[i].style.display = "block";
					state = 0;
				}
				else{
					residences_tips[i].style.display = "none";
				}
			}
			
			if(state == 0){
				return false;
			}
			alert("true");
			return false;
		}
	</script>

</body>
</html>
