package org.iesvdm.examenecommerce.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@EqualsAndHashCode(of = "product_id")
@Table(
        name = "cart_item"
)
public class Cart_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "FK_product"))
    private Product product_id;


    private Long quantity;

    private Date created_date;

    private Date modified_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private User usuario_id;
}
