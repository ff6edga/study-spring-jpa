package study.spring.jpaexam;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account account = new Account();
		account.setUsername("Jack2");
		account.setPassword("hibernate");

		Study study = new Study();
		study.setName("Sprind Data JPA");

		//양방향 이므로 양 측에 관계를 맵핑하자.
		//특히 주인(Study)은 Optional이 아닌 필수다.
		//account.getStudies().add(study);
		//study.setOwner(account);

		//Use Convenient Method
		account.addStudy(study);

		//JPA
		//entityManager.persist(account);

		//Hibernate
		Session session = entityManager.unwrap(Session.class);
		session.save(account);
		session.save(study);

		//실제 Insert는 아래 load호출 시점보다 늦게 일어난다.
		//그럼에도 jack2 인스턴스를 가져온다 Session에서 1차 캐싱을 하였기 때문이다.
		Account jack2 = session.load(Account.class, account.getId());
		System.out.println("========");
		System.out.println(jack2.getUsername());
	}
}
