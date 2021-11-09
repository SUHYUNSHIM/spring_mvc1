<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>

    <li> long id <%=((Member)request.getAttribute("member")).getId()%></>
    <li> long username <%=((Member)request.getAttribute("member")).getUsername()%></>
    <li> long age <%=((Member)request.getAttribute("member")).getAge()%></>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
