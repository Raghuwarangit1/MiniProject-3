package com.nt.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer sid;
	@NonNull
private String sName;
	@NonNull
@ManyToOne(targetEntity =Country.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name ="country_id",referencedColumnName ="cid")
	   @JsonIgnore
private Country countryName;
@OneToMany(targetEntity =City.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="stateName" )
private List<City>cityName;

@Override
public String toString() {
	return " sName=" + sName ;
}


}

