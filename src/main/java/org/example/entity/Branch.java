package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branch")

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Branch {
    @Id
    @Column(name = "branch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "branch_name")
    private String name;

    @Column(name = "manager_name")
    private String manager;

    @Column(name = "branch_location")
    private String location;
}
