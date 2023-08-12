package com.sys.EmployeeMicroservices;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
@FieldNameConstants
@Entity
@Data
public class Employee {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int eid;
private String ename;
private int esalary;

@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = PhoneNumber.Fields.employee)
@EqualsAndHashCode.Exclude
@ToString.Exclude
@JsonManagedReference
private Set<PhoneNumber> phonenumber;
}
