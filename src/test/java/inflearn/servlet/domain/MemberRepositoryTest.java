package inflearn.servlet.domain;

import static org.assertj.core.api.Assertions.assertThat;

import inflearn.servlet.domain.member.Member;
import inflearn.servlet.domain.member.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

  MemberRepository memberRepository = MemberRepository.getInstance();
  // 싱글톤이기 때문에 new 해서 새로 생성할 필요가 없음.

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  @DisplayName("회원 저장 테스트")
  void save() {
    //given
    Member member = new Member("hello", 20);

    //when
    Member savedMember = memberRepository.save(member);

    //then
    Member findMember = memberRepository.findById(savedMember.getId());
    assertThat(findMember).isEqualTo(savedMember);
  }

  @Test
  @DisplayName("전체 회원 조회")
  void findAll() {
    //given
    Member member1 = new Member("member1", 20);
    Member member2 = new Member("member2", 30);

    memberRepository.save(member1);
    memberRepository.save(member2);

    //when
    List<Member> result = memberRepository.findAll();

    //then
    assertThat(result.size()).isEqualTo(2);
    assertThat(result).contains(member1, member2);

  }
}