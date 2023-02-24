package org.iesvdm.examenecommerce.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="product")
@EqualsAndHashCode(of = "name")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name= "descrip")
    private String descrip;

    private String image_url;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @OneToMany(mappedBy = "product_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    Set<Cart_Item> cartItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "FK_categoria"))
    private Category category_id;

}
