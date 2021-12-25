package br.com.mamute.cotacaoapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.mamute.cotacaoapi.model.Colaborador;
import br.com.mamute.cotacaoapi.model.Permissao;
import br.com.mamute.cotacaoapi.model.Usuario;
import br.com.mamute.cotacaoapi.repository.ColaboradorRepository;
import br.com.mamute.cotacaoapi.repository.PapelRepository;
import br.com.mamute.cotacaoapi.repository.PermissaoRepository;
import br.com.mamute.cotacaoapi.repository.UsuarioRepository;

@Service
public class PermissaoService {
	
	@Autowired private PermissaoRepository permissaoRepository;	
	@Autowired private PapelRepository papelRepository;	
	@Autowired private ColaboradorRepository colaboradorRepository;	
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private UsuarioService usuarioService;	

	
	public ModelAndView form(Long id, RedirectAttributes attributes) {	
		ModelAndView mvForm = new ModelAndView("dashboard-admin/permissao/form-registrar-permissao");
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
		try {
			if (usuario.isEmpty() || colaborador.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Colaborador inesistente.");	
				return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
			}
			
			
			mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			mvForm.addObject("usuario", usuario);
			mvForm.addObject("colaborador", colaborador);
			mvForm.addObject("papeis", papelRepository.findAll());
			mvForm.addObject("permissao", new  Permissao());
			return mvForm;
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Colaborador inesistente.");	
			return new ModelAndView("redirect:/dashboard-admin/permissao/registrar");			
		}		
	}
	
	public ModelAndView salvar(@Valid Long id, Permissao permissao, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) 
			return form(id, attributes);		
		try {		
			permissao.setUsuario(usuarioRepository.findById(id).get());
			permissaoRepository.save(permissao);		
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Permissão concedida!");
			return new ModelAndView("redirect:/dashboard-admin/permissao/listar");			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
		}		
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		ModelAndView mvLista = new ModelAndView("dashboard-admin/permissao/lista-permissao");
		try {
			List<Permissao> permissaoes = permissaoRepository.findAll();
			if(permissaoes.size() == 0) {
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/colaborador/listar");
			}
			mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
			mvLista.addObject("permissoes", permissaoes);		 
			return mvLista;
			 
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro inesperado ao conectar com o banco de dados!");	
			return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
		}
	} 
	
	public ModelAndView editar(Integer id, RedirectAttributes attributes) {	
		
		try {
			Optional<Permissao> permissaoOp = permissaoRepository.findById(id);
			Permissao permissao = permissaoOp.get();
			if(permissao == null || permissaoOp.isEmpty()) {				
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Permissão inesistente!");	
				return new ModelAndView("redirect:/dashboard-admin/permissao/listar");					
			}
			
			ModelAndView mvForm = new ModelAndView("dashboard-admin/permissao/form-registrar-permissao");
			mvForm.addObject("colaborador", colaboradorRepository.findById(permissao.getUsuario().getId()));
			mvForm.addObject("papeis", papelRepository.findAll());
			mvForm.addObject("permissao", permissao);
			return mvForm;		
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro inesperado ao conectar com o banco de dados!");	
			return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
		}	
	} 
	
	public ModelAndView deletar(Integer id, RedirectAttributes attributes) {	
		try {
			for (Permissao p : permissaoRepository.findAll()) {
				if(p.getId() == id) {
					permissaoRepository.deleteById(id);
					attributes.addFlashAttribute("icone", "thumb_up");
					attributes.addFlashAttribute("menssagem", "Permissão deletada!");
					return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
				}				
			}	
			
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
			return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro inesperado ao conectar com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/permissao/listar");
		}		
	}
}
	

