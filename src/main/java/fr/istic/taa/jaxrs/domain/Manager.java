package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("M")
public class Manager extends User implements Serializable{

}
