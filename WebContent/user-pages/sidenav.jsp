<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<div class="sidenav affix">
	<!-- 左侧导航选项 -->
	<ul class="nav nav-pills nav-stacked">
		<li id="all-events-flag"><a href="user/all-events"><s:text name="allEvents" /></a></li>
		<li id="reserved-events-flag"><a href="user/reserved-events"><s:text name="reservedEvents" /></a></li>
		<li id="new-event-flag"><a href="user/reserve-new-events"><s:text name="reserveNewEvent" /></a></li>
		<li id="info-flag"><a href="user/user-info"><s:text name="personalInfo" /></a></li>
	</ul>
                
    <!-- 活动提醒列表 -->
    <div class="btn-group" id="recent-events">
    	<button type="button" class="btn btn-info"><s:text name="latestEvents" /></button>
    	<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-list-alt"></span></button>
   		<ul class="dropdown-menu">
    		<li><a href="#">One</a></li>
    		<li><a href="#">Two</a></li>
    		<li><a href="#">Three</a></li>
    	</ul>
    </div>
</div>