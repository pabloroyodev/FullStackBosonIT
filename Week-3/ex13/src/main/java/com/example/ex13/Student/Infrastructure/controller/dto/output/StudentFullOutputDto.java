package com.example.ex13.Student.Infrastructure.controller.dto.output;

import com.example.ex13.Student.Domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentFullOutputDto extends StudentOutputDto{
    String user;
    String password;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    LocalDate createdDate;
    String imageUrl;
    LocalDate terminationDate;

    public StudentFullOutputDto(Student student){
        super(student);
        setUser(student.getPersona().getUser());
        setPassword(student.getPersona().getPassword());
        setName(student.getPersona().getName());
        setSurname(student.getPersona().getSurname());
        setCompanyEmail(student.getPersona().getCompanyEmail());
        setPersonalEmail(student.getPersona().getPersonalEmail());
        setCity(student.getPersona().getCity());
        setActive(student.getPersona().getActive());
        setCreatedDate(student.getPersona().getCreatedDate());
        setImageUrl(student.getPersona().getImageUrl());
        setTerminationDate(student.getPersona().getTerminationDate());
    }
}
