package study.spring.datacommon2.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepository;

	@Test
	public void crud() {
		Post post = new Post();
		post.setTitle("hibernate");

		assertThat(postRepository.contains(post)).isFalse();

		postRepository.save(post);

		assertThat(postRepository.contains(post)).isTrue();

		assertThat(postRepository.findAll().isEmpty()).isFalse();

		postRepository.delete(post);
		postRepository.flush();
	}
}