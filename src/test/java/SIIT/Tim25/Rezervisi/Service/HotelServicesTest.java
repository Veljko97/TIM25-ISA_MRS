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
public class HotelServicesTest {

	@Mock
	private HotelRepository hotelRepository;
	
	private MockMvc mockMvc;

	@InjectMocks
	private HotelServices hotelServices;
	
	
	@Before
	public void setUp() {
	    Hotel h = new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config");
	 
	    Mockito.when(hotelRepository.findOne(h.getHotelID()))
	      .thenReturn(h);
	    
	}
	
	@Test
	public void findOneByHotelId() {
	    int id = 1;
	    Hotel found = hotelServices.findOne(id);
	  
	     assertThat(found.getHotelID())
	      .isEqualTo(id);
	 }
	
	
	@Test
	public void testFindAllPageable() {
		PageRequest pageRequest = new PageRequest(1, 2); //second page
		when(hotelRepository.findAll(pageRequest)).thenReturn(new PageImpl<Hotel>(Arrays.asList(new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config"))));
		Page<Hotel> hotels = hotelServices.findAll(pageRequest);
		assertThat(hotels).hasSize(1);
		verify(hotelRepository, times(1)).findAll(pageRequest);
        verifyNoMoreInteractions(hotelRepository);
	}
	
	@Test
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
	public void testAddHotel() {
		
		when(hotelRepository.findAll()).thenReturn(Arrays.asList(new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config")));
		Hotel hotel = new Hotel();
		hotel.setHotelID(1);
		hotel.setHotelName("hotelname");;
		hotel.setHotelDescription("description");;
		hotel.setHotelAddress("adresa");
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		
		int dbSizeBeforeAdd = hotelServices.findAll().size();
		
		Hotel dbHotel = hotelServices.save(hotel);
		assertThat(dbHotel).isNotNull();
		
		when(hotelRepository.findAll()).thenReturn(Arrays.asList(new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config"), hotel));
		// Validate that new rent is in the database
        List<Hotel> hotels = hotelServices.findAll();
        assertThat(hotels).hasSize(dbSizeBeforeAdd + 1);
        dbHotel = hotels.get(hotels.size() - 1); //get last airline
        assertThat(dbHotel.getHotelID()).isEqualTo(1);
        assertThat(dbHotel.getHotelName()).isEqualTo("hotelname");
        assertThat(dbHotel.getHotelAddress()).isEqualTo("adresa");
        assertThat(dbHotel.getHotelDescription()).isEqualTo("description");
        verify(hotelRepository, times(2)).findAll();
        verify(hotelRepository, times(1)).save(hotel);
        verifyNoMoreInteractions(hotelRepository);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdateHotel() {
		
		when(hotelRepository.findOne(1)).thenReturn(new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config"));
		Hotel dbHotel = hotelServices.findOne(1);
		
		dbHotel.setHotelID(2);
		dbHotel.setHotelName("hotelname");
		dbHotel.setHotelDescription("description");
		dbHotel.setHotelAddress("adresa");
		
		when(hotelRepository.save(dbHotel)).thenReturn(dbHotel);
		dbHotel = hotelServices.save(dbHotel);
		assertThat(dbHotel).isNotNull();
		
		//verify that database contains updated data
		dbHotel = hotelServices.findOne(1);
		 assertThat(dbHotel.getHotelID()).isEqualTo(2);
	     assertThat(dbHotel.getHotelName()).isEqualTo("hotelname");
	     assertThat(dbHotel.getHotelAddress()).isEqualTo("adresa");
	     assertThat(dbHotel.getHotelDescription()).isEqualTo("description");
        verify(hotelRepository, times(2)).findOne(1);
        verify(hotelRepository, times(1)).save(dbHotel);
        verifyNoMoreInteractions(hotelRepository);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveHotel() {
		
		when(hotelRepository.findAll()).thenReturn(Arrays.asList(new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config"),
				new Hotel(2,"hotelName2","hotelAddress2",new Destination(), "hotelDescription2",
						new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
						"config2")));
		int dbSizeBeforeRemove = hotelServices.findAll().size();
		doNothing().when(hotelRepository).delete(1);
		hotelRepository.delete(1);
		
		when(hotelRepository.findAll()).thenReturn(Arrays.asList((new Hotel(1,"hotelName","hotelAddress",new Destination(), "hotelDescription",
				new HashSet<HotelOffer>(), new HashSet<Room>(),new HashSet<HotelAdmin>(),new HashSet<HotelGrade>(),
				"config"))));
		List<Hotel> hotels = hotelRepository.findAll();
		assertThat(hotels).hasSize(dbSizeBeforeRemove - 1);
		
		when(hotelRepository.findOne(1)).thenReturn(null);
		Hotel dbHotel = hotelServices.findOne(1);
		assertThat(dbHotel).isNull();
		verify(hotelRepository, times(1)).delete(1);
		verify(hotelRepository, times(2)).findAll();
        verify(hotelRepository, times(1)).findOne(1);
        verifyNoMoreInteractions(hotelRepository);
	}
	
/* negative tests*/
	

	@Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
	public void testAddNonUniqueId() {
		
		Hotel hotel = new Hotel();
		hotel.setHotelID(1);
		hotel.setHotelName("hotelname");;
		hotel.setHotelDescription("description");;
		hotel.setHotelAddress("adresa");
		
		when(hotelRepository.save(hotel)).thenThrow(DataIntegrityViolationException.class);
		hotelServices.save(hotel);
		verify(hotelRepository, times(1)).save(hotel);
        verifyNoMoreInteractions(hotelRepository);
		
	}
	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullId() {
		
		Hotel hotel = new Hotel();
		hotel.setHotelID(1);
		hotel.setHotelName("hotelname");;
		hotel.setHotelDescription("description");;
		hotel.setHotelAddress("adresa");
		
		
		when(hotelRepository.save(hotel)).thenThrow(DataIntegrityViolationException.class);
		hotelServices.save(hotel);
		verify(hotelRepository, times(1)).save(hotel);
        verifyNoMoreInteractions(hotelRepository);
	}
	
	
}
