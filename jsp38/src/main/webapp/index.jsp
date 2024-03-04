<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Addition Quiz</title>
</head>
<body>
<h1>Addition Quiz</h1>
<form action="quiz" method="post">
    <% 
        int num1, num2, answer;
        int numQuestions = Integer.parseInt(getServletContext().getInitParameter("numQuestions"));
        for (int i = 0; i < numQuestions; i++) {
            num1 = (int)(Math.random() * 100) + 1;
            num2 = (int)(Math.random() * 100) + 1;
            answer = num1 + num2;
    %>
    <p>
        <%= num1 %> + <%= num2 %> =
        <input type="text" name="answers<%= i %>" size="4">
        <input type="hidden" name="correctAnswers<%= i %>" value="<%= answer %>">
    </p>
    <% } %>
    <input type="submit" value="Submit">
</form>
</body>
</html>