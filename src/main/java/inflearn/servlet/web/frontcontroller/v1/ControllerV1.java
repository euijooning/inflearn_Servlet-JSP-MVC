package inflearn.servlet.web.frontcontroller.v1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    //서블릿과 모양이 똑같은 인터페이스
    // 이것 가지고 구현을 여러 개 할 것이다.
    // 매핑 정보에서 찾아서 호출할 때 다형성을 이용해서 프론트 컨트롤러는 인터페이스에 의존하면서 편리하게 할 수 있음.
}
