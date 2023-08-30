package inflearn.servlet.web.frontcontroller.v3;

import inflearn.servlet.web.frontcontroller.ModelView;
import inflearn.servlet.web.frontcontroller.MyView;
import inflearn.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import inflearn.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import inflearn.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

//        MyView view = controller.process(request, response);
//        view.render(request, response); 이거 아님.
        Map<String, String> paramMap = createParamMap(request); // 디테일하니까 메서드로 분리
        ModelView mv = controller.process(paramMap); // 여기서 받기

        String viewName = mv.getViewName(); // 이 부분에서는 논리이름만 얻음
        MyView view = viewResolver(viewName); // 뷰 리졸버가 객체까지 만들어주는 것. 설계상 메서드 분리
//      위 코드가 바로 이 것. new MyView("/WEB-INF/views/" + viewName + ".jsp")

        // 모델을 render에 같이 넘겨줘야 함.
        view.render(mv.getModel(), request, response); // MyView에 아랫쪽에 있는 render(~);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>(); // Map 만들기
        request.getParameterNames() // 모든 파라미터 다 가져오기
                .asIterator() // 돌면서 Map에다가 값을 다 넣어주기
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
