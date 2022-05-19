package br.com.mamute.cotacaoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.FormaPagamento;
import br.com.mamute.cotacaoapi.repository.FormaPagamentoRepository;


@Service
public class FormaPagamentoService {
	
	@Autowired private FormaPagamentoRepository formaPagamentoRepository;	
	@Autowired private UsuarioService usuarioService;
	
	public ModelAndView form(FormaPagamento formaPagamento) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/forma-pagamento/form-registrar-forma-pagamento");
    	mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		mvForm.addObject("formaPagamento", formaPagamento);
		return mvForm;

    }
	
	public ModelAndView salvar(FormaPagamento formaPagamento, BindingResult result, RedirectAttributes attributes){		
		if(result.hasErrors()) 
			form(formaPagamento);	
		try {
		if(formaPagamento.getNome() == null || formaPagamento.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campo nome não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");
		}						
			
			for (FormaPagamento m : formaPagamentoRepository.findAll()) {
				if(m.getNome().equalsIgnoreCase((formaPagamento.getNome())) && m.getId() != formaPagamento.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa forma de Pagamento já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");
				}else 
					if (m.getId() == formaPagamento.getId()) {
						formaPagamentoRepository.saveAndFlush(formaPagamento);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Forma de Pagamento alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");
				}		
			}	
			
			formaPagamentoRepository.saveAndFlush(formaPagamento);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Forma de Pagamento salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");				
				
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
		try {
			if(formaPagamentos.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/forma-pagamento/lista-forma-pagamento");
	    	mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvLista.addObject("formaPagamentos", formaPagamentos);
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados");
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/forma-pagamento/form-registrar-forma-pagamento");
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
		try {	
			if(formaPagamento == null || formaPagamento.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/listar");
			}
	    	mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvForm.addObject("formaPagamento", formaPagamento);
					
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
		try {
			if(formaPagamento == null || formaPagamento.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/listar");
			}				
			formaPagamentoRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Forma de Pagamento deletada!");
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/listar");
						
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Forma de Pagamento vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/forma-pagamento/listar");
		}		
	}
}
