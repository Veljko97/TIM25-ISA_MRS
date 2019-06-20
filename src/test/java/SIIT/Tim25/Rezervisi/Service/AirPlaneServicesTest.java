package SIIT.Tim25.Rezervisi.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.AirPlane;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Repository.AirLineRepository;
import siit.tim25.rezervisi.Repository.AirplaneRepository;
import siit.tim25.rezervisi.Services.AirLineServices;
import siit.tim25.rezervisi.Services.AirplaneServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPlaneServicesTest {
	
	@Mock
	private AirplaneRepository airplaneRepo;
	
	private MockMvc mockMvc;

	@InjectMocks
	private AirplaneServices airplaneServices;
	
	
	@Before
	public void setUp() {
	    AirPlane ap = new AirPlane(1,"name",10,10,10,10.5);
	 
	    Mockito.when(airplaneRepo.findOneByName(ap.getName()))
	      .thenReturn(ap);
	    
	    Mockito.when(airplaneRepo.findOne(ap.getId()))
	      .thenReturn(ap);
	}
	
	@Test
	public void findAllTest() {
		AirPlane ap = new AirPlane();
		ap.setId(1);
		ap.setName("BOEING 747");
		when(airplaneRepo.findAll()).thenReturn(Arrays.asList(ap));
		List<AirPlane> lines = airplaneServices.findAll();
		assertThat(lines).hasSize(1);
		assertThat(lines.get(0).getId()).isEqualTo(ap.getId());
	}
	
	@Test
	public void findOneByAirPlaneId() {
	    int id = 1;
	    AirPlane found = airplaneServices.findOne(id);
	  
	     assertThat(found.getId())
	      .isEqualTo(id);
	 }
	
	

}
