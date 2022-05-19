package br.com.mamute.cotacaoapi.ecommerceService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.ClienteRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.EmailRepository;
import br.com.mamute.cotacaoapi.repository.EnderecoRepository;
import br.com.mamute.cotacaoapi.repository.TelefoneRepository;

@Service
public class ClienteCadastroService {
	
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private TelefoneRepository telefoneRepositoty;	
	@Autowired private EnderecoRepository enderecoRepository;
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private EmailRepository emailRepository;
			
	public ModelAndView logar(Cliente cliente, BindingResult result) {
		ModelAndView mvLogin = new ModelAndView("ecommerce/login");
		mvLogin.addObject("cliente", new Cliente());
		mvLogin.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvLogin.addObject("descricoes",descricaoTelefoneRepository.findAll());
		mvLogin.addObject("pedido",carrinhoService.compra);
		mvLogin.addObject("departamentos", departamentoRepository.findAll());
		mvLogin.addObject("categorias", categoriaRepository.findAll());
		mvLogin.addObject("compras", carrinhoService.listaCompras);
		return mvLogin;
    }
	
	@Transactional
	public ModelAndView registrar(Cliente cliente, BindingResult result) {
		/*
		if(result.hasErrors())
			logar(cliente, result);			
		try { 		
			cliente.getUsuario().setDataCadastro(new Date( System.currentTimeMillis()));
			cliente.setSexo("sera atualizado");
			cliente.setImagem("sera atuallizado");
			telefoneRepositoty.saveAndFlush(cliente.getTelefone());
			enderecoRepository.saveAndFlush(cliente.getEndereco());
			emailRepository.saveAndFlush(cliente.getEmail());
			clienteRepository.saveAndFlush(cliente); 
		} catch (Exception e) {
			e.printStackTrace();
		}		
		ModelAndView mvPagina = new ModelAndView("redirect:/mamute/carrinho");
		//attributes.addFlashAttribute("menssagem", "Usuário salva com sucesso.");
		 * 
		 */
		return null;	
    }
}
