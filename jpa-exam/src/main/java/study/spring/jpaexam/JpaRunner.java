package study.spring.jpaexam;

import javafx.geometry.Pos;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

/*
 * Post - Comment : Fetch / Cascade     예제
 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
////		Post post = new Post();
////		post.setTitle("Spring Data JPA ");
////
////		Comment comment = new Comment();
////		comment.setComment("This is Comment 1 Message");
////		post.addComment(comment);
////
////		Comment comment1 = new Comment();
////		comment1.setComment("This is Comment 2 Message");
////		post.addComment(comment1);
////
////		Session session = entityManager.unwrap(Session.class);
////		session.save(post);
//		Session session = entityManager.unwrap(Session.class);
//
//		Post post = session.get(Post.class, 1l);
//		System.out.println("==============");
//		System.out.println(post.getTitle());
//
//		post.getComnents().forEach(c -> {
//			System.out.println("============");
//			System.out.println(c.getComment());
//		});
//
////		Comment comment = session.get(Comment.class, 2l);
////		System.out.println("===============");
////		System.out.println(comment.getComment());
////		System.out.println(comment.getPost().getTitle());
////
//	}

	/*
		Account - Study Example
	 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		Account account = new Account();
//		account.setUsername("Jack2");
//		account.setPassword("hibernate");
//
//		Study study = new Study();
//		study.setName("Sprind Data JPA");
//
//		//양방향 이므로 양 측에 관계를 맵핑하자.
//		//특히 주인(Study)은 Optional이 아닌 필수다.
//		//account.getStudies().add(study);
//		//study.setOwner(account);
//
//		//Use Convenient Method
//		account.addStudy(study);
//
//		//JPA
//		//entityManager.persist(account);
//
//		//Hibernate
//		Session session = entityManager.unwrap(Session.class);
//		session.save(account);
//		session.save(study);
//
//		//실제 Insert는 아래 load호출 시점보다 늦게 일어난다.
//		//그럼에도 jack2 인스턴스를 가져온다 Session에서 1차 캐싱을 하였기 때문이다.
//		Account jack2 = session.load(Account.class, account.getId());
//		System.out.println("========");
//		System.out.println(jack2.getUsername());
//	}
}
