package inflearn.servlet.web.frontcontroller.v2.controller;

import inflearn.servlet.domain.member.Member;
import inflearn.servlet.domain.member.MemberRepository;
import inflearn.servlet.web.frontcontroller.MyView;
import inflearn.servlet.web.frontcontroller.v2.ControllerV2;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member);

//        String viewPath = "/WEB-INF/views/save-result.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request,response);
// 위의 세줄이 아래 한 코드
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
