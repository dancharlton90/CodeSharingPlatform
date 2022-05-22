package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AppController {

    @Autowired
    CodeService codeService;

    public AppController() {
    }

    @GetMapping("/api/code/{n}")
    public ResponseEntity<?> getCode(@PathVariable String n) {
        Code code = codeService.findByUuid(n);
        if(code != null) {
            System.out.println("DEBUG: api/code/{n}: " + code.toString());
            return new ResponseEntity<>(code, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> createCodeJson(@RequestBody Code codeSnippet) {
        codeService.save(codeSnippet);
        System.out.println("DEBUG: api/code/new: " + codeSnippet.toString());
        return new ResponseEntity<>(new HashMap<>(Map.of(
                "id", String.valueOf(codeSnippet.getUuid()))), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatest() {
        return new ResponseEntity<>(codeService.findLatest(10), HttpStatus.OK);
    }
}
