package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloservlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");//soutm
        System.out.println("request = " + request);//soutv
        System.out.println("response = " + response);//soutv->response

        String username = request.getParameter("username");//username = request.getParameter (username)하고 앞에 string username =
        System.out.println("username = " + username);//request.get 파라미터로 쿼리 파라미터 조회?

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//두개는 컨텐츠타입 헤더정보에 들어감
        response.getWriter().write("hello "+ username);//바디에 들어감
    }
}
