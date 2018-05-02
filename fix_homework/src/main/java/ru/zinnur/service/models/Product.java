package ru.zinnur.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import ru.zinnur.service.forms.ProductForm;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="manufacturer_id")
    private Manufacturer manufacturer;
    private Integer count;
    private Integer cost;

    public static Product from(ProductForm form){
        return Product.builder()
                .name(form.getName())
                .count(form.getCount())
                .cost(form.getCost())
                .build();
    }
}
