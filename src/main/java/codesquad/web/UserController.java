package codesquad.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import codesquad.UnAuthenticationException;
import codesquad.security.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codesquad.domain.User;
import codesquad.dto.UserDto;
import codesquad.security.LoginUser;
import codesquad.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @PostMapping("")
    public String create(UserDto userDto) {
        userService.add(userDto);
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
        log.debug("LoginUser : {}", loginUser);
        model.addAttribute("user", userService.findById(loginUser, id));
        return "/user/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@LoginUser User loginUser, @PathVariable long id, UserDto target) {
        userService.update(loginUser, id, target);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, HttpSession session) {
        try {
            User user = userService.login(userDto.getUserId(), userDto.getPassword());
            session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
            return "redirect:/";
        } catch (UnAuthenticationException e) {
            log.info("아이디 또는 비밀번호가 다릅니다.");
            e.printStackTrace();
            return "redirect:/users/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }

}
