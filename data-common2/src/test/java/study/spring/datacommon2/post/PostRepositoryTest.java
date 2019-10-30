package study.spring.datacommon2.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
// 여기서
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	ApplicationContext applicationContext;

//	@Test
//	public void event() {
//		// AbstractAggregateRoot class를 도메인이 상속하면 아래 코드는 필요 없다.
//		Post post = new Post();
//		post.setTitle("event");
//		PostPublishedEvent event = new PostPublishedEvent(post);
//		applicationContext.publishEvent(event);
//	}

	@Test
	public void crud() {
		Post post = new Post();
		post.setTitle("hibernate");

		assertThat(postRepository.contains(post)).isFalse();

		postRepository.save(post.publish());

		assertThat(postRepository.contains(post)).isTrue();

		assertThat(postRepository.findAll().isEmpty()).isFalse();

		postRepository.delete(post);
		postRepository.flush();
	}
}