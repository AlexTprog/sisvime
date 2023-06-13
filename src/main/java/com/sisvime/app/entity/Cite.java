package com.sisvime.app.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Cite {
    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    private Doctor Doctor;

    @OneToOne
    private Patient Patient;

    private String Diagnostic;

    private String Details;

    @OneToOne
    private Recipe Recipe;

    @NotNull
    private Date CiteDate;

    @NotNull
    private LocalDateTime StartHour;

    @NotNull
    private LocalDateTime EndHour;

    public Cite(LocalDateTime startHour) {
        this.StartHour = startHour;
        this.EndHour =startHour.plusMinutes(30);

    }
}
