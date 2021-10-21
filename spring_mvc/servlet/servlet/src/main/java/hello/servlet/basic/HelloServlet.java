package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns="/hello")
public class HelloServlet extends HttpServlet {

    //service 메서드는 이 서블릿 호출될때 서비스 메서드가 호출된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");


        String username = request.getParameter("username");
        System.out.println(username);


        response.setContentType("text/plain"); //http 메세지 header에 들어감
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello "+username);  //http 메세지 body에 들어

    }
}
