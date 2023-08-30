package inflearn.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelView {
    private String viewName; //뷰의 논리적 이름
    private Map<String, Object> model = new HashMap<>(); // 모델 직접 생성
    //이렇게 모델에다가 내가 원하는 데이터를 넣어두면 나중에 꺼내서 jsp에서 쓸 수 있도록 후처리 해줄 예정

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

}
