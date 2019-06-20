package SIIT.Tim25.Rezervisi.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;



import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Repository.AirLineRepository;
import siit.tim25.rezervisi.Services.AirLineServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirLineServiceTest {
	
 
	@Mock
	private AirLineRepository airLineRepository;
	
	private MockMvc mockMvc;

	@InjectMocks
	private AirLineServices airLineServices;
	
	
	@Before
	public void setUp() {
	    AirLine al = new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city");
	 
	    Mockito.when(airLineRepository.findOneByAirLineName(al.getAirLineName()))
	      .thenReturn(al);
	    
	    Mockito.when(airLineRepository.findOne(al.getAirLineID()))
	      .thenReturn(al);
	}
	
	@Test
	public void testFindAllPageable() {
		
		PageRequest pageRequest = new PageRequest(1, 2); //second page
		when(airLineRepository.findAll(pageRequest)).thenReturn(new PageImpl<AirLine>(Arrays.asList(new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city")).subList(0, 1), pageRequest, 1));
		Page<AirLine> students = airLineServices.findAll(pageRequest);
		assertThat(students).hasSize(1);
		verify(airLineRepository, times(1)).findAll(pageRequest);
        verifyNoMoreInteractions(airLineRepository);
	}
	
	@Test
	public void findAllTest() {
		AirLine al = new AirLine();
		al.setAirLineID(1);
		al.setAirLineName("ime");
		when(airLineRepository.findAll()).thenReturn(Arrays.asList(al));
		List<AirLine> lines = airLineServices.findAll();
		assertThat(lines).hasSize(1);
		assertThat(lines.get(0).getAirLineID()).isEqualTo(al.getAirLineID());
	}
	
	
	@Test
	public void findOneByAirLineNameTest() {
	    String name = "name";
	    AirLine found = airLineServices.findOneByAirLineName(name);
	  
	     assertThat(found.getAirLineName())
	      .isEqualTo(name);
	 }
	
	@Test
	public void findOneByAirLineID() {
	    int id = 1;
	    AirLine found = airLineServices.findOne(id);
	  
	     assertThat(found.getAirLineID())
	      .isEqualTo(id);
	 }
	
	@Test
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
	public void testAddAirline() {
		
		when(airLineRepository.findAll()).thenReturn(Arrays.asList(new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city")));
		AirLine air = new AirLine();
		air.setAirLineID(2);
		air.setAirLineName("AirName");
		air.setAirLineAddress("adresa aerodroma");
		air.setAirLineDescription("cool");
		when(airLineRepository.save(air)).thenReturn(air);
		
		int dbSizeBeforeAdd = airLineServices.findAll().size();
		
		AirLine dbAirline = airLineServices.save(air);
		assertThat(dbAirline).isNotNull();
		
		when(airLineRepository.findAll()).thenReturn(Arrays.asList(new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city"), air));
		// Validate that new airline is in the database
        List<AirLine> airlines = airLineServices.findAll();
        assertThat(airlines).hasSize(dbSizeBeforeAdd + 1);
        dbAirline = airlines.get(airlines.size() - 1); //get last airline
        assertThat(dbAirline.getAirLineID()).isEqualTo(2);
        assertThat(dbAirline.getAirLineName()).isEqualTo("AirName");
        assertThat(dbAirline.getAirLineAddress()).isEqualTo("adresa aerodroma");
        assertThat(dbAirline.getAirLineDescription()).isEqualTo("cool");
        verify(airLineRepository, times(2)).findAll();
        verify(airLineRepository, times(1)).save(air);
        verifyNoMoreInteractions(airLineRepository);
	}
	
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdateAirline() {
		
		when(airLineRepository.findOne(1)).thenReturn(new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city"));
		AirLine dbAirline = airLineServices.findOne(1);
		
		dbAirline.setAirLineID(2);
		dbAirline.setAirLineName("AirName");
		dbAirline.setAirLineAddress("adresa aerodroma");
		dbAirline.setAirLineDescription("cool");
		
		when(airLineRepository.save(dbAirline)).thenReturn(dbAirline);
		dbAirline = airLineServices.save(dbAirline);
		assertThat(dbAirline).isNotNull();
		
		//verify that database contains updated data
		dbAirline = airLineServices.findOne(1);
		assertThat(dbAirline.getAirLineID()).isEqualTo(2);
        assertThat(dbAirline.getAirLineName()).isEqualTo("AirName");
        assertThat(dbAirline.getAirLineAddress()).isEqualTo("adresa aerodroma");
        assertThat(dbAirline.getAirLineDescription()).isEqualTo("cool");
        verify(airLineRepository, times(2)).findOne(1);
        verify(airLineRepository, times(1)).save(dbAirline);
        verifyNoMoreInteractions(airLineRepository);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveAirLine() {
		
		when(airLineRepository.findAll()).thenReturn(Arrays.asList(new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city"),
				new AirLine(2,"name2","address2","description2", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city2")));
		int dbSizeBeforeRemove = airLineServices.findAll().size();
		doNothing().when(airLineRepository).delete(1);
		airLineRepository.delete(1);
		
		when(airLineRepository.findAll()).thenReturn(Arrays.asList((new AirLine(1,"name","address","description", new HashSet<Destination>(),new HashSet<Flight>(),new HashSet<Ticket>(),5.0,100.0,"city"))));
		List<AirLine> airlines = airLineServices.findAll();
		assertThat(airlines).hasSize(dbSizeBeforeRemove - 1);
		
		when(airLineRepository.findOne(1)).thenReturn(null);
		AirLine dbAirline = airLineServices.findOne(1);
		assertThat(dbAirline).isNull();
		verify(airLineRepository, times(1)).delete(1);
		verify(airLineRepository, times(2)).findAll();
        verify(airLineRepository, times(1)).findOne(1);
        verifyNoMoreInteractions(airLineRepository);
	}
	
	
	/* negative tests*/
	

	@Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
	public void testAddNonUniqueId() {
		
		AirLine air = new AirLine();
		air.setAirLineID(1); //id already exist
		air.setAirLineName("AirName");
		air.setAirLineAddress("adresa aerodroma");
		air.setAirLineDescription("cool");
		
		when(airLineRepository.save(air)).thenThrow(DataIntegrityViolationException.class);
		airLineServices.save(air);
		verify(airLineRepository, times(1)).save(air);
        verifyNoMoreInteractions(airLineRepository);
		
	}
	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullId() {
		
		AirLine air = new AirLine();
		air.setAirLineName("AirName");
		air.setAirLineAddress("adresa aerodroma");
		air.setAirLineDescription("cool");
		
		when(airLineRepository.save(air)).thenThrow(DataIntegrityViolationException.class);
		airLineServices.save(air);
		verify(airLineRepository, times(1)).save(air);
        verifyNoMoreInteractions(airLineRepository);
	}

	
	
}
