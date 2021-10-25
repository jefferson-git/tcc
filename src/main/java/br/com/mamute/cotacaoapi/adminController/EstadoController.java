package br.com.mamute.cotacaoapi.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Estado;
import br.com.mamute.cotacaoapi.repository.EstadoRepository;

@Controller
@RequestMapping("/dashboard-admin/estado")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	private ModelAndView mvform = new ModelAndView("dashboard-admin/estado/form-registrar-estado");
	private ModelAndView mvLista = new ModelAndView("dashboard-admin/estado/lista-estado");		

	@GetMapping
	ModelAndView form(Estado estado) {
		return mvform.addObject("estado", estado);				
	}
	
	@PostMapping
	ModelAndView salvar(@Validated Estado estado, BindingResult result, RedirectAttributes attributes) {
		try {
			if(result.hasErrors())
				return form(estado);
			for (Estado estadoVerifica : estadoRepository.findAll()) {
				if((estadoVerifica.getNome().equals(estado.getNome()) && estadoVerifica.getSigla().equals(estado.getSigla())) && estadoVerifica.getId() != estado.getId()) {
					ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Erro, estado ou sigla já estão cadastrados.");
					return mv;
				}else {
					if(estadoVerifica.equals(estado)) {
						estadoRepository.saveAndFlush(estado);
						ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Estado atualizado com sucesso.");
						return mv;
					}					
				}if(estadoVerifica.getNome().equals(estado.getNome()) || estadoVerifica.getSigla().equals(estado.getSigla()) && estadoVerifica.getId() != estado.getId()) {
					ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Erro, estado ou sigla já estão cadastrados.");
					return mv;
				}
			}						
			estadoRepository.saveAndFlush(estado);
			ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Estado cadastrado com sucesso.");
			return mv;
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao cadastrar estado.");
			return mv;
		}		
	}
	
	@GetMapping("/")
	ModelAndView listar(RedirectAttributes attributes) {
		if(estadoRepository.findAll().isEmpty()) {		
			ModelAndView mvLista = new ModelAndView("redirect:/dashboard-admin/estado");
			attributes.addFlashAttribute("icone", "visibility_off");
			attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
			return mvLista;
		}
		return mvLista.addObject("estados", estadoRepository.findAll()); 
	}
	
	@GetMapping("/{id}")
	ModelAndView editar(@PathVariable(name = "id") Integer id, RedirectAttributes attributes) {		
		try {
			for (Estado estadoVerifica : estadoRepository.findAll()) 
				if(estadoVerifica.getId() == id) 
					return mvform.addObject("estado", estadoRepository.findById(id));					
			
			ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro, identificador do Estado inexistente");
			return mv;
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/estado");
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro, inesperado na url.");
			return mv;
		}		
	}
	
	@GetMapping("/deletar/{id}")
	String deletar(@PathVariable(name = "id") Integer id, RedirectAttributes attributes){
		try {
			estadoRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "cancel");
			attributes.addFlashAttribute("menssagem", "O Estado foi deletado.");
			return "redirect:/dashboard-admin/estado/";
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "pan_tool");
			attributes.addFlashAttribute("menssagem", "Voçê não pode realizar essa operação.");
			return "redirect:/dashboard-admin/estado/";			
		}
	}
}
