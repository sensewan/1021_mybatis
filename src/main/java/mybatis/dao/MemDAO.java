package mybatis.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.MemVO;

public class MemDAO {
	
	// ↱ private지만 템플릿이 이미 만들어져 있다면!! (root-context) 스프링이 접근해서 만들어줌 (setter가 없어도 자동으로 됨)
	@Autowired
	private SqlSessionTemplate sst;
	
	public MemDAO() {
		System.out.println("MemDao!~!~생성자 테스트");
	}
	
	
	// ↱ 회원 목록 만들기  (static을 사용하지 않은 이유는 다른 곳, 다른 사람에 의해 빈번하게 사용 될 경우 
	//   혼선이 이루어 질 수 있으므로 한개 만들어서 계속 쓸거임 (싱글톤과 비슷한 느낌) /  필요한 컨트롤러에서 갖다 쓸경우?
	public MemVO[] getAll() {
		MemVO[] ar = null;
		
		List<MemVO> list = sst.selectList("mem.all");
		
		if (list != null && list.size() > 0) {
			ar = new MemVO[list.size()];  // 현재 배열은 MemVO가 생성된 것이 아니라 MemVO를 저장할 수 있는 공간이 마련된 것임
			                              // 그리고 그 크기가 list의 크기와 같다.
			
			// list에 있는 각요소들을 배열인 ar에 복사한다.
			list.toArray(ar);
			
		}
		
		
		
		return ar;
	}
	
	
}
