package br.com.mamute.cotacaoapi.service;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Categoria;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public ModelAndView form(Categoria categoria) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/categoria/form-registrar-categoria");
		mvForm.addObject("categoria", categoria);
		mvForm.addObject("departamentos", departamentoRepository.findAll());
		return mvForm;
    }
	
	public ModelAndView salvar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()) 
			form(categoria);	
		
		if(categoria.getNome() == null || categoria.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");
		}						
		try {			
			for (Categoria c : categoriaRepository.findAll()) {
				if(c.getNome().equalsIgnoreCase((categoria.getNome())) && c.getId() != categoria.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa categoria já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");
				}else 
					if (c.getId() == categoria.getId()) {
						categoriaRepository.saveAndFlush(categoria);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Categoria alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");
				}		
			}		
			
			categoriaRepository.saveAndFlush(categoria);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Categoria salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");			
		
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			
		}
		return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Categoria> categorias = categoriaRepository.findAll();
		try {
			if(categorias.isEmpty()) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/categoria/lista-categoria");
			return mvLista.addObject("categorias", categorias);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Categoria não pode ser deletada, ela está vinculada a um produto!");
			return new ModelAndView("redirect:/dashboard-admin/categoria/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		try {	
			if(categoria == null || categoria.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/categoria/listar");
			}	
			
			ModelAndView mvForm = new ModelAndView("dashboard-admin/categoria/form-registrar-categoria");
			return mvForm.addObject("categoria", categoria);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/categoria/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {	
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
	
		try {
			if(categoria == null || categoria.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/categoria/listar");
			}				
			
			categoriaRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Categoria deletada!");
			return new ModelAndView("redirect:/dashboard-admin/categoria/listar");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Categoria vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/categoria/listar");
		}		
	}

}
