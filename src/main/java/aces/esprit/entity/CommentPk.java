package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CommentPk implements Serializable{
	private int idPub;
	private int idUser;
	@Temporal(TemporalType.DATE)
	private Date datecreation;
	public CommentPk(int idPub, int idUser, Date datecreation) {
		super();
		this.idPub = idPub;
		this.idUser = idUser;
		this.datecreation = datecreation;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public int getIdPub() {
		return idPub;
	}
	public void setIdPub(int idPub) {
		this.idPub = idPub;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public CommentPk() {
		super();
	}
	

}
