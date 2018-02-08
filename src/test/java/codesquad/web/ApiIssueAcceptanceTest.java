package codesquad.web;

import codesquad.domain.Issue;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.IssueDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;
import support.test.BasicAuthAcceptanceTest;

import static org.junit.Assert.assertNotNull;

public class ApiIssueAcceptanceTest extends AcceptanceTest {
	private static final Logger log = LoggerFactory.getLogger(ApiIssueAcceptanceTest.class);

	@Autowired
	UserRepository userRepository;

	@Test
	public void add_assignee() {
		User user = userRepository.findOne(1L);
		log.debug("headhead: {}", user.toString());
		String location = createResource("/api/issues/1/setAssignee/" + user.getId(), user._toUserDto());

		Issue issue = getResource(location, Issue.class);
		assertNotNull(issue.getAssignee());
	}
}
