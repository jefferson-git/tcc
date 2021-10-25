package br.com.mamute.cotacaoapi.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard-user/usuario")
public class PerfilController {

	@GetMapping("/perfil")
	ModelAndView perfil() {
		ModelAndView mv = new ModelAndView("dashboard-user/usuario/perfil");
		return mv;
	}
}
