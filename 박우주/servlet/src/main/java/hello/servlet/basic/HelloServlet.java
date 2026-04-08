package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //Ctrl + O: protecteds

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); //soutm 입력으로 빠르게 print 적을 수 있음
        System.out.println("req = " + req); //soutv로 매개변수 빠르게 print 가능
        System.out.println("resp = " + resp);

        //http://localhost:8080/hello?username=wow -> ? 쿼리 파라미터
        String username = req.getParameter("username"); //CTRL + ALT + V로 변수 빠르게 선언 가능
        System.out.println("username = " + username);

        //응답 메시지
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello" + username);
    }
}