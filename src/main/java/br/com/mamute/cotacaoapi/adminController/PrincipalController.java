package br.com.mamute.cotacaoapi.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Usuario;
import br.com.mamute.cotacaoapi.repository.PessoaRepository;
import br.com.mamute.cotacaoapi.repository.UsuarioRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Controller
@RequestMapping("/")
public class PrincipalController {	
	
	@Autowired UsuarioRepository usuarioRepository;
	@Autowired PessoaRepository pessoaRepository;
	@Autowired private UsuarioService usuarioService;

	
	@GetMapping()
	public ModelAndView principal(RedirectAttributes attributes) {
		ModelAndView mvPrincipal = new ModelAndView("dashboard-admin/analise/resultados");
		Usuario usuario = usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		attributes.addFlashAttribute("menssagem", "Ola! Seja bem vindo, "+ usuario.getUsername());			
		mvPrincipal.addObject("colaboradorLogado",usuarioService.usuarioLogado());
		return new ModelAndView("redirect:/dashboard-admin");
	}
	
	
	
	
	

}
