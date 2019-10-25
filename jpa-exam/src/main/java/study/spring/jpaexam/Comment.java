package study.spring.jpaexam;

import javax.persistence.*;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Post post;

	private String comment;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", comment='" + comment + '\'' +
				'}';
	}
}
