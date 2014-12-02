import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import facebook4j.FacebookException;
import syncdata.FBDBController;

@Controller
@RequestMapping("/")
public class MvCController {

	@RequestMapping(value="/pik-a-place",method=RequestMethod.GET)
    public String index() {
    	System.out.println("Reached mainpage");
        return "Login";
    }
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
    public String indexJsp() {
    	System.out.println("Reached here signup");
        return "index";
    }
	
	@RequestMapping(value="/success",method=RequestMethod.GET)
    public String successJsp() {
		
    	System.out.println("Reached here success");
        return "Success";
    }
	
	@RequestMapping(value="/Error",method=RequestMethod.GET)
	public String Errorjsp(){
		System.out.println("Reached Error");
		return "Error";
	}
}