package study.spring.commonweb.post;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {
	String getComment();

	int getUp();

	int getDown();

	// @Value를 쓰게 되면 Open Projection으로 전환된다.
	//@Value("#{target.up + ' ' + target.down}")
	//String getVotes();

	// default 키워드를 사용하여 커스텀 메소드를
	// Closed Projection을 유지하며 사용할 수 있다.
	default String getVotes() {
		return getUp() + " " + getDown();
	}
}
