package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
          initHandlerMappingMap();

          initHandlerAdapters();
     }

     private void initHandlerMappingMap() {
          handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());
          handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());
          handlerMappingMap.put("/front-controller/v5/v3/members",new MemberListControllerV3());

          //v4 추가
          handlerMappingMap.put("/front-controller/v5/v4/members/new-form",new MemberFormControllerV4());
          handlerMappingMap.put("/front-controller/v5/v4/members/save",new MemberSaveControllerV4());
          handlerMappingMap.put("/front-controller/v5/v4/members",new MemberListControllerV4());
     }

     private void initHandlerAdapters() {
          handlerAdapters.add(new ControllerV3HandlerAdapter());
          handlerAdapters.add(new ControllerV4HandlerAdapter()); //v4 추가
     }

     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          Object handler = getHandler(request); //handler를 찾는다. ex) MemberFormControllerV4 반환
          
          if (handler == null){
               response.setStatus(HttpServletResponse.SC_NOT_FOUND);
               return;
          }

          MyHandlerAdapter adapter = getHandlerAdapter(handler); //adapter를 찾는다 . 반환 받은 ControllerV4HandlerAdpater.
          ModelView mv = adapter.handle(request,response,handler); //modelview 반환 받음.

          String viewName = mv.getViewName();
          MyView view = viewResolver(viewName);

          view.render(mv.getModel(), request, response);
     }

     private Object getHandler(HttpServletRequest request) {
          String requestURI = request.getRequestURI();
          return handlerMappingMap.get(requestURI);
     }

     private MyHandlerAdapter getHandlerAdapter(Object handler) {
         //MemberFormControllerV4 . handler 값.
          for (MyHandlerAdapter adapter : handlerAdapters) {
               if (adapter.supports(handler)){  //v3, v4 맞는지 리스트 확인 후 adapter 반환. ControllerV4HandlerAdapter 반환.
                    return adapter;
               }
          }
          throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler= "+handler); //support하는 adapter를 못찾을 때 예외.
     }

     private MyView viewResolver(String viewName) {
          return new MyView("/WEB-INF/views/"+ viewName + ".jsp");
     }
}
