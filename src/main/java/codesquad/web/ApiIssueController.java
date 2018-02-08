package codesquad.web;

import codesquad.domain.Issue;
import codesquad.dto.IssueDto;
import codesquad.dto.UserDto;
import codesquad.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/issues")
public class ApiIssueController {
	private static final Logger log = LoggerFactory.getLogger(ApiIssueController.class);

	@Resource(name = "issueService")
	private IssueService issueService;

	@PostMapping("/{issueId}/setAssignee/{userId}")
	public Issue setAssignee(@PathVariable long issueId, @PathVariable long userId, @RequestBody UserDto userDto) {
		return issueService.setAssignee(issueId, userId);
	}
}
