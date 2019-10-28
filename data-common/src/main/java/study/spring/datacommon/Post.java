package study.spring.datacommon;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@OneToMany(mappedBy = "post",
			cascade = CascadeType.ALL)
	private Set<Comment> comnents = new HashSet<>();

	public void addComment(Comment comment) {
		this.getComnents().add(comment);
		 comment.setPost(this);
	}

	public Set<Comment> getComnents() {
		return comnents;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", title='" + title + '\'' +
				", comnents=" + comnents +
				'}';
	}
}
