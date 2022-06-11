package telran.java40.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor

@Table(name = "flyghts")
@EqualsAndHashCode(of = {"destination","flyght1","flyght2"})

//@IdClass(CompositeKey.class)
public class Flyght {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@ManyToOne
	Destination destination;
	@ManyToOne
	Destination flyght1;
	@ManyToOne
	Destination flyght2;
	
	
	public Flyght(Destination destination, Destination flyght1, Destination flyght2) {
		this.destination = destination;
		this.flyght1 = flyght1;
		this.flyght2 = flyght2;
	}
	
	public Destination getDestination() {
		return destination;
	}


	public Destination getFlyght1() {
		return flyght1;
	}


	public Optional<Destination> getFlyght2() {
		return Optional.ofNullable(flyght2);
	}

	
	
	
}
