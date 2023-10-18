package com.nt.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity


@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Credentials {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer crid;
@NonNull
@Column(unique =true)
private String email;
@NonNull
private String password;

@OneToOne(targetEntity =UserInfo.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name ="user_id",referencedColumnName ="uId")
private UserInfo user;
@Override
public String toString() {
	return "UserCredentials [crid=" + crid + ", email=" + email + ", password=" + password +"]";
}


}
