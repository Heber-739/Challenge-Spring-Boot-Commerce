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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @NotNull
    @Min(1000000)
    @Column(name = "dni", unique = true)
    private int dni;

    @NotNull
    @Column(name = "enabled")
    private boolean enabled;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotNull
    @NotBlank
    @Column(name = "phone_number", unique = true)
    private String phone_number;

    @NotNull
    @NotBlank
    @Column(name = "direction")
    private String direction;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Sale> sales = new HashSet<>();

    //Methods
    public void addSale(Sale sale) {
        this.sales.add(sale);
        sale.setClient(this);
    }

    public void removeSale(int id) {
        Sale sale = this.sales.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (sale != null) {
            this.sales.remove(sale);
        }
    }
}
