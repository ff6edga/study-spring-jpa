package study.spring.commonweb.post;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommentSpecs {

	public static Specification<Comment> isBest() {
		return (Specification<Comment>)
				(root, query, builder) -> builder.isTrue(root.get(Comment_.best));
	}

	public static Specification<Comment> isGood() {
		return new Specification<Comment> () {
			@Override
			public Predicate toPredicate(Root<Comment> root,
					CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				return builder.greaterThanOrEqualTo(root.get(Comment_.up), 10);
			}
		};
	}
}
