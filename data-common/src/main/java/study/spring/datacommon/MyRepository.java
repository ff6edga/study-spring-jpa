package study.spring.datacommon;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable>
		extends Repository<T, Id> {

	//하위 타입도 함께 지원 예정.
	<E extends T> E save(E entity);

	List<T> findAll();

	long count();
}
