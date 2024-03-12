package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "branches")
    private List<Book> books = new ArrayList<>();
    public Branch(int code, String name, String manager, String location) {
        this.code = code;
        this.name = name;
        this.manager = manager;
        this.location = location;
    }
}
