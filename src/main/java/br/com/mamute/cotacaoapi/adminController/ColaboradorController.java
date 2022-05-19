package br.com.mamute.cotacaoapi.adminController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Colaborador;
import br.com.mamute.cotacaoapi.model.DescricaoTelefone;
import br.com.mamute.cotacaoapi.repository.ColaboradorRepository;
import br.com.mamute.cotacaoapi.repository.EmailRepository;
import br.com.mamute.cotacaoapi.repository.EnderecoRepository;
import br.com.mamute.cotacaoapi.repository.TelefoneRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.UsuarioRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Controller
@RequestMapping("/dashboard-admin/colaborador")
public class ColaboradorController {
	
	private static String caminhoDaImagem = "/cotacao_api/imagens/";	
	@Autowired private UsuarioRepository usuarioRepository;		
	@Autowired private ColaboradorRepository colaboradorRepository;	
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;		
	@Autowired private TelefoneRepository telefoneRepositoty;	
	@Autowired private EnderecoRepository enderecoRepository;		
	@Autowired private EmailRepository emailRepository;
	@Autowired private UsuarioService usuarioService;	
	private ModelAndView mvForm = new ModelAndView("dashboard-admin/colaborador/form-registrar-colaborador");
	private ModelAndView mvLista = new ModelAndView("dashboard-admin/colaborador/lista-colaborador");
	
	@GetMapping("/registrar")
	ModelAndView form(Colaborador colaborador, DescricaoTelefone descricao, MultipartFile arquivo) {
		mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		mvForm.addObject("colaborador", colaborador);
		mvForm.addObject("descricoes", descricaoTelefoneRepository.findAll());		
		mvForm.addObject("arquivo", arquivo);
		return mvForm;	
	}
	
	@PostMapping("/salvar")
	@Transactional
	ModelAndView salvar(@Valid Colaborador colaborador, BindingResult result,DescricaoTelefone tipo,
		@RequestParam("file") MultipartFile arquivo, RedirectAttributes attributes) {
				
		if(result.hasErrors())
			form(colaborador, tipo, arquivo);			
		try {
			Random random = new Random();
			float num = random.nextFloat();
			
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoDaImagem+String.valueOf(num)+arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				colaborador.setImagem(String.valueOf(num)+arquivo.getOriginalFilename());
			}
			telefoneRepositoty.saveAndFlush(colaborador.getTelefone());
			enderecoRepository.saveAndFlush(colaborador.getEndereco());
			emailRepository.saveAndFlush(colaborador.getEmail());
			usuarioRepository.saveAndFlush(colaborador.getUsuario());
			colaboradorRepository.saveAndFlush(colaborador);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		ModelAndView mvForm = new ModelAndView("redirect:/dashboard-admin/colaborador/registrar");
		attributes.addFlashAttribute("menssagem", "Usuário salva com sucesso.");
		return mvForm;		
	}
	
	@GetMapping("/listar")
	public ModelAndView listar(RedirectAttributes attributes) {
		List<Colaborador> colaboradores = colaboradorRepository.findAll();
		try {
			if(colaboradores.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/colaborador/registrar");
			}
			
		ModelAndView mvLista = new ModelAndView("dashboard-admin/colaborador/lista-colaborador");
		mvLista.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		return mvLista.addObject("colaboradores", colaboradores);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/marca/registrar");
		}
	}	
	
	@GetMapping("/{id}")
	ModelAndView editar(@PathVariable(name = "id") Long id, RedirectAttributes attributes) {
		mvForm.addObject("tipos", descricaoTelefoneRepository.findAll());
		mvForm.addObject("colaboradorLogado", usuarioService.usuarioLogado());
		mvForm.addObject("colaborador", colaboradorRepository.findById(id));
        return mvForm;
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable(name = "id") Long id) {
        colaboradorRepository.deleteById(id);
        mvLista.addObject("colaborador", colaboradorRepository.findAll()); 
		return mvForm;
	}
	
	@GetMapping("/visualizar/{id}")
	ModelAndView visualizar(@PathVariable(name = "id") Long id) {
		ModelAndView mv = new ModelAndView("dashboard-admin/colaborador/visao-colaborador");
		Optional<Colaborador> colab = colaboradorRepository.findById(id);
		Colaborador colaborador = colab.get(); 
		mv.addObject("colaboradorLogado", usuarioService.usuarioLogado());
        mv.addObject("colaborador", colaborador); 
		return mv;
	}
    	
	@ResponseBody
	@GetMapping("/mostarImagem/{imagem}")
	byte[] mostarImagem(@PathVariable(name = "imagem") String imagem) throws IOException {
		File imagemColaborador = new File(caminhoDaImagem+imagem);
		return Files.readAllBytes(imagemColaborador.toPath());
	}
}
