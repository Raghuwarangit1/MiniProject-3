package com.nt.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity

@NoArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer uId;
	@NonNull
private String firstName;
	@NonNull
private String lastName;
	@NonNull
private Long phNo;
	@NonNull
@DateTimeFormat(pattern = "yyyy-MM-dd")
private LocalDate dob;
	@NonNull
private String gender;
	@NonNull
private String country;
	@NonNull
private String state;
	@NonNull
private String city;
	
private Integer status=1;
@OneToOne(targetEntity =Credentials.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY )
private Credentials credentials;
@Override
public String toString() {
	return "Users [uId=" + uId + ", firstName=" + firstName + ", lastName=" + lastName + ", phNo=" + phNo + ", dob="
			+ dob + ", gender=" + gender + ", country=" + country + ", state=" + state + ", city=" + city + ", status="
			+ status + "]";
}


}
