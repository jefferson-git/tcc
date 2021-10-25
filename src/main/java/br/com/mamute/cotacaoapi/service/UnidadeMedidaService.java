package br.com.mamute.cotacaoapi.service;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.UnidadeDeMedida;
import br.com.mamute.cotacaoapi.repository.UnidadeDeMedidaRepository;

@Service
public class UnidadeMedidaService {

	@Autowired
	private UnidadeDeMedidaRepository unidadeDeMedidaRepository;
	
	public ModelAndView form(UnidadeDeMedida unidadeDeMedida) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/unidade-medida/form-registrar-unidade");
		return mvForm.addObject("unidadeDeMedida", unidadeDeMedida);
    }
	
	public ModelAndView salvar(@Valid UnidadeDeMedida unidadeDeMedida, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()) 
			form(unidadeDeMedida);	
		
		if(unidadeDeMedida.getNome() == null || unidadeDeMedida.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");
		}						
		try {			
			for (UnidadeDeMedida u : unidadeDeMedidaRepository.findAll()) {
				if(u.getNome().equalsIgnoreCase((unidadeDeMedida.getNome())) && u.getId() != unidadeDeMedida.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa unidade já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");
				}else 
					if (u.getId() == unidadeDeMedida.getId()) {
						unidadeDeMedidaRepository.saveAndFlush(unidadeDeMedida);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Unidade de medida alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");
				}		
			}	
			
			unidadeDeMedidaRepository.saveAndFlush(unidadeDeMedida);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Unidade de medida salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");			
		
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<UnidadeDeMedida> unidadeDeMedidas  = unidadeDeMedidaRepository.findAll();
		try {
			if(unidadeDeMedidas.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/unidade-medida/lista-unidade");
			return mvLista.addObject("unidadeDeMedidas", unidadeDeMedidas);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Unidade de medida não pode ser deletada, ela está vinculada a um produto!");
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {			
		
		Optional<UnidadeDeMedida> unidadeDeMedida = unidadeDeMedidaRepository.findById(id);
		try {	
			if(unidadeDeMedida == null || unidadeDeMedida.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/unidade-medida/listar");
			}
			
			ModelAndView mvForm = new ModelAndView("dashboard-admin/unidade-medida/form-registrar-unidade");
			return mvForm.addObject("unidadeDeMedida", unidadeDeMedida);			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		try {
			if(unidadeDeMedidaRepository.findById(id).isPresent()) {
				unidadeDeMedidaRepository.deleteById(id);
				attributes.addFlashAttribute("icone", "thumb_up");
				attributes.addFlashAttribute("menssagem", "Unidade deletada!");
				return new ModelAndView("redirect:/dashboard-admin/unidade-medida/listar");
			}				
			
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/listar");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Unidade vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/unidade-medida/listar");
		}		
	}

}
