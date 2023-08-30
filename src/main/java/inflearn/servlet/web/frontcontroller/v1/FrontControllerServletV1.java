package inflearn.servlet.web.frontcontroller.v1;

import inflearn.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import inflearn.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import inflearn.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // 별이 들어가면 하위에 어떤 게 들어와도 무조건 호출된다는 뜻
public class FrontControllerServletV1 extends HttpServlet { //서블릿이어야 한다.

    // 매핑 정보
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() { // 생성자에 매핑 정보 다 담아버리기
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();
        /*
        이렇게 하면 /front-controller/v1/members/new-form 이러한 형태를 딱 얻어낼 수가 있다.
         */
        ControllerV1 controller = controllerMap.get(requestURI); // 얻은 정보를 꺼내기
        // 인터페이스로 꺼내면 이 코드를 아주 일관성 있게 사용할 수 있게 된다.
        // 호출 된 것은 셋 중에 하나지만, 다형성에 의거하여 부모인 controller로 받을 수 있게 되는 것.

        if (controller == null) { // 예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 메시지
            return;
        }

        controller.process(request, response); //인터페이스 바로 호출
    }
}
