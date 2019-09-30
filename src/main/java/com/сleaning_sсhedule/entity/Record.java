package com.сleaning_sсhedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
