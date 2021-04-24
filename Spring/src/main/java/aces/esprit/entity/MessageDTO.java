package aces.esprit.entity;

public class MessageDTO {

	
	private int idsender;
	private int idreciver;
	private String content;
	
	
	public MessageDTO(int idsender, int idreciver, String content) {
		super();
		this.idsender = idsender;
		this.idreciver = idreciver;
		this.content = content;
	}
	
	
	public MessageDTO() {
		super();
	}


	public int getIdsender() {
		return idsender;
	}
	public void setIdsender(int idsender) {
		this.idsender = idsender;
	}
	public int getIdreciver() {
		return idreciver;
	}
	public void setIdreciver(int idreciver) {
		this.idreciver = idreciver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
