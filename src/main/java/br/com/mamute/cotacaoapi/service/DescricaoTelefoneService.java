package br.com.mamute.cotacaoapi.service;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.DescricaoTelefone;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;

@Service
public class DescricaoTelefoneService {

	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private UsuarioService usuarioService;
	
	public ModelAndView form(DescricaoTelefone descricao) {
		ModelAndView mvForm = new ModelAndView("dashboard-admin/descricao-telefone/form-registrar-descricao-telefone");
		mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvForm.addObject("descricao", descricao);
    }
	
	public ModelAndView salvar(@Valid DescricaoTelefone descricao, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()) 
			form(descricao);	
		
		if(descricao.getNome() == null || descricao.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");
		}						
		try {			
			for (DescricaoTelefone d : descricaoTelefoneRepository.findAll()) {
				if(d.getNome().equalsIgnoreCase((descricao.getNome())) && d.getId() != descricao.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa descriçao já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");
				}else 
					if (d.getId() == descricao.getId()) {
						descricaoTelefoneRepository.saveAndFlush(descricao);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Descrição alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");
				}		
			}						
			descricaoTelefoneRepository.saveAndFlush(descricao);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Descrição salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");			
		
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<DescricaoTelefone> descricoes = descricaoTelefoneRepository.findAll();
		try {
			if(descricoes.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/descricao-telefone/lista-descricao-telefone");
			mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvLista.addObject("descricoes", descricoes);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Descrição não pode ser deletada, ela está vinculada a um produto!");
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/registrar");
		}
	}
	
	public ModelAndView editar(Integer id, RedirectAttributes attributes) {			
		Optional<DescricaoTelefone> descricao = descricaoTelefoneRepository.findById(id);
		
		try {	
			if(descricao == null || descricao.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/listar");
			}
			
			ModelAndView mvForm = new ModelAndView("dashboard-admin/descricao-telefone/form-registrar-descricao-telefone");
			mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			return mvForm.addObject("descricao", descricao);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/listar");
		}
	}
	
public ModelAndView deletar(Integer id, RedirectAttributes attributes) {	
		
		Optional<DescricaoTelefone> descricao = descricaoTelefoneRepository.findById(id);
	
		try {
			if(descricao == null || descricao.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/listar");
			}				
			
			descricaoTelefoneRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Descricao deletada!");
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/listar");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Descricao vinculada a um ou mais Telefone!");
			return new ModelAndView("redirect:/dashboard-admin/descricao-telefone/listar");
		}		
	}

}
