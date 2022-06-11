package telran.java40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java40.service.AirlaineSrvice;

@RestController
@RequestMapping("/airline")
public class AirlainsController {

	AirlaineSrvice airlaineSrvice;
	@Autowired
	public AirlainsController(AirlaineSrvice airlaineSrvice) {
		this.airlaineSrvice = airlaineSrvice;
	}

	@GetMapping("/allDestinations")
	public ResponseEntity<?> getAllDestinations() {
		return ResponseEntity.ok(airlaineSrvice.getAllDestinations());
	}
	
	@GetMapping("/destination/find AllFlight")
	public ResponseEntity<?> getAllFlights(){
		return ResponseEntity.ok(airlaineSrvice.getAllFlights());
	}
	
	@PutMapping("/addDestination")
	public boolean addDestination(@RequestBody String destination) {
		
		return airlaineSrvice.addDestination(destination);
	}
	
	@DeleteMapping("/deleteDestination")
	public void deletedestination(@RequestBody String destination) {
		airlaineSrvice.deleteDestination(destination);
	}
}
