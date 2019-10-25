package study.spring.datacommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

	@Autowired
	PostRepository postRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Post> posts = postRepository.findAll();
		posts.forEach(System.out::println);
	}

}
