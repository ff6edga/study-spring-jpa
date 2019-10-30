package study.spring.datacommon2.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {
	@Bean
	public PostListener postListener() {
		return new PostListener();
	}

//	// 별도 클래스 정의 없이 익명 클래스로?
//	@Bean
//	public ApplicationListener<PostPublishedEvent> postListener() {
//		return event -> {
//			System.out.println("====================");
//			System.out.println(postPublishedEvent.getPost().getTitle() + " is published !");
//			System.out.println("====================");
//		}
//	}
}
