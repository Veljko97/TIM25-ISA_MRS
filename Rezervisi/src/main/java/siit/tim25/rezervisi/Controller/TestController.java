package siit.tim25.rezervisi.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import siit.tim25.rezervisi.Beans.TestBean;
import siit.tim25.rezervisi.Services.ImageServices;
import siit.tim25.rezervisi.Services.TestServices;

@RestController
@RequestMapping(path="app/test")
public class TestController {
	
	@Autowired
	private TestServices testServices;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ImageServices imageServices;
	
	@GetMapping(path="/testGet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TestBean>> test() {
		return  new ResponseEntity<ArrayList<TestBean>>(testServices.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(path="/testPost", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public void testPost(@RequestBody TestBean bean) {
		testServices.save(bean);
	}
	
	@PostMapping(path="/testMail", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public void testMail() {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("vmvucenic@Live.com");
		mail.setSubject("Subjekat test");
		mail.setText("Test Poruke");
		mailSender.send(mail);
	}
	
	@PostMapping(path="/testFile")
	public void testFile(@RequestParam("user") MultipartFile img) {
		imageServices.saveUserImg(img, "1");
	}
}
