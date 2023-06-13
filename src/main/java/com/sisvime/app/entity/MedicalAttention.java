package com.sisvime.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class MedicalAttention {
    @Id
    @GeneratedValue
    private Long Id;

    private LocalDateTime DateAttention;

    @OneToOne
    private Brigade Brigade;
}
