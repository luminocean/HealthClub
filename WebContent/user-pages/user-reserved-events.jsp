<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
</head>

<body>

<s:include value="navbar.jsp" />



<!-- 页面主体 -->
<div class="container">
	<div class="row">
    	<!-- 左侧栏 -->
        <div class="col-md-3">
            
            <s:include value="sidenav.jsp" />
            
        </div><!-- 左侧栏End -->
        
        
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
                  <p class="page-title"><span class="glyphicon glyphicon-paperclip" /> 已预约的活动</p>
                </div>
                
                
                <s:actionerror />
            
                <!-- 迭代显示所有内容 -->
                <s:iterator var="item" value="#request['reservedEvents']" status="status">
                	<s:push value="#item">
    					<s:include value="event-item.jsp"></s:include>
					</s:push>
                </s:iterator>
 
            </div>
        </div>
    </div>
</div>


<!--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>-->

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script>
	$("#reserved-events-flag").attr("class","active");
</script>

</body>
</html>
