package br.com.mamute.cotacaoapi.ecommerceService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.ClienteRepository;
import br.com.mamute.cotacaoapi.repository.CompraRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.EmailRepository;
import br.com.mamute.cotacaoapi.repository.EnderecoRepository;
import br.com.mamute.cotacaoapi.repository.TelefoneRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class UsuarioPerfilService {
	
	private static String caminhoDaImagem = "/cotacao_api/imagens/cliente/";
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private listaDesejosRepository desejosRepository;	
	@Autowired private UsuarioService usuarioService;
	@Autowired private TelefoneRepository telefoneRepositoty;	
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private EmailRepository emailRepository;
	@Autowired private EnderecoRepository enderecoRepository;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CompraRepository compraRepository;

	public ModelAndView perfil(Cliente cliente) {		
		ModelAndView mvPerfil = new ModelAndView("ecommerce/usuario-perfil");
		mvPerfil.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvPerfil.addObject("descricoes",descricaoTelefoneRepository.findAll());
		mvPerfil.addObject("pedido",carrinhoService.compra);
		mvPerfil.addObject("comprasRealizadas", compraRepository.listarCompras(usuarioService.ClienteLogado().getId()));
		mvPerfil.addObject("departamentos", departamentoRepository.findAll());
		mvPerfil.addObject("categorias", categoriaRepository.findAll());
		mvPerfil.addObject("compras", carrinhoService.listaCompras);
		mvPerfil.addObject("desejos", desejosRepository.findAll());
		return mvPerfil;
    }	
	
	public ModelAndView editar(Cliente cliente, MultipartFile arquivo, BindingResult result, RedirectAttributes attributes) {		
		Cliente clienteRecuperado = usuarioService.ClienteLogado();
		
		if(result.hasErrors())
			perfil(cliente);			
		try {					
			if(arquivo.isEmpty()){		
				cliente.setImagem(clienteRecuperado.getImagem());	
				cliente.setEndereco(clienteRecuperado.getEndereco());						
				cliente.setCartao(clienteRecuperado.getCartao());				
				cliente.setUsuario(clienteRecuperado.getUsuario());				
				enderecoRepository.saveAndFlush(cliente.getEndereco());				
				telefoneRepositoty.saveAndFlush(cliente.getTelefone());
				emailRepository.saveAndFlush(cliente.getEmail());				
				clienteRepository.saveAndFlush(cliente);
				return new ModelAndView("redirect:/usuario/perfil");
			}
			 
			if(!arquivo.isEmpty()) {
				Random random = new Random();
				float num = random.nextFloat();
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoDaImagem+String.valueOf(num)+arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				cliente.setImagem(String.valueOf(num)+arquivo.getOriginalFilename());
			}			
			cliente.setEndereco(clienteRecuperado.getEndereco());
			cliente.setUsuario(clienteRecuperado.getUsuario());
			enderecoRepository.saveAndFlush(cliente.getEndereco());	
			telefoneRepositoty.saveAndFlush(cliente.getTelefone());
			emailRepository.saveAndFlush(cliente.getEmail());
			clienteRepository.saveAndFlush(cliente); 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/usuario/perfil");	
    }	
	
	public byte[] imagem(String imagem) throws IOException {
		File imagemUsuario = new File(caminhoDaImagem+imagem);
		return Files.readAllBytes(imagemUsuario.toPath());
	}
}
