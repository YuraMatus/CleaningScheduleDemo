package com.сleaning_sсhedule.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Record> records;
}
