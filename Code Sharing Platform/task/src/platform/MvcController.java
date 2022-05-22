package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class MvcController {

    @Autowired
    CodeService codeService;

    public MvcController() {
    }

    @GetMapping("/code/new")
    public ModelAndView createCodeHtml(Code theCode) {
        return new ModelAndView("/create.html", HttpStatus.OK);
    }

    @GetMapping("/code/{n}")
    public String code(@PathVariable("n") String n, Model model) {
        Code code = codeService.findByUuid(n);
        if (code == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            model.addAttribute("code_snippet", code);
            model.addAttribute(HttpStatus.OK);
            if (code.getExpiryTime() != null) {
                model.addAttribute("duration_remaining",
                        String.valueOf(Duration.between(LocalDateTime.now(), code.getExpiryTime()).toSeconds()));
            }
            return "code";
        }
    }

    @GetMapping("/code/latest")
    public String code(Model model) {
        model.addAttribute("code_snippets", codeService.findLatest(10));
        return "latest";
    }
}
