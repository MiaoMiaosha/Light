<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>页面不存在</title>
    <style>
        html,
        body {
            margin: 0;
            padding: 0;
            font-size: 14px;
        }
        
        a {
            text-decoration: none;
            color: #000;
        }
        
        body {
            background: #fff;
        }
        
        .notfound img {
            max-width: 100%;
        }
        
        .notfound .notes {
            text-align: center;
            font-size: 18px;
        }
        
        .notfound .notes p {
            padding: 20px 10px;
            color: #a1a1a1;
            margin-bottom: 20px;
        }
        
        .notfound .backhome {
            width: 120px;
            display: block;
            font-size: 14px;
            text-decoration: none;
            padding: 10px 20px;
            border: 1px solid #a1a1a1;
            border-radius: 4px;
            margin: 0 auto;
            color: #a1a1a1;
        }
    </style>
</head>
<body>
	 <div class="notfound">
        <img src="<%=basePath%>/img/404.jpg" alt="">
        <div class="notes">
            <p>亲~你所访问的页面不存在哦!</p>
<!--             <a href="#" class="backhome">返回首页</a>
 -->        </div>
    </div>

</body>
</html>