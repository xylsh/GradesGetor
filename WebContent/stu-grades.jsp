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
<link href="css/stu-grades.css" rel="stylesheet">

</head>

<body>

        <table class="table table-striped">
            <caption>
                <h3><a href="./" target="_blank">本学期成绩</a></h3>
            </caption>
            <thead>
                <tr>
                    <th>课号</th>
                    <th>课名</th>
                    <th>学分</th>
                    <th>属性</th>
                    <th>成绩</th>
                </tr>
            </thead>
            <tbody>
            <s:iterator value="grades" id="grade">
                <tr>
                    <td><s:property value="#grade.courseNumber" /></td>
                    <td><s:property value="#grade.courseName" /></td>
                    <td><s:property value="#grade.courseCredit" /></td>
                    <td><s:property value="#grade.courseAttribute" /></td>
                    <td><s:property value="#grade.courseGrade" /></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>

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

</body>
</html>