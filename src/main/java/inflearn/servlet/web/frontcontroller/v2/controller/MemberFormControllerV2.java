package inflearn.servlet.web.frontcontroller.v2.controller;

import inflearn.servlet.web.frontcontroller.MyView;
import inflearn.servlet.web.frontcontroller.v2.ControllerV2;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //변수 인라인화 시켜서 깔끔하게
//        MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
//        return myView;
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
