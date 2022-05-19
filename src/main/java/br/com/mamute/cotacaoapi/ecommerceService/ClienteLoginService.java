package br.com.mamute.cotacaoapi.ecommerceService;


import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.CartaoDeCredito;
import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.model.Endereco;
import br.com.mamute.cotacaoapi.model.Papel;
import br.com.mamute.cotacaoapi.model.Permissao;
import br.com.mamute.cotacaoapi.repository.CartaoDeCreditoRepository;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.ClienteRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.EmailRepository;
import br.com.mamute.cotacaoapi.repository.PapelRepository;
import br.com.mamute.cotacaoapi.repository.PermissaoRepository;
import br.com.mamute.cotacaoapi.repository.TelefoneRepository;

@Service
public class ClienteLoginService {
	
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private TelefoneRepository telefoneRepositoty;	
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private EmailRepository emailRepository;
	@Autowired private PapelRepository papelRepository;
	@Autowired private PermissaoRepository permissaoRepository;
	@Autowired private CartaoDeCreditoRepository cartaoDeCreditoRepository;
			
	public ModelAndView logar(Cliente cliente, BindingResult result, String mensagem) {
      
		ModelAndView mvLogin = new ModelAndView("ecommerce/login");
		mvLogin.addObject("descricoes",descricaoTelefoneRepository.findAll());		
		mvLogin.addObject("pedido",carrinhoService.compra);
		mvLogin.addObject("departamentos", departamentoRepository.findAll());
		mvLogin.addObject("categorias", categoriaRepository.findAll());
		mvLogin.addObject("cliente", new Cliente() );
		mvLogin.addObject("logout", mensagem);
		mvLogin.addObject("compras", carrinhoService.listaCompras); 
		return mvLogin;
    }
	
	@Transactional
	public ModelAndView registrar(Cliente cliente, BindingResult result, RedirectAttributes attributes, String mensagem) {
		Permissao permissao = new Permissao();
		if(result.hasErrors())
			logar(cliente, result , mensagem);			
		try { 		
			cliente.getUsuario().setDataCadastro(new Date( System.currentTimeMillis()));
			cliente.getUsuario().setUsername(cliente.getEmail().getEnderecoEmail());
			cliente.getUsuario().setStatus(1);
			cliente.setSexo("sera atualizado");
			cliente.setImagem("sera atuallizado"); 
			cliente.setEndereco(new Endereco(null, "", "", "", "", "", 0, "", ""));
			cliente.setCartao(new CartaoDeCredito(null, "","", "", ""));
			telefoneRepositoty.saveAndFlush(cliente.getTelefone());
			emailRepository.saveAndFlush(cliente.getEmail());	
			cartaoDeCreditoRepository.saveAndFlush(cliente.getCartao());
			clienteRepository.saveAndFlush(cliente); 
			permissao.setUsuario(cliente.getUsuario());
			
			for (Papel papel  : papelRepository.findAll()) {
				if(papel.getNome().equalsIgnoreCase("USUARIO"))
					permissao.setPapel(papel);
			}		
			permissaoRepository.save(permissao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		attributes.addFlashAttribute("mensagem", "salvo");	
		return new ModelAndView("redirect:/usuario/login");	
    }
}
