package study.spring.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static study.spring.commonweb.post.CommentSpecs.isBest;
import static study.spring.commonweb.post.CommentSpecs.isGood;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
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
		comment.setComment("spring data jpa projection");
		comment.setPost(savedPost);
		comment.setUp(10);
		comment.setDown(1);
		commentRepository.save(comment);

		commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class)
				.forEach(c -> {
					System.out.println("==================");
					System.out.println(c.getComment());
				});
	}

	@Test
	public void specs() {
		Page<Comment> page = commentRepository.findAll(isBest().or(isGood()),
				PageRequest.of(0, 10));
	}

	@Test
	public void qbe() {
		Comment prove = new Comment();
		prove.setBest(true);

		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withIgnorePaths("up", "down");

		Example<Comment> example = Example.of(prove, exampleMatcher);

		commentRepository.findAll(example);
	}
}