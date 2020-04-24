package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {
    private static final long serialVersionUID = -6425688262558835094L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pid")
    private Integer id;

    @Column(name = "Name", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Uid")
    private Unit unit;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    public Product() {
        name = null;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setID(Integer Id) {
        this.id = Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
