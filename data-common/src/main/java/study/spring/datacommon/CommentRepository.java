package study.spring.datacommon;

import org.springframework.data.domain.Pageable;

import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comment, Long> {
// Case 1 - 키워드 검색 & 프로퍼티 비교 검색
//	List<Comment> findByCommentContainsIgnoreCaseOrderAndGreaterThan
//			(String keyword, Integer likeCount);

// Case 2 - 프로퍼티 비교 정렬
//	List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc
//			(String keyword);

// Case 3 - 페이징
//	Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

	// Case 4 - 스트림
	Stream<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);
}