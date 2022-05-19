package br.com.mamute.cotacaoapi.ecommerceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.model.Usuario;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;



@Service
public class ClienteLogoutService {
	
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private ClienteLoginService clienteLoginService;
	
	public ModelAndView sair(HttpServletRequest request) {		
		HttpSession httpSession = request.getSession();
		httpSession.getServletContext();
        httpSession.invalidate(); 
        
        String mensagem = "logout";
        
        ModelAndView mvLogin = new ModelAndView("ecommerce/login");
        mvLogin.addObject("usuarioLogado",usuarioService.ClienteLogado());
        mvLogin.addObject("descricoes",descricaoTelefoneRepository.findAll());
		mvLogin.addObject("pedido",carrinhoService.compra);
		mvLogin.addObject("departamentos", departamentoRepository.findAll());
		mvLogin.addObject("categorias", categoriaRepository.findAll());
		mvLogin.addObject("compras", carrinhoService.listaCompras); 
		mvLogin.addObject("logout", mensagem); 
		mvLogin.addObject("cliente", new Cliente()); 
		
		return clienteLoginService.logar(new Cliente(), null, mensagem);
    }
}
