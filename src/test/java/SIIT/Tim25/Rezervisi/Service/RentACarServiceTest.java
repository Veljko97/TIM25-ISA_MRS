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
import java.util.Set;

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


import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.HotelOffer;
import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Beans.RentACarOffer;
import siit.tim25.rezervisi.Beans.Room;

import siit.tim25.rezervisi.Beans.Grades.HotelGrade;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.Repository.HotelRepository;
import siit.tim25.rezervisi.Repository.RentACarRepository;
import siit.tim25.rezervisi.Services.HotelServices;
import siit.tim25.rezervisi.Services.RentACarServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarServiceTest {

	@Mock
	private RentACarRepository rentacarRepository;
	
	private MockMvc mockMvc;

	@InjectMocks
	private RentACarServices rentacarServices;
	
	
	@Before
	public void setUp() {
	    RentACar rent = new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0);
	 
	    Mockito.when(rentacarRepository.findOne(rent.getRentACarID()))
	      .thenReturn(rent);
	    
	}
	
	@Test
	public void findOneByRentId() {
	    int id = 1;
	    RentACar found = rentacarServices.findOne(id);
	  
	     assertThat(found.getRentACarID() )
	      .isEqualTo(id);
	 }
	
	@Test
	public void findOneByRentACarName() {
	    String name = "rentACarName";
	    RentACar found = rentacarServices.findOneByRentACarName(name);
	  
	     assertThat(found.getRentACarName())
	      .isEqualTo(name);
	 }
	
	@Test
	public void testFindAllPageable() {
		PageRequest pageRequest = new PageRequest(1, 2); //second page
		when(rentacarRepository.findAll(pageRequest)).thenReturn(new PageImpl<RentACar>(Arrays.asList(new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0))));
		Page<RentACar> rentacars = rentacarServices.findAll(pageRequest);
		assertThat(rentacars).hasSize(1);
		verify(rentacarRepository, times(1)).findAll(pageRequest);
        verifyNoMoreInteractions(rentacarRepository);
	}
	
	@Test
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
	public void testAddRentACar() {
		
		when(rentacarRepository.findAll()).thenReturn(Arrays.asList(new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0)));
		RentACar rent = new RentACar();
		rent.setRentACarID(1);
		rent.setRentACarName("rentACarName");
		rent.setRentACarDescription("rentACarDescription");
		rent.setRentACarAddress("rentACarAddress");
		when(rentacarRepository.save(rent)).thenReturn(rent);
		
		int dbSizeBeforeAdd = rentacarServices.findAll().size();
		
		RentACar dbRentACar = rentacarServices.save(rent);
		assertThat(dbRentACar).isNotNull();
		
		when(rentacarRepository.findAll()).thenReturn(Arrays.asList(new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0), rent));
		// Validate that new rent is in the database
        List<RentACar> rentacars = rentacarServices.findAll();
        assertThat(rentacars).hasSize(dbSizeBeforeAdd + 1);
        dbRentACar = rentacars.get(rentacars.size() - 1); //get last airline
        assertThat(dbRentACar.getRentACarID()).isEqualTo(1);
        assertThat(dbRentACar.getRentACarName()).isEqualTo("rentACarName");
        assertThat(dbRentACar.getRentACarAddress()).isEqualTo("rentACarAddress");
        assertThat(dbRentACar.getRentACarDescription()).isEqualTo("rentACarDescription");
        verify(rentacarRepository, times(2)).findAll();
        verify(rentacarRepository, times(1)).save(rent);
        verifyNoMoreInteractions(rentacarRepository);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdateRent() {
		
		when(rentacarRepository.findOne(1)).thenReturn(new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0));
		RentACar dbRent = rentacarServices.findOne(1);
		
		dbRent.setRentACarID(2);
		dbRent.setRentACarName("RentName");
		dbRent.setRentACarDescription("rentDesc");
		dbRent.setRentACarAddress("rentAddress");
		
		when(rentacarRepository.save(dbRent)).thenReturn(dbRent);
		dbRent = rentacarServices.save(dbRent);
		assertThat(dbRent).isNotNull();
		
		//verify that database contains updated data
		dbRent = rentacarServices.findOne(1);
		assertThat(dbRent.getRentACarID()).isEqualTo(2);
        assertThat(dbRent.getRentACarName()).isEqualTo("RentName");
        assertThat(dbRent.getRentACarAddress()).isEqualTo("rentAddress");
        assertThat(dbRent.getRentACarDescription()).isEqualTo("rentDesc");
        verify(rentacarRepository, times(2)).findOne(1);
        verify(rentacarRepository, times(1)).save(dbRent);
        verifyNoMoreInteractions(rentacarRepository);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveRent() {
		
		when(rentacarRepository.findAll()).thenReturn(Arrays.asList(new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0),
				new RentACar(2,"rentACarName2","rentACarAddress2", "rentACarDescription2",
						new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
						10.0)));
		int dbSizeBeforeRemove = rentacarServices.findAll().size();
		doNothing().when(rentacarRepository).delete(1);
		rentacarRepository.delete(1);
		
		when(rentacarRepository.findAll()).thenReturn(Arrays.asList((new RentACar(1,"rentACarName","rentACarAddress", "rentACarDescription",
				new HashSet<RentACarOffer>(), new HashSet<RentACarBranch>(),
				10.0))));
		List<RentACar> rents = rentacarRepository.findAll();
		assertThat(rents).hasSize(dbSizeBeforeRemove - 1);
		
		when(rentacarRepository.findOne(1)).thenReturn(null);
		RentACar dbRent = rentacarServices.findOne(1);
		assertThat(dbRent).isNull();
		verify(rentacarRepository, times(1)).delete(1);
		verify(rentacarRepository, times(2)).findAll();
        verify(rentacarRepository, times(1)).findOne(1);
        verifyNoMoreInteractions(rentacarRepository);
	}
	
/* negative tests*/
	

	@Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
	public void testAddNonUniqueId() {
		
		RentACar rent = new RentACar();
		rent.setRentACarID(1);
		rent.setRentACarName("rentACarName2");
		rent.setRentACarDescription("rentACarDescription2");
		rent.setRentACarAddress("rentACarAddress2");
		
		
		when(rentacarRepository.save(rent)).thenThrow(DataIntegrityViolationException.class);
		rentacarServices.save(rent);
		verify(rentacarRepository, times(1)).save(rent);
        verifyNoMoreInteractions(rentacarRepository);
		
	}
	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullId() {
		
		RentACar rent = new RentACar();
		rent.setRentACarName("rentACarName2");
		rent.setRentACarDescription("rentACarDescription2");
		rent.setRentACarAddress("rentACarAddress2");
		
		
		when(rentacarRepository.save(rent)).thenThrow(DataIntegrityViolationException.class);
		rentacarServices.save(rent);
		verify(rentacarRepository, times(1)).save(rent);
        verifyNoMoreInteractions(rentacarRepository);
	}
	
	
}
