package com.example.store.storeApi.persistence.entity;

import lombok.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Data
@Entity
@Getter
@Setter
@Table(name="notes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"}),
        @UniqueConstraint(columnNames = {"description"})
})
public class Note  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "note_user",
                joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))

    private Set<User> Users;


}
