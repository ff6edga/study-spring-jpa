package study.spring.jpaexam;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "owner")
	private Set<Study> studies = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Study> getStudies() {
		return studies;
	}

	public void setStudies(Set<Study> studies) {
		this.studies = studies;
	}

	public void addStudy(Study study) {
		this.getStudies().add(study);
		study.setOwner(this);
	}
}
