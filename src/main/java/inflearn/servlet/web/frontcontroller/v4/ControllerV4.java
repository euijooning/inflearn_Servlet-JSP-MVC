package inflearn.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /** 치고 엔터치면 이 주석 나온다.
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
