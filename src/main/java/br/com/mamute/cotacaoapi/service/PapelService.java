package br.com.mamute.cotacaoapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Papel;
import br.com.mamute.cotacaoapi.repository.PapelRepository;

@Service
public class PapelService {

	@Autowired private PapelRepository papelRepository;
	@Autowired private UsuarioService usuarioService;	
	ModelAndView mvForm = new ModelAndView("/dashboard-admin/papel/form-registrar-papel");
	ModelAndView mvLista = new ModelAndView("/dashboard-admin/papel/lista-papel");
	
	public ModelAndView form(Papel papel) {
		mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvForm.addObject("papel",papel);
	}
	
	public ModelAndView salvar(@Valid Papel papel, BindingResult result, RedirectAttributes attributes) {		
		if(result.hasErrors())
			return form(papel);	
		try {
			for (Papel papelVerifica : papelRepository.findAll()) {
				if(papelVerifica.getNome().equals(papel.getNome()) && papelVerifica.getId() != papel.getId()) {
					ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/papel/registrar");
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Esse Papel já Esiste no Banco de Dados.");
					return mvForm;
				}else {
					if(papelVerifica.getId() == papel.getId()) {
						papelRepository.saveAndFlush(papel);
						ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/papel/registrar");
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Papel atualizado com sucesso.");
						return mvForm;
					}else {
						if(papel.getNome() == null || papel.getNome().isBlank()) {
							ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/papel/registrar");
							attributes.addFlashAttribute("icone", "thumb_up");
							attributes.addFlashAttribute("menssagem", "O campo não pode ser nulo ou ficar em branco.");
							return mvForm;
						}
					}
				}				
			}
			
			papelRepository.saveAndFlush(papel);
			ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/papel/registrar");
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Papel salvo com sucesso.");
			return mvForm;
		} catch (Exception e) {
			ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/papel/registrar");
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao inserir novo papel.");
			return mvForm;
		}				
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {		
		
		List<Papel> lista = papelRepository.findAll();
		if(lista.size() == 0) {			
			ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/papel/registrar");
			attributes.addFlashAttribute("icone", "visibility_off");
			attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
			return mvForm;			
		}
		mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvLista.addObject("papeis", lista);
	}
	
	public ModelAndView editar(Integer id, RedirectAttributes attributes) {
		
		Optional<Papel> papel = papelRepository.findById(id);
		if(papel == null || papel.isEmpty()) {		
			ModelAndView lista = new ModelAndView("redirect:/dashboard-admin/papel/listar");
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "id inesistente!");
			return lista;	
		}
		mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvForm.addObject("papel", papel);
	}
	
	public ModelAndView deletar(Integer id, RedirectAttributes attributes) {		
		try {
			if(papelRepository.findById(id).isPresent()) {
				papelRepository.deleteById(id);
				attributes.addFlashAttribute("icone", "thumb_up");
				attributes.addFlashAttribute("menssagem", "Papel deletada!");
				return new ModelAndView("redirect:/dashboard-admin/papel/listar");
			}				
						
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
			return new ModelAndView("redirect:/dashboard-admin/papel/listar");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Operação não permitida no banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/papel/listar");
		}		
	}
}
