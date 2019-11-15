package study.spring.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PostRepository postRepository;

	@Test
	public void getComment() {
		Post post = new Post();
		post.setTitle("jpa");
		Post savedPost = postRepository.save(post);

		Comment comment = new Comment();
		comment.setComment("comment");
		comment.setPost(savedPost);
		commentRepository.save(comment);

		Optional<Comment> byId = commentRepository.findById(1l);
		System.out.println(byId.get().getPost());
	}

	@Test
	public void getComment1() {
		// load - 해당 메소드를 호출하면 default 말고, 사전에 정의된(EntityGraph)
		// 다른 Fetching 전략으로 필요한 정보를 가져올 수 있다.
		commentRepository.getById(1l);

		System.out.println(" ==================== ");

		commentRepository.findById(1l);
	}
}