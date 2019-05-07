package siit.tim25.rezervisi.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServices {
	final private Path imgStatic = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static");
	final private Path imgPath = Paths.get("assets", "images");
	
	public String saveImg(MultipartFile img, String id, String fileDir) {
		String extention = img.getOriginalFilename().split("\\.")[1];
		String name = Paths.get(imgPath.toString(), fileDir, id.toString() + "." + extention).toString();
		String savePath = Paths.get(imgStatic.toString(), name).toString();
		try {
			img.transferTo(new File(savePath));
		} catch (IOException e) {
			return "";
		}
		return "../"+name;
	}
	
	public String saveUserImg(MultipartFile img, String id) {
		return this.saveImg(img, id, "users");
	}
	
	public String getUserPath(MultipartFile img, String id) {
		String extention = img.getOriginalFilename().split("\\.")[1];
		String name = Paths.get(imgPath.toString(), "users", id.toString() + "." + extention).toString();
		return "../"+name;
	}
	
	public String saveRentACarImg(MultipartFile img, Integer id) {
		return this.saveImg(img, id.toString(), "rent-a-cars");
	}
	
	public String saveAirlineImg(MultipartFile img, Integer id) {
		return this.saveImg(img, id.toString(), "airlines");
	}
	
	public String saveHotelImg(MultipartFile img, Integer id) {
		return this.saveImg(img, id.toString(), "hotels");
	}
	
	public String saveDestinationImg(MultipartFile img, Integer id) {
		return this.saveImg(img, id.toString(), "destinations");
	}
}
