package br.com.mamute.cotacaoapi.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import br.com.mamute.cotacaoapi.model.Usuario;


@Controller
@RequestMapping("/dashboard-admin")
public class LoginController {
	

	@GetMapping("/user-login")
	String userLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return"dashboard-admin/login/user-login";
	}
	
	@PostMapping("/user-login")
	String userLogin() {		
        return "dashboard-admin/login/user-login";
    }

	@GetMapping("/logout")
	String userLogout() {
		return"dashboard-admin/login/user-login";
	}
	
	
	@GetMapping("/user-register")
	public ModelAndView form(Usuario usuario) {	
		ModelAndView mvForm = new ModelAndView("dashboard-admin/login/user-register");
		return mvForm.addObject("usuario", usuario);
	}
	
	@GetMapping("/user-forgot-password")
	String userForgot() {
		return"dashboard-admin/login/user-forgot-password";
	}
	
	@GetMapping("/user-lock-screen")
	String userLock() {
		return"dashboard-admin/login/user-lock-screen";
	}
}
