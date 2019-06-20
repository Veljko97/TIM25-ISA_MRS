package siit.tim25.rezervisi.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.TestBean;

@Component
public class TestRepository {
	
	private ArrayList<TestBean> tests = new ArrayList<TestBean>();
	
	public boolean save(TestBean test) {
		test.setId(tests.size());
		tests.add(test);
		return true;
	}
	
	public ArrayList<TestBean> findAll(){
		return tests;
	}
	
}
