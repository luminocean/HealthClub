<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="sidenav affix">
	<!-- 左侧导航选项 -->
	<ul class="nav nav-pills nav-stacked">
		<li id="all-events-flag"><a href="user/all-events">所有活动</a></li>
		<li id="reserved-events-flag"><a href="user/reserved-events">已预约的活动</a></li>
		<li id="new-event-flag"><a href="user/reserve-new-events">预约新活动</a></li>
		<li id="info-flag"><a href="user/user-info">我的信息</a></li>
	</ul>
                
    <!-- 活动提醒列表 -->
    <div class="btn-group" id="recent-events">
    	<button type="button" class="btn btn-info">最近活动</button>
    	<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-list-alt"></span></button>
   		<ul class="dropdown-menu">
    		<li><a href="#">One</a></li>
    		<li><a href="#">Two</a></li>
    		<li><a href="#">Three</a></li>
    	</ul>
    </div>
</div>