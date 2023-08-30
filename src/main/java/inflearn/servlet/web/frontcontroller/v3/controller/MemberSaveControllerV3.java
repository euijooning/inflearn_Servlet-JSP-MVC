package inflearn.servlet.web.frontcontroller.v3.controller;


import inflearn.servlet.domain.member.Member;
import inflearn.servlet.domain.member.MemberRepository;
import inflearn.servlet.web.frontcontroller.ModelView;
import inflearn.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        /*
        HttpServletRequest에서 getAttribute 해서 막 이렇게 복잡하게 꺼내는 게 아니라
        그 작업은 다 프론트 컨트롤러에서 처리하고
        Map에다가 요청 파라미터 정보들을 다 담아서 넘겨줄 것이다.
        그럼 단순히 여기서는 꺼내서 쓰기만 하면 된다.
         */
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age")); // 문자로 오므로 숫자로 바꿔줘야 한다.

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result"); // 모델 뷰 생성
        mv.getModel().put("member", member); // 멤버 집어넣고
        return mv; //리턴
    }
}
