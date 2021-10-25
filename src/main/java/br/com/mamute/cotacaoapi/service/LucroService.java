package br.com.mamute.cotacaoapi.service;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Lucro;
import br.com.mamute.cotacaoapi.repository.LucroRepository;

@Service
public class LucroService {

	@Autowired
	private LucroRepository lucroRepository;
	
	public ModelAndView form(Lucro lucro) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/lucro/form-registrar-lucro");
		return mvForm.addObject("lucro", lucro);
    }
	
	public ModelAndView salvar(@Valid Lucro lucro, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()) 
			form(lucro);	
		
		if(lucro.getPorcentagem() == 0 || lucro.getPorcentagem() < 0) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos, negativo ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");
		}						
		try {			
			for (Lucro l : lucroRepository.findAll()) {
				if(lucro.getPorcentagem() == l.getPorcentagem() && l.getId() != lucro.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa porcentagem já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");
				}else 
					if (l.getId() == lucro.getId()) {
						lucroRepository.saveAndFlush(lucro);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Margem de lucro alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");
				}		
			}	
			
			lucroRepository.saveAndFlush(lucro);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Margem de lucro salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");			
		
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Lucro> lucros  = lucroRepository.findAll();
		try {
			if(lucros.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/lucro/lista-lucro");
			return mvLista.addObject("lucros", lucros);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Margem de lucro não pode ser deletada, ela está vinculada a um produto!");
			return new ModelAndView("redirect:/dashboard-admin/lucro/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/lucro/form-registrar-lucro");
		Optional<Lucro> lucro = lucroRepository.findById(id);
		try {	
			if(lucro == null || lucro.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/lucro/listar");
			}			
			
			return mvForm.addObject("lucro", lucro);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/lucro/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<Lucro> lucro = lucroRepository.findById(id);
		try {
			if(lucro == null || lucro.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/lucro/listar");
			}
			
			lucroRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Margem de lucro deletada!");
			return new ModelAndView("redirect:/dashboard-admin/lucro/listar");
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Não pode ser deletada vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/lucro/listar");
		}		
	}

}
