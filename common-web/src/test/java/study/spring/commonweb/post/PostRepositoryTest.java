package study.spring.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@Test
	public void crud() {
		Post post = new Post();
		post.setTitle("jpa");

		// 현재 id 프로퍼티에 @GeneratedValue가 빠졌으므로
		// Exception이 발생하는데 SQLException을
		// DataAccessException 계층으로 변경해 주기 때문에
		// 보다 더 자세하고 친절한 Exception내용을 볼 수 있다.
		List<Post> all = postRepository.findAll();
		assertThat(all.size()).isEqualTo(1);
	}
}