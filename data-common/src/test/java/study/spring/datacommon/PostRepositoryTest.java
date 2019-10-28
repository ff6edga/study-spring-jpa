package study.spring.datacommon;

import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepository;

	@Test
	//@Transactional에 의한 기본 롤백을 하고 싶지 않다면
	//아래 어노테이션을 쓴다
	//@Rollback(false)
	public void crudRepository() {
		// Given
		Post post = new Post();
		post.setTitle("hello spring boot common");
		// Mather param 작성이 번거로우므로 Junit 대신 AssertJ의 것으로 사용.
		assertThat(post.getId()).isNull();

		// When
		Post newPost = postRepository.save(post);

		// Then
		assertThat(newPost.getId()).isNotNull();

		// When
		List<Post> posts = postRepository.findAll();

		// Then
		//assertThat(posts.size()).isEqualTo(5);
		assertThat(posts).contains(newPost);

		// When
		Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));

		//Then
		assertThat(page.getTotalElements()).isEqualTo(8);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getSize()).isEqualTo(10);

		// When
		page = postRepository.findByTitleContains("spring",
						PageRequest.of(0, 10));

		//Then
		assertThat(page.getTotalElements()).isEqualTo(7);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getSize()).isEqualTo(10);
	}
}