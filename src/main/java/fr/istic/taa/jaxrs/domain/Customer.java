package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Customer extends User implements Serializable {
	
	
	
	
	
	

}
