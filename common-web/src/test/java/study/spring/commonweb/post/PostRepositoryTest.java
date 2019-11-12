package study.spring.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void save() {
		Post post = new Post();
		post.setTitle("jpa");
		Post savedPost = postRepository.save(post); // insert (persist)

		assertThat(entityManager.contains(post)).isTrue();
		assertThat(entityManager.contains(savedPost)).isTrue();
		assertThat(savedPost == post);

		Post postUpdate = new Post();
		postUpdate.setId(1l);
		postUpdate.setTitle("hibernate");
		// merge가 발생하며 JPA대신 Hibernate에서 수정이 일어난다.
		Post updatedPost = postRepository.save(postUpdate); // update

		assertThat(entityManager.contains(updatedPost)).isTrue();
		// entityManager.merge()가 실행될때, 원본 객체는 영속화 되지 않는다.
		assertThat(entityManager.contains(postUpdate)).isFalse();
		assertThat(updatedPost == postUpdate);

		List<Post> all = postRepository.findAll();
		assertThat(all.size()).isEqualTo(1);

	}

	@Test
	public void findByTitleStartsWith() {
		savePost();

		List<Post> all = postRepository.findByTitleStartsWith("Spring");
		assertThat(all.size()).isEqualTo(1);
	}

	@Test
	public void findByTitle() {
		savePost();

		List<Post> all = postRepository.findByTitle("Spring");
		assertThat(all.size()).isEqualTo(1);
	}

	private void savePost() {
		Post post = new Post();
		post.setTitle("Spring");
		postRepository.save(post); // persist
	}
}