package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 { //controller interface. 메소드 구현을 통해서 controller를 모아서 호출
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
