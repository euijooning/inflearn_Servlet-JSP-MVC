<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="inflearn.servlet.domain.member.Member" %>
<%@ page import="inflearn.servlet.domain.member.MemberRepository" %>

<% //여기에 자바 코드를 집어 넣을 수 있다.
    //request, response 그냥 사용 가능 <- 나중에 jsp도 서블릿으로 변환되어서 사용. 문법상 지원됨.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인 페이지</a>
</body>
</html>
