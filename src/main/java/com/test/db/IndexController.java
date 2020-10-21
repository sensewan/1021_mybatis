package com.test.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mybatis.dao.MemDAO;
import mybatis.vo.MemVO;

// rootconext에 의해 움직이지 않음 servlet-context에 의해 움직임

@Controller
public class IndexController {
	
	
	// ↱ DAO사용하는 것으로 자동으로 DAO 만들어진거 들어옴 (servlet-context.xml에 <annotation-driven/>이 있어서 가능함)
	@Autowired
	private MemDAO m_dao;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	
                                      // ↱이렇게 하면 total()은 무조건 POST방식으로만 호출해야 한다.
	@RequestMapping(value = "/total", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> total(){
		              // ↳머가 들어올지 모르므로..배열도 올수 있고 등 (사실 memvo[]로 해도 됨)
		Map<String, Object> map = new HashMap<String, Object>();
		
		// DB사용하기 (DAO사용하기)
		
		MemVO[] ar = m_dao.getAll();
		map.put("list", ar);
		
		return map;  // 여기 total은 index.jsp의 $.ajax비동기식 통신으로 호출되어 졌고 반환을 JSON으로 해야하지만
					 // 현재 Map으로 하고 있다 . 그러므로 Map을 -> JSON으로 변환해야 한다. ->@ResponseBody 적어주면
		
	}
	
	
}
