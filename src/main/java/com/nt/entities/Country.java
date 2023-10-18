package com.nt.entities;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer cid;
@NonNull
private String cName;

@OneToMany(targetEntity =State.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="countryName" )
private List<State>state;

@Override
public String toString() {
	return "cName="+cName ;
}

}


