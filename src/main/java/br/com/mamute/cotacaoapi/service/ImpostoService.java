package br.com.mamute.cotacaoapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Imposto;
import br.com.mamute.cotacaoapi.repository.ImpostoRepository;

@Service
public class ImpostoService {

	@Autowired private ImpostoRepository impostoRepository;
	@Autowired private UsuarioService usuarioService;
	
	public ModelAndView form(Imposto imposto) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/imposto/form-registrar-imposto");
    	mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvForm.addObject("imposto", imposto);
    }
	
	public ModelAndView salvar(@Valid Imposto imposto, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()) 
			form(imposto);	
		
		if(imposto.getPorcentagem() <= 0) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos, negativos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");
		}						
		try {			
			for (Imposto i : impostoRepository.findAll()) {
				if(i.getPorcentagem().equals(imposto.getPorcentagem())) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa porcentagem já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");
				}else 
					if (i.getId() == imposto.getId()) {
						impostoRepository.saveAndFlush(imposto);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Porcentagem alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");
				}		
			}	
					
			impostoRepository.saveAndFlush(imposto);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Porcentagem salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");			
		
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Imposto> impostos = impostoRepository.findAll();
		try {
			if(impostos.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/imposto/lista-imposto");
			mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvLista.addObject("impostos", impostos);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/imposto/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/imposto/form-registrar-imposto");
		Optional<Imposto> imposto = impostoRepository.findById(id);
		try {	
			if(imposto == null || imposto.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/imposto/listar");
			}
			mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvForm.addObject("imposto", imposto);			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/imposto/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<Imposto> imposto = impostoRepository.findById(id);
		try {
			if(imposto == null || imposto.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/imposto/listar");
			}				
			
			impostoRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Porcentagem deletada!");
			return new ModelAndView("redirect:/dashboard-admin/imposto/listar");		
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Imposto vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/imposto/listar");
		}		
	}
}
