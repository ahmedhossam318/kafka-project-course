package com.course.kafkaproducer.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.Date;

public class Employee {
    @JsonProperty("employee_id")
    private Long employeeId;
    @JsonProperty("name")
    private String name ;
    @JsonProperty("birth_date")
    @JsonFormat(timezone = "MM/dd/yyyy")
    private Date birthDate;

    public Employee() {
    }

    public Employee(Long employeeId, String name, Date birthDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
