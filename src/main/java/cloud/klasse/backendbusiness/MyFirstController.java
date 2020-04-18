package cloud.klasse.backendbusiness;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstController {

    @RequestMapping ("/")
    public String index () {
        return "First Page: Klasse.cloud Business logic Backend :)";
    }
}
