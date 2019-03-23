package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.TestBean;
import siit.tim25.rezervisi.Services.TestServices;

@RestController
@RequestMapping(path="app/test")
public class TestController {
	
	@Autowired
	private TestServices testServices;
	
	@GetMapping(path="/testGet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TestBean>> test() {
		return  new ResponseEntity<ArrayList<TestBean>>(testServices.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(path="/testPost", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public void testPost(@RequestBody TestBean bean) {
		testServices.save(bean);
	}
	
}
