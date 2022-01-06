package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
     //private final Map<String, ControllerV4> controllerMap = new HashMap<>();  // 기존의 것. 
     private final Map <String, Object> handlerMappingMap = new HashMap<>();  //controller의 버전에 상관없이 넣을 수 있도록
     private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();//여러 개의 adapter 중에 하나를 찾아쓴다.

     //생성자
     public FrontControllerServletV5(){
          handlerMappingMap.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());
          handlerMappingMap.put("/front-controller/v3/members/save",new MemberSaveControllerV3());
          handlerMappingMap.put("/front-controller/v3/members",new MemberListControllerV3());


     }




}
