package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

//파라미터 전송
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames(); //전체 파라미터 네임 가져오기
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + req.getParameter(paramName)));

        //단일 파라미터
        String username = req.getParameter("username");
        String age = req.getParameter("age");

        System.out.println("age = " + age);
        System.out.println("username = " + username);

        
        //복수 파라미터 itr 입력하면 for문 만들어짐
        for (String name : req.getParameterValues("username")) {
            System.out.println("name = " + name);
        }

        resp.getWriter().write(req.getParameter("username"));


    }
}
