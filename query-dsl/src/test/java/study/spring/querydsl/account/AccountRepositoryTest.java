package study.spring.querydsl.account;

import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void crud() {
		//테스트 코드가 없어도, 빈 생성 및 등록, 리포지토리 생성
		//테스트 DB 및 테이블 생성 여부 까지 확인이 가능하다.
		QAccount account = QAccount.account;
		Predicate predicate = account
				.firstName.containsIgnoreCase("younsoo")
				.and(account.lastName.startsWith("kim"));

		Optional<Account> one = accountRepository.findOne(predicate);
		assertThat(one).isEmpty();
	}
}