package siit.tim25.rezervisi.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.TestBean;
import siit.tim25.rezervisi.Repository.TestRepository;

@Component
public class TestServices {
	@Autowired
	private TestRepository testRepository;
	
	public boolean save(TestBean test) {
		return testRepository.save(test);
	}
	
	public ArrayList<TestBean> findAll(){
		return testRepository.findAll();
	}
	
}
