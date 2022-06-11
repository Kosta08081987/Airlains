package telran.java40.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java40.dao.DestinationRepository;
import telran.java40.dao.FlyghtsRepository;
import telran.java40.exception.DestinationAlreadyExistsException;
import telran.java40.model.Destination;
import telran.java40.model.Flyght;

@Service
public class AirlaineServiceImpl implements AirlaineSrvice {

	DestinationRepository destinationRepository;
	FlyghtsRepository flyghtsRepository;

	@Autowired
	public AirlaineServiceImpl(DestinationRepository destinationRepository, FlyghtsRepository flyghtsRepository) {
		this.destinationRepository = destinationRepository;
		this.flyghtsRepository = flyghtsRepository;
	}

	@Override
	public Iterable<String> getAllDestinations() {
		return destinationRepository.findAll().stream().map(d -> d.getDestinatioName()).collect(Collectors.toList());
	}

	@Override
	public Iterable<String> getAllFlights() {
		List<Flyght> flyghts = flyghtsRepository.findAll();
		if(flyghts.size()>0) {
			return flyghts.stream().map(f -> f.getDestination().toString() 
									+ " --> "
									+ f.getFlyght1().toString()
									+ " --> "
									+ f.getFlyght2().orElse(new Destination("")).toString()
									).collect(Collectors.toList());
		}

		return null;
	}

	@Override
//	@Transactional
	public boolean addDestination(String destination) {
		if(destinationRepository.existsById(destination)) {
//			throw new DestinationAlreadyExistsException(destination);
			return false;
		}
		List<String> destinations =  (List<String>) getAllDestinations();
		destinations.add(destination);
		List<Flyght> flyghts = flyghtsRepository.findAll();
		String temp;
		for (int i = 0; i <= destinations.size() - 1; i++) {
			temp = destinations.get(i);
			for (int j = 0; j <= destinations.size() - 1 ; j++) {
				if(j!=i) {
					flyghts.add(new Flyght(new Destination(temp),
								new Destination(destinations.get(j)),null));
				}
			}
		}
		for (int i = 0; i <= destinations.size() - 1; i++) {
			temp = destinations.get(i);
			System.out.println(temp);
			for (int j = 0; j <= destinations.size() - 1 ; j++) {
				for (int k = 0; k <= destinations.size() - 1 ; k++) {
					if(j!=i && k!=i && j!=k) {
						flyghts.add(new Flyght(new Destination(temp), 	
									new Destination(destinations.get(j))
									,new Destination(destinations.get(k))));
					}
				}
			}
		}
		Destination destinationEntity =  new Destination(destination);
		destinationRepository.save(destinationEntity);
		
		flyghts.stream().distinct()
					.forEach(s -> flyghtsRepository.save(s));
		
		return true;
	}

	@Override
	public void deleteDestination(String destinationName) {
		Destination target = destinationRepository.findById(destinationName)
				.orElseThrow(() -> new EntityNotFoundException());
		destinationRepository.delete(target);
	}

}
