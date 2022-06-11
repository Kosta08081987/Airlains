package telran.java40.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "destination")
@EqualsAndHashCode(of = {"destinatioName"})
public class Destination {
	
	@Id
	String destinatioName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "destination" , cascade = CascadeType.ALL )
	Set<Flyght> flyghts;
	@JsonIgnore
	@OneToMany(mappedBy = "flyght1" , cascade = CascadeType.ALL )
	Set<Flyght> flyghts1;
	@JsonIgnore
	@OneToMany(mappedBy = "flyght2" , cascade = CascadeType.ALL )
	Set<Flyght> flyghts2;

	public Destination(String destinatioName) {
	this.destinatioName = destinatioName;
}

	@Override
	public String toString() {
		return destinatioName;
	}
	
	
	
	
}
