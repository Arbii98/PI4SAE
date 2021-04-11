package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;


@Entity
@Component
public class Commande implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;

	
	private float total;
	
	
	@Enumerated(EnumType.STRING)
	private OrderStatus etat;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOrder;
	
	
	@OneToMany(mappedBy="commande")
	private List<Cart> carts;
	
	public Commande()
	{
	}
	
	public Commande(int id)
	{
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public OrderStatus getEtat() {
		return etat;
	}

	public void setEtat(OrderStatus etat) {
		this.etat = etat;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	
	
	
	
	
	
}
