package siit.tim25.rezervisi.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.DTO.VehicleDTO;
import siit.tim25.rezervisi.Repository.BranchRepository;
import siit.tim25.rezervisi.Repository.RentACarRepository;
import siit.tim25.rezervisi.Repository.VehicleRepository;

@Component
public class VehicleServices {
	
	@Autowired
	RentACarRepository rentacarRepository;
	
	@Autowired
	BranchRepository branchRepository;

	@Autowired
	VehicleRepository vehicleRepository;
	
	public Vehicle save(Integer serviceId, Vehicle v) {
		RentACarBranch b = v.getBranch();
		b.getVehiclesList().add(v);
		branchRepository.save(b);
		return v;
	}
	
	public Set<VehicleDTO> findAll(Integer serviceId){
		RentACar r = rentacarRepository.findOne(serviceId);
		Set<VehicleDTO> s = new HashSet<VehicleDTO>();
		for(RentACarBranch b: r.getRentACarBranches()) {
			for(Vehicle v: b.getVehiclesList()) {
				s.add(v.convert());
			}
		}

		return s;
	}
	
	public Page<Vehicle> findAll(Integer serviceId, Pageable pageable) {
		return vehicleRepository.findAllByServiceId(serviceId, pageable);
		
	}
	
	public Page<VehicleDTO> findAllAndConvert(Integer serviceId, Pageable pageable) {
		Page<Vehicle> vehicleList = vehicleRepository.findAllByServiceId(serviceId, pageable);
		return vehicleList.map(new Converter<Vehicle, VehicleDTO>() {
		    @Override
		    public VehicleDTO convert(Vehicle entity) {
		        return entity.convert();
		    }
		});
	}
	
	
	public Vehicle findOne(Integer serviceId, Integer vehicleId)
	{
		RentACar r = rentacarRepository.findOne(serviceId);
		for(RentACarBranch br: r.getRentACarBranches()) {
			for(Vehicle v: br.getVehiclesList()) {
				if (v.getIdVehicle() == vehicleId) {
					return v;
				}
			}
		}
		return null;
	}
	
	public Vehicle update(Integer serviceId, Vehicle vehicle) {
		RentACar r = rentacarRepository.findOne(serviceId);
		Vehicle vh = null;
		for(RentACarBranch br: r.getRentACarBranches()) {
			if (br.getIdBranch() == vehicle.getBranch().getIdBranch()) {
				for (Vehicle v: br.getVehiclesList()) {
					if (v.getIdVehicle() == vehicle.getIdVehicle()) {
						vh = v;
						v.setVehicleName(vehicle.getVehicleName());
					}
				}
			}
			
		}
		rentacarRepository.save(r);
		return vh;
	}
	
	public void delete(Integer serviceId, Integer vehicleId) {
		Vehicle vh = this.findOne(serviceId, vehicleId);
		RentACarBranch br = vh.getBranch();
		vh.setBranch(null);
		br.getVehiclesList().remove(vh);
		branchRepository.save(br);
		vehicleRepository.delete(vh);
	}
	
}
