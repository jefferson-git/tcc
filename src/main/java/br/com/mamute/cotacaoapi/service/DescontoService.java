package br.com.mamute.cotacaoapi.service;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Desconto;
import br.com.mamute.cotacaoapi.repository.DescontoRepository;

@Service
public class DescontoService {

	@Autowired private DescontoRepository descontoRepository;
	@Autowired private UsuarioService usuarioService;
	
	public ModelAndView form(Desconto desconto) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/desconto/form-registrar-desconto");
    	mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvForm.addObject("desconto", desconto);
    }
	
	public ModelAndView salvar(@Valid Desconto desconto, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()) 
			form(desconto);	
		
		if(desconto.getPorcentage() < 0) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos, negativo ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");
		}						
		try {			
			for (Desconto d : descontoRepository.findAll()) {
				if(desconto.getPorcentage() == d.getPorcentage() && d.getId() != desconto.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa porcentagem já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");
				}else 
					if (d.getId() == desconto.getId()) {
						descontoRepository.saveAndFlush(desconto);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Desconto alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");
				}		
			}	
			
			descontoRepository.saveAndFlush(desconto);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Desconto salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");			
		
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Desconto> descontos  = descontoRepository.findAll();
		try {
			if(descontos.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/desconto/lista-desconto");
			mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvLista.addObject("descontos", descontos);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Desconto não pode ser deletada, ela está vinculada a um produto!");
			return new ModelAndView("redirect:/dashboard-admin/desconto/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/desconto/form-registrar-desconto");
		Optional<Desconto> desconto = descontoRepository.findById(id);
		try {	
			if(desconto == null || desconto.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/desconto/listar");
			}
			
			mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvForm.addObject("desconto", desconto);
			
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/desconto/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<Desconto> desconto = descontoRepository.findById(id);
		try {
			if(desconto == null || desconto.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/desconto/listar");				
			}				
			
			descontoRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Desconto deletada!");
			return new ModelAndView("redirect:/dashboard-admin/desconto/listar");
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Não pode ser deletada vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/desconto/listar");
		}		
	}

}
