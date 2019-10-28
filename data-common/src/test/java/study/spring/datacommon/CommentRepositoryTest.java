package study.spring.datacommon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CommentRepositoryTest {

	@Autowired
	CommentRepository commentRepository;

	@Test
	public void crud() {

		// Optional Supports Test
//		Optional<Comment> byId = commentRepository.findById(100l);
//		assertThat(byId).isEmpty();
//		Comment comment = byId.orElseThrow(IllegalAccessError::new);

		// Collection never retuns Null
//		List<Comment> comments = commentRepository.findAll();
//		assertThat(comments).isEmpty();,
	}
}