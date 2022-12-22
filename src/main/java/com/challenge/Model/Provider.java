package com.challenge.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provider")
public class Provider implements Serializable {

    @Id
    @NotNull
    @Min(1)
    @Column(name = "cuit", unique = true)
    private Long cuit;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "phone_number", unique = true)
    private String phone_number;

    @NotNull
    @NotBlank
    @Column(name = "direction")
    private String direction;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        product.setProvider(this);
        this.products.add(product);
    }

}
