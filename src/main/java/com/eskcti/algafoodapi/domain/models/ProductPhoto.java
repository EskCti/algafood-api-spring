package com.eskcti.algafoodapi.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tab_products_photos")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductPhoto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;

    @Column(name = "name_file", nullable = false)
    private String nameFile;

    @Column
    private String description;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    public Long getRestaurantId() {
        if (getProduct() != null) {
            return this.getProduct().getRestaurant().getId();
        }
        return null;
    }
}
