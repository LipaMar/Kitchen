package main;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="products")
public class Product implements Serializable{
	private static final long serialVersionUID = -6425688262558835094L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID;
	@Column(name = "Name",unique=true)
	private String Name;
	@JoinColumn()
	public Product() {
		Name = null;
	}
	public Product(String name) {
		Name = name;
	}
	public Integer getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public void setName(String name) {
		Name = name;
	}
	
}
