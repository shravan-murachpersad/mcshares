package mu.mcb.mcshares;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mu.mcb.mcshares.models.Token;
import mu.mcb.mcshares.utils.SecurityUtils;

@RestController
@RequestMapping("/public")
public class PublicController {

	@PostMapping("/token")
    public Token getToken(@RequestParam("username") String username, @RequestParam("password") String password) {
		SecurityUtils securityUtils = new SecurityUtils();
		Token token = new Token();
		token.setAccessToken(securityUtils.getJWTToken(username));
		
        return token;
    }
	
    
    
}
