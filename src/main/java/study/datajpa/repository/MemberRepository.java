package study.datajpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

	@Query(name = "Member.findByUsername")
	List<Member> findByUsername(@Param("username") String username);

	//============ 쿼리 정의하기 기본 ============
	@Query("select m from Member m where m.username = :username and m.age = :age")
	List<Member> findUser(@Param("username") String username, @Param("age") int age);

	//============ 특정 '값' 조회 ============
	@Query("select m.username from Member m")
	List<String> findUsernameList();

	//============ 'DTO' 로 직접 조회 ============
	@Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
	List<MemberDto> findMemberDto();

	//============ 조회조건 in 인 경우 list 로 파라미터 바인딩 ============
	@Query("select m from Member m where m.username in :names")
	List<Member> findByNames(@Param("names") List<String> names);

	//============ 여러 반환타입 지원 ============
	List<Member> findListByUsername(String name);

	Member findMemberByUsername(String name);

	Optional<Member> findOptionalByUsername(String name);
}
