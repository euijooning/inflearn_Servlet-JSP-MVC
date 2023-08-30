package inflearn.servlet.web.frontcontroller.v3;

import inflearn.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
    // 얘는 서블릿 기술들이 구현되어있지 않음(v2까지는 있었던 것에 비해)
    // 프레임워크에 종속적이지, 서블릿에 종속적이지 않다.
}
