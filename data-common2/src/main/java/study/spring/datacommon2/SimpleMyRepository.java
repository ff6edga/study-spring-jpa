package study.spring.datacommon2;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class SimpleMyRepository<T, ID extends Serializable>
				extends SimpleJpaRepository<T, ID>
				implements MyRepository<T, ID> {

	private EntityManager entityManager;
	public SimpleMyRepository(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public boolean contains(T entity) {
		return entityManager.contains(entity);
	}

	//contains()처럼 MyRepository에 언급 없이 Override해도 상관은 없다.
	@Override
	public List<T> findAll() {
		System.out.println("===== custom findAll");
		return super.findAll();
	}
}
