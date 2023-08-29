package inflearn.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L; // static 사용
  // -> MemberRepository가 아무리 많아도 얘들은 딱 하나만 생성된다.(static)

  private static final MemberRepository instance = new MemberRepository();

  public static MemberRepository getInstance() {
    return instance;
  }

  private MemberRepository() {}
  // 싱글톤으로 생성할 때는 이렇게 생성자를 막아야 한다.

  // 저장 메서드
  public Member save(Member member) {
    member.setId(++sequence); //하나씩 증가시켜서 설정
    store.put(member.getId(), member);
    return member;
  }

  // 조회
  public Member findById(Long id) {
    return store.get(id);
  }

  // 전체조회
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
    // store에 있는 모든 값들을 다 꺼내서 ArrayList에 담아서 넘겨준다.
  }

  // 테스트코드용 초기화 메서드
  public void clearStore() {
    store.clear();
  }

}
