package br.com.mamute.cotacaoapi.adminController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Cidade;
import br.com.mamute.cotacaoapi.repository.CidadeRepository;
import br.com.mamute.cotacaoapi.repository.EstadoRepository;

@Controller
@RequestMapping("/dashboard-admin/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	private ModelAndView mvForm = new ModelAndView("dashboard-admin/cidade/form-registrar-cidade");
	private ModelAndView mvLista = new ModelAndView("dashboard-admin/cidade/lista-cidade");
	
	@GetMapping()
	ModelAndView form(Cidade cidade) {
		mvForm.addObject("estados", estadoRepository.findAll());
		return mvForm.addObject("cidade", cidade);
	}
	
	@PostMapping()
	ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {	
		try {
			if(result.hasErrors())
				return form(cidade);
			for (Cidade cidadeConfere : cidadeRepository.findAll()) {
				if(cidadeConfere.getNome().equalsIgnoreCase(cidade.getNome()) && cidadeConfere.getEstado().equals(cidade.getEstado()) && cidadeConfere.getId() != cidade.getId()) {
					ModelAndView mv = new ModelAndView("redirect:/dashboard-admin/cidade");
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Erro, esta cidade já foi cadastrada para esse estado.");
					return mv;
				}else {
					if(cidadeConfere.getId().equals(cidade.getId())) {
					ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/cidade");
					attributes.addFlashAttribute("icone", "thumb_up");
					attributes.addFlashAttribute("menssagem", "Cidade atualizada com sucesso.");
					return mvForm;
					}
				}
			}	
			cidadeRepository.saveAndFlush(cidade);		
			ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/cidade");
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Cidade cadastrado com sucesso.");
			return mvForm;
		} catch (Exception e) {
			ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/cidade");
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro inesperado ao cadastrado cidade.");
			return mvForm;
		}
	}

	@GetMapping("/")
	ModelAndView listar(RedirectAttributes attributes) {	
		if(cidadeRepository.findAll().isEmpty()) {
			ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/cidade");
			attributes.addFlashAttribute("icone", "visibility_off");
			attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
			return mvForm;			
		}
		return mvLista.addObject("cidades", cidadeRepository.findAll());		 		 
	} 
	
	@GetMapping("/{id}")
	ModelAndView editar(@PathVariable(name = "id") Integer id) {
		mvForm.addObject("estados", estadoRepository.findAll());
		mvForm.addObject("cidade", cidadeRepository.findById(id));
		return mvForm;
	}
	
	@GetMapping("/deletar/{id}")
	String deletar(@PathVariable Integer id, RedirectAttributes attributes) {
		try {
			cidadeRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "cancel");
			attributes.addFlashAttribute("menssagem", "A Cidade foi deletada.");
			return "redirect:/dashboard-admin/cidade/";
		} catch (Exception e) {			
			attributes.addFlashAttribute("icone", "pan_tool");
			attributes.addFlashAttribute("menssagem", "Voçê não pode realizar essa operação.");
			return "redirect:/dashboard-admin/cidade/";
		}
	}
}