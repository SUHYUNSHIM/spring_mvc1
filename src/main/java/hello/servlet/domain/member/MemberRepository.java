package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//동시성 문제를 고려하지 않은 것 . ConcurrentHashMap, AtomicLong 사용 고려
public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>(); //키, 값
    private static long sequence = 0L;    //static 하나만 생성되도록.

    private static final MemberRepository instance = new MemberRepository(); //싱글톤으로 만든다
    //이것으로만 객체 조회
    public static MemberRepository getInstance(){
        return instance;
    }
    //싱글톤, private으로 생성자 막음
    private MemberRepository(){

    }
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }

}
