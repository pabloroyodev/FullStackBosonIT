package com.example.ex13.Student.Domain;

import com.example.ex13.Persona.Domain.Persona;
import com.example.ex13.StudentAsignatura.Domain.StudentAsignatura;
import com.example.ex13.utils.PrefixSequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "idStudent",
            strategy = "com.example.ex13.utils.PrefixSequenceGenerator",
            parameters = {
                    @Parameter(name = PrefixSequenceGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "STUD"),
                    @Parameter(name = PrefixSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")
            }
    )
    @Column(name = "id_student")
    private String idStudent;

    //El estudiante tiene los datos de su correspondiente persona
    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "fk_persona")
    private Persona persona;

    @Column(name = "num_hours_week", nullable = false)
    private Integer numHoursWeek;

    private String comments;

    @NotNull
    private String branch;

    //Creamos la tabla para (N:M) llamada "student_asignatura" para
    //relacionar los multiples estudiantes estudiando multiples asignaturas.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_asignatura",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "asignatura_id")}
    )
    List<StudentAsignatura> asignaturas;
}
