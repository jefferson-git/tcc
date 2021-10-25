package br.com.mamute.cotacaoapi.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Departamento;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	private static String caminhoDaImagem = "/cotacao_api/departamento/";

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public ModelAndView form(Departamento departamento, MultipartFile arquivo) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/departamento/form-registrar-departamento");
		mvForm.addObject("departamentos", departamentoRepository.findAll());
		mvForm.addObject("arquivo", arquivo);
		return mvForm;
    }
	
	public ModelAndView salvar(Departamento departamento, MultipartFile file, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()) 
			form(departamento, file);	
		
		if(departamento.getNome() == null || departamento.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campo nome não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");
		}						
		try {	
						
			if(!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path caminho = Paths.get(caminhoDaImagem+file.getOriginalFilename());
				Files.write(caminho, bytes);
				departamento.setImagem(file.getOriginalFilename());
			}
			
			for (Departamento d : departamentoRepository.findAll()) {
				if(d.getNome().equalsIgnoreCase((departamento.getNome())) && d.getId() != departamento.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa marca já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");
				}else 
					if (d.getId() == departamento.getId()) {
						departamentoRepository.saveAndFlush(departamento);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Departamento alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");
				}		
			}	
			
			departamentoRepository.saveAndFlush(departamento);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Departamento salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");				
				
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Departamento> departamentos  = departamentoRepository.findAll();
		try {
			if(departamentos.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/departamento/lista-departamento");
			return mvLista.addObject("departamentos", departamentos);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Departamento não pode ser deletada, ele está vinculada a um item!");
			return new ModelAndView("redirect:/dashboard-admin/departamento/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/departamento/form-registrar-departamento");
		Optional<Departamento> departamento = departamentoRepository.findById(id);
		try {	
			if(departamento == null || departamento.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/departamento/listar");
			}
			
			return mvForm.addObject("departamento", departamento);
					
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/departamento/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<Departamento> departamento  = departamentoRepository.findById(id);
		try {
			if(departamento == null || departamento.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/departamento/listar");
			}				
			departamentoRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Departamento deletada!");
			return new ModelAndView("redirect:/dashboard-admin/departamento/listar");
						
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Impossivel, departamento vinculada a categoria!");
			return new ModelAndView("redirect:/dashboard-admin/departamento/listar");
		}		
	}
	
	public byte[] visualizar(String imagem) throws IOException {		
		File imagemMarca = new File(caminhoDaImagem+imagem);
		return Files.readAllBytes(imagemMarca.toPath());
	}
}
