package inflearn.servlet.web.frontcontroller.v3.controller;

import inflearn.servlet.web.frontcontroller.ModelView;
import inflearn.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); // 전체 경로를 넣지 않고, 논리이름만 넣는다!
    }
}
