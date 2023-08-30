package inflearn.servlet.web.frontcontroller.v4.controller;


import inflearn.servlet.domain.member.Member;
import inflearn.servlet.domain.member.MemberRepository;
import inflearn.servlet.web.frontcontroller.v4.ControllerV4;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        /*
        ModelView mv = new ModelView("save-result"); // 모델 뷰 생성
        mv.getModel().put("member", member); // 멤버 집어넣고
        return mv; //리턴
        이게 필요가 없다.
         */
        model.put("member", member); // 모델이 위에서 만들어져서 넘어오니까. 값 넣어주기만 하면 됨.
        return "save-result";
    }
}
