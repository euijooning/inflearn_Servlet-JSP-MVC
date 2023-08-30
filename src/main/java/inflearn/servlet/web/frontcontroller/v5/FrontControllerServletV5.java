package inflearn.servlet.web.frontcontroller.v5;

import inflearn.servlet.web.frontcontroller.ModelView;
import inflearn.servlet.web.frontcontroller.MyView;
import inflearn.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import inflearn.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import inflearn.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import inflearn.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import inflearn.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import inflearn.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import inflearn.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import inflearn.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); //다 지원하기 위해서  Object넣음
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 핸들러 여러개 중에서 찾아 써야 함.

    public FrontControllerServletV5() { // 값 넣기 메서드로 분리
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request); // Object 핸들러 찾아와 핸들러 호출

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV3HandlerAdapter
        // ControllerV4HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 핸들러 어댑터 찾아와 핸들러 어댑터 찾기

        ModelView mv = adapter.handle(request, response, handler); // 핸들 호출

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);

    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // ControllerV3HandlerAdapter
        // ControllerV4HandlerAdapter
        for (MyHandlerAdapter adapter : handlerAdapters) { // 돌려서 어댑터 찾기
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        // 못 찾은 경우
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
