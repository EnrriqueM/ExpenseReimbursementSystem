package models;

import java.time.LocalDateTime;

public class Reimbursement 
{
	//Properties
	private Integer id;
	private double amount;
	private	LocalDateTime dateCreated;
	private LocalDateTime dateResolved;
	private String description;
	private byte[] file;
	private Integer author_id;
	private String author_firstname;
	private String author_lastname;
	private Integer resolver_id;
	private String resolver_firstname;
	private String resolver_lastname;
	private ReimbursementType type;
	private ReimbursementStatus status;
	
	//GETTERS and SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDateTime getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(LocalDateTime dateResolved) {
		this.dateResolved = dateResolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getAuthor_firstname() {
		return author_firstname;
	}
	public void setAuthor_firstname(String author_firstname) {
		this.author_firstname = author_firstname;
	}
	public String getAuthor_lastname() {
		return author_lastname;
	}
	public void setAuthor_lastname(String author_lastname) {
		this.author_lastname = author_lastname;
	}
	public String getResolver_firstname() {
		return resolver_firstname;
	}
	public void setResolver_firstname(String resolver_firstname) {
		this.resolver_firstname = resolver_firstname;
	}
	public String getResolver_lastname() {
		return resolver_lastname;
	}
	public void setResolver_lastname(String resolver_lastname) {
		this.resolver_lastname = resolver_lastname;
	}
	public ReimbursementType getType() {
		return type;
	}
	public void setType(ReimbursementType type) {
		this.type = type;
	}
	public ReimbursementStatus getStatus() {
		return status;
	}
	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
	public Integer getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
	public Integer getResolver_id() {
		return resolver_id;
	}
	public void setResolver_id(Integer resolver_id) {
		this.resolver_id = resolver_id;
	}
	
	//OVERRIDE
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		//Test for unmatching pairs
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		//If made it here, must be equal
		return true;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", ownerName=" + author_firstname + ' ' + author_lastname + ", amount=" + amount + ", type=" + type + ", status="
				+ status + "]";
	}
}
