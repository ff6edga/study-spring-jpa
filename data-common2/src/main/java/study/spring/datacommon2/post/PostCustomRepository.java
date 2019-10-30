package study.spring.datacommon2.post;

import java.util.List;

public interface PostCustomRepository<T> {

	List<Post> findMyPost();

	void delete(T entity);
}
