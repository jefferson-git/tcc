package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class PedidoFinalizadoService {	
	
	private String codigo;
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private ProdutoRepository produtoRepository;	
	@Autowired private DepartamentoRepository departamentoRepository;		
	@Autowired private CarrinhoService carrinhoService; 
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	
	
	
	public ModelAndView page() {
    	ModelAndView mvPedido = new ModelAndView("ecommerce/pedido-finalizado");
		mvPedido.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvPedido.addObject("cliente", new Cliente());
		mvPedido.addObject("descricoes", descricaoTelefoneRepository.findAll());
    	mvPedido.addObject("departamentos", departamentoRepository.findAll());
    	mvPedido.addObject("categorias", categoriaRepository.findAll());
    	mvPedido.addObject("produtos", produtoRepository.findAll());
    	mvPedido.addObject("pedido", carrinhoService.compra);
    	mvPedido.addObject("compras", carrinhoService.listaCompras);
		return mvPedido;
    }	
		
	public ModelAndView pedido() {
    	ModelAndView mvPedido = new ModelAndView("ecommerce/pedido-finalizado");
		mvPedido.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvPedido.addObject("cliente", new Cliente());
		mvPedido.addObject("codigo", codigo);
		mvPedido.addObject("descricoes", descricaoTelefoneRepository.findAll());
    	mvPedido.addObject("departamentos", departamentoRepository.findAll());
    	mvPedido.addObject("categorias", categoriaRepository.findAll());
    	mvPedido.addObject("produtos", produtoRepository.findAll());
    	mvPedido.addObject("pedido", carrinhoService.compra);
    	mvPedido.addObject("compras", carrinhoService.listaCompras);
		return mvPedido;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	
}
