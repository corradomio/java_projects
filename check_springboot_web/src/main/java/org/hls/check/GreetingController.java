package org.hls.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController()
public class GreetingController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @GetMapping(value = "greeting", produces = "application/json")
    public Map greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return MapUtils.asMap("message", "greeting" );
    }

    @GetMapping(value = "error", produces = "application/json")
    public Object raiseError() throws Throwable {
        try {
            throw new RuntimeException("Exception");
        }
        catch (Throwable ex) {
            throw new Throwable(ex);
        }
    }

    @GetMapping(value = "exception", produces = "application/json")
    public ResponseEntity raiseException() {
        try {
            try {
                throw new RuntimeException("Exception");
            }
            catch (Throwable ex) {
                throw new Throwable(ex);
            }
        }
        catch (Throwable ex) {
            return InternalServerError.response(request, ex);
            // throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
