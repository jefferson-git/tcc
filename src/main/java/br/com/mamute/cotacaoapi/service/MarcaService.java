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

import br.com.mamute.cotacaoapi.model.Marca;
import br.com.mamute.cotacaoapi.repository.MarcaRepository;

@Service
public class MarcaService {
	
	private static String caminhoDaImagem = "/cotacao_api/marca/";

	@Autowired
	private MarcaRepository marcaRepository;
	
	public ModelAndView form(Marca marca, MultipartFile arquivo) {
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/marca/form-registrar-marca");
		mvForm.addObject("marca", marca);
		mvForm.addObject("arquivo", arquivo);
		return mvForm;

    }
	
	public ModelAndView salvar(Marca marca, MultipartFile file,	BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()) 
			form(marca, file);	
		
		if(marca.getNome() == null || marca.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campo nome não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
		}						
		try {	
						
			if(!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path caminho = Paths.get(caminhoDaImagem+file.getOriginalFilename());
				Files.write(caminho, bytes);
				marca.setImagem(file.getOriginalFilename());
			}
			
			for (Marca m : marcaRepository.findAll()) {
				if(m.getNome().equalsIgnoreCase((marca.getNome())) && m.getId() != marca.getId()) {
					attributes.addFlashAttribute("icone", "thumb_down");
					attributes.addFlashAttribute("menssagem", "Essa marca já essiste no banco de dados.");
					return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
				}else 
					if (m.getId() == marca.getId()) {
						marcaRepository.saveAndFlush(marca);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Marca alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
				}		
			}	
			
			marcaRepository.saveAndFlush(marca);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Marca salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/marca/registrar");				
				
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
		}
	}
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Marca> marcas = marcaRepository.findAll();
		try {
			if(marcas.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/marca/lista-marca");
			return mvLista.addObject("marcas", marcas);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados");
			return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
		}
	}
	
	public ModelAndView editar(Long id, RedirectAttributes attributes) {	
		
		ModelAndView mvForm = new ModelAndView("dashboard-admin/marca/form-registrar-marca");
		Optional<Marca> marca = marcaRepository.findById(id);
		try {	
			if(marca == null || marca.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/marca/listar");
			}
			
			return mvForm.addObject("marca", marca);
					
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/marca/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		Optional<Marca> marca = marcaRepository.findById(id);
		try {
			if(marca == null || marca.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/marca/listar");
			}				
			marcaRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Marca deletada!");
			return new ModelAndView("redirect:/dashboard-admin/marca/listar");
						
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Marca vinculada a um ou mais produtos!");
			return new ModelAndView("redirect:/dashboard-admin/marca/listar");
		}		
	}
	
	public byte[] visualizar(String imagem) throws IOException {		
		File imagemMarca = new File(caminhoDaImagem+imagem);
		return Files.readAllBytes(imagemMarca.toPath());
	}
}
