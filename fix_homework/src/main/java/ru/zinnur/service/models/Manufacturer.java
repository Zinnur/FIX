package ru.zinnur.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

    public Manufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
