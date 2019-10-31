package study.spring.commonweb.post;

import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
/* 아래 어노테이션과 함께 /test/resources/application-test.properties
 * 에 테스트시 사용할 내용만 덧붙여서 사용할 수 있다.
 */
//@ActiveProfiles("test")
public class PostControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PostRepository postRepository;

	@Test
	public void getPost() throws Exception {
		Post post = new Post();
		post.setTitle("jpa");
		postRepository.save(post);

		mockMvc.perform(get("/posts/" + post.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("jpa"));
	}

	@Test
	public void getPosts() throws Exception {
		Post post = new Post();
		post.setTitle("jpa");
		postRepository.save(post);

		mockMvc.perform(get("/posts/")
					.param("page", "0")
					.param("size", "10")
					.param("sort", "created.desc")
					.param("sort", "title"))
				.andDo(print())
				.andExpect(status().isOk());
	}


}