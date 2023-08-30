package inflearn.servlet.web.frontcontroller.v5.adapter;


import inflearn.servlet.web.frontcontroller.ModelView;
import inflearn.servlet.web.frontcontroller.v3.ControllerV3;
import inflearn.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler; // support()에서 한 번 걸렀기 때문에 괜찮다. 형변환 해준 다음에

        Map<String, String> paramMap = createParamMap(request); //V3에서 했던 로직과 동일
        ModelView mv = controller.process(paramMap); // 실제 컨트롤러 호출, 모델뷰 반환

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }


}
