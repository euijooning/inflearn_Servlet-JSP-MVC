package inflearn.servlet.web.frontcontroller.v2;

import inflearn.servlet.web.frontcontroller.MyView;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerV2 {

    // 기존 ControllerV1과 같은데 반환만 MyView를 한다.
    // 기존에는 void였고 컨트롤러가 알아서 다 forward 이동했는데 그냥 MyView를 만들어서 넘기면 되는 식으로 인터페이스를 설계
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
