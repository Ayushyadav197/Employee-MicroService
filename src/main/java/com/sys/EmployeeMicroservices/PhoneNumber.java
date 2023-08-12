package com.sys.EmployeeMicroservices;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
@FieldNameConstants
@Entity
@Data
public class PhoneNumber {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int pid;
private int pno;

@JsonBackReference
@ManyToOne(cascade = CascadeType.ALL,optional = false)
@JoinColumn(name = "eid",nullable = false)
private Employee employee;
}
