package inflearn.servlet.web.frontcontroller.v5;


import inflearn.servlet.web.frontcontroller.ModelView;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

    boolean supports(Object handler); //판단

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
    // 실제 컨트롤러 호출, ModelView 반환. 과거에는 프론트 컨트롤러가 실제 컨트롤러 호출했는데... 대체됨!
}
