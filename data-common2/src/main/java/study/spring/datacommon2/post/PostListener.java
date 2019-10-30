package study.spring.datacommon2.post;

import org.springframework.context.event.EventListener;

public class PostListener {
	@EventListener
//public class PostListener implements ApplicationListener<PostPublishedEvent> {
//	@Override
	public void onApplicationEvent(PostPublishedEvent postPublishedEvent) {
		System.out.println("====================");
		System.out.println(postPublishedEvent.getPost().getTitle() + " is published !");
		System.out.println("====================");
	}
}
