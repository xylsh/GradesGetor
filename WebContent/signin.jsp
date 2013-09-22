<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>CDUESTC信息查询</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">

</head>

<body>

	<div class="container">
	
	    <s:form action="stu-grades" namespace="/" cssClass="form-signin">
	        <h1><a href="./">成绩查询</a></h1>
	        <h2 class="form-signin-heading">Please sign in</h2>
	        <s:textfield name="account" cssClass="form-control" placeholder="Student Number" />
	        <s:password name="password" cssClass="form-control" placeholder="Password" />
	        <s:submit value="Sign in" cssClass="btn btn-lg btn-primary btn-block" />
	    </s:form>
	</div>
	
	<div class="footer">
        Powered by <a href="https://www.openshift.com/" target="_blank">OpenShift</a> . <a href="http://xylsh.github.com/" target="_blank">Author's Blog</a> .
        
        <!-- 统计代码 -->
        <div style="display:none;">
        <script>
            var _hmt = _hmt || [];
            (function() {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?bd0f4bd0c2dc62b7004f36fe3d655a2f";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        </script>
        </div>

    </div>

</body>
</html>
