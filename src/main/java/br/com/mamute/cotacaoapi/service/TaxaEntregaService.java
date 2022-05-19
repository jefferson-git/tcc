package br.com.mamute.cotacaoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.TaxaEntrega;
import br.com.mamute.cotacaoapi.repository.TaxaEntregaRepository;

@Service
public class TaxaEntregaService {
	
	@Autowired private TaxaEntregaRepository taxaEntregaRepository;	
	@Autowired private UsuarioService usuarioService;
	
	public ModelAndView form(TaxaEntrega taxaEntrega) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/taxa-entrega/form-registrar-taxa-entrega");
    	mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		mvForm.addObject("taxa", taxaEntrega);
		return mvForm;
    }
	
	public ModelAndView salvar(TaxaEntrega taxaEntrega,	BindingResult result, RedirectAttributes attributes){		
		if(result.hasErrors()) 
			form(taxaEntrega);			
		if(taxaEntrega.getNome() == null || taxaEntrega.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campo nome não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");
		}						
		try {							
						
			for (TaxaEntrega taxa : taxaEntregaRepository.findAll()) {
				if(taxa.getNome().equalsIgnoreCase((taxaEntrega.getNome())) && taxa.getId() != taxaEntrega.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa taxa de Entrega já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");
				}else 
					if (taxa.getId() == taxaEntrega.getId()) {
						taxaEntregaRepository.saveAndFlush(taxaEntrega);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Taxa de entrega alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");
				}		
			}	
			
			taxaEntregaRepository.saveAndFlush(taxaEntrega);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Taxa de entrega salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");				
				
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<TaxaEntrega> taxas = taxaEntregaRepository.findAll();
		try {
			if(taxas.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/taxa-entrega/lista-taxa-entrega");
	    	mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvLista.addObject("taxas", taxas);
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados");
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/taxa-entrega/form-registrar-taxa-entrega");
		Optional<TaxaEntrega> taxa = taxaEntregaRepository.findById(id);
		try {	
			if(taxa == null || taxa.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/listar");
			}
	    	mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvForm.addObject("taxa", taxa);
					
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<TaxaEntrega> taxa = taxaEntregaRepository.findById(id);
		try {
			if(taxa == null ||taxa.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/listar");
			}				
			taxaEntregaRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Taxa de entrega deletada!");
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/listar");
						
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Não pode ser deletada, taxa de entrega vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/taxa-entrega/listar");
		}		
	}
}
