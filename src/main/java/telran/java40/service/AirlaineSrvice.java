package telran.java40.service;

public interface AirlaineSrvice {
	
	public Iterable<String> getAllDestinations();

	public Iterable<String> getAllFlights();

	public boolean addDestination(String destination);
	
	public void deleteDestination(String destinationName);
}
