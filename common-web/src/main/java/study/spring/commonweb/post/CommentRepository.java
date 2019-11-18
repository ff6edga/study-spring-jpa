package study.spring.commonweb.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>, QueryByExampleExecutor<Comment> {

	// == @EntityGraph(attributePaths = "post")
	@EntityGraph(value = "Comment.post")
	Optional<Comment> getById(Long id);

	//List<CommentSummary> findByPost_Id(Long id);
	@Transactional(readOnly = true)
	<T> List<T> findByPost_Id(Long id, Class<T> type);
}
