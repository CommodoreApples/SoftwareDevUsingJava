<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Result</title>
</head>
<body>
    <h1>Quiz Result</h1>
    <p>Your score is <%= request.getAttribute("score") %> out of <%= getServletContext().getInitParameter("numQuestions") %>.</p>
</body>
</html>