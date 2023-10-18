package com.nt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter

@NoArgsConstructor
@RequiredArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer did;

	@NonNull
private String dName;
@NonNull
	@ManyToOne(targetEntity =State.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name="state_name",referencedColumnName ="sid")

private State stateName;
@Override
public String toString() {
	return "dName="+dName ;
}


}
