package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line] 응답의 첫번째 line
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-headers]
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello"); //임의의 header를 만들 수도 있다.

        //[Header 편의 메서드]
        content(response);
        cookie(response); //쿠키에 대한 정보
        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("성공");
    }

    private void content(HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse response){
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException{
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location","/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
