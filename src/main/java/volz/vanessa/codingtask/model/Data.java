package volz.vanessa.codingtask.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Data {

	// Assuming PRIMARY_KEY as Long
	@Id
	private Long id;
	private String name;
	private String description;
	// Assuming UPDATED_TIMESTAMP as LocalDateTime
	private LocalDateTime updatedTimestamp;
	
	public Data() {
		super();
	}

	public Data(Long id, String name, String description, LocalDateTime updatedTimestamp) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.updatedTimestamp = updatedTimestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	
}
