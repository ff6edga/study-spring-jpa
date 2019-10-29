package study.spring.datacommon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CommentRepositoryTest {

	@Autowired
	CommentRepository commentRepository;

	@Test
	public void crud() {
		createComment(100, "spring data jpa");
		createComment(55, "hibernate spring");

		PageRequest pageRequest =
				PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));
		// IgnoreCase 키워드는 대상과 키워드 모두 대문자로 변경(upper())하여 조회한다.
//		Page<Comment> commentsPage =
//				commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
//
//
//		assertThat(commentsPage.getNumberOfElements()).isEqualTo(2);
//		assertThat(commentsPage).first().hasFieldOrPropertyWithValue("likeCount", 100);

		try(Stream<Comment> commentStream = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest)) {
			Comment firstComment = commentStream.findFirst().get();
			assertThat(firstComment.getLikeCount()).isEqualTo(100);
		}
	}

	private void createComment(int likeCount, String commentMsg) {
		Comment comment = new Comment();
		comment.setComment(commentMsg);
		comment.setLikeCount(likeCount);
		commentRepository.save(comment);
	}
}