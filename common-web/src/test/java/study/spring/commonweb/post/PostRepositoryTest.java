package study.spring.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

		List<Post> all =
				// Sort.by 로는 alias나 property 정렬만 가능하지만
				//postRepository.findByTitle("Spring", Sort.by("LENGTH(title)"));
				// 기타조합도 쓰고 싶다면 아래와 같이 사용하자
				postRepository.findByTitle("Spring", JpaSort.unsafe("LENGTH(title)"));
		assertThat(all.size()).isEqualTo(1);
	}

	private Post savePost() {
		Post post = new Post();
		post.setTitle("Spring");
		return postRepository.save(post); // persist
	}

	@Test
	public void updateTitle() {
		Post spring = savePost();

		String hibernate = "hibernate";
		int update = postRepository.updateFile("hibernate", spring.getId());
		assertThat(update).isEqualTo(1);

		// 하나의 트랜잭션내에서 persist 상태가 된 객체는 캐싱되므로
		// update가 되어도 아래처럼 캐시를 날리지 않으면 이전 객체를 리턴한다.
		// @Modifying(clearAutomatically = true, flushAutomatically = true)
		Optional<Post> byId = postRepository.findById(spring.getId());
		assertThat(byId.get().getTitle()).isEqualTo(hibernate);
	}

	@Test
	public void updateTitleByLogic() {
		Post spring = savePost();
		spring.setTitle("hibernate");

		List<Post> all = postRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo("hibernate");
	}

}