import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int score = 0;
        int numQuestions = Integer.parseInt(getServletContext().getInitParameter("numQuestions"));
        for (int i = 0; i < numQuestions; i++) {
            int userAnswer = Integer.parseInt(request.getParameter("answers" + i));
            int correctAnswer = Integer.parseInt(request.getParameter("correctAnswers" + i));
            if (userAnswer == correctAnswer) {
                score++;
            }
        }
        request.setAttribute("score", score);
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}