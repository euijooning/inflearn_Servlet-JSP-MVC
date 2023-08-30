package inflearn.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {
// 기존에 컨트롤러에서 했던 로직을 MyView 만들어서 분리해서 넣음.
  private String viewPath; // => new MyView("/WEB-INF/views/new-form.jsp")

  // 생성자
  public MyView(String viewPath) {
    this.viewPath = viewPath;
  }

  public void render (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
  }

  public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    modelToRequestAttribute(model, request); // 모델에 있는 데이터를 requestAttribute로 바꾼다.

    // modelToRequestAttribute가 다 끝나고 여기 오면 jsp가 getAttribute해서 값을 쓰겠죠.
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
  }

  private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
    model.forEach((key, value) -> request.setAttribute(key, value)); // 랜더가 오면 다 돌면서 리퀘스트의 키, 밸류를 다 담아놓는다.(setAttribute)
    // jsp는 여기서 값을 꺼내기 때문에 이 작업이 필요하다.
  }
}
