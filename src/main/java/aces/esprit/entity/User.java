package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
@DiscriminatorValue("User")
public class User implements Serializable {
	@Id
	private int id;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User() {
		super();
	}
	@OneToMany(mappedBy = "sender")
	@JsonIgnore
	private List<Message> messagesSent;

	@OneToMany(mappedBy = "receiver")
	@JsonIgnore
	private List<Message> messagesReceived;
	
	
	
	@OneToMany(mappedBy="client")
	private List<Cart> carts;


	

}
