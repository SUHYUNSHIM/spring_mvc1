package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap); //프레임워크에 종속적. servlet에는 종속적이지 않다. request, response가
    //불필요하기 때문.
}
