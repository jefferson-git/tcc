package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Departamento;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;

@Service
public class PedidoFinalizadoService {	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository ;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
		
	@Autowired
	private CarrinhoService carrinhoService; 
	
	public ModelAndView page(Departamento departamento) {
    	ModelAndView mvpPedido = new ModelAndView("ecommerce/pedido-finalizado");
    	mvpPedido.addObject("departamentos", departamentoRepository.findAll());
    	mvpPedido.addObject("categorias", categoriaRepository.findAll());
    	mvpPedido.addObject("produtos", produtoRepository.findAll());
    	mvpPedido.addObject("pedido", carrinhoService.compra);
    	mvpPedido.addObject("compras", carrinhoService.listaCompras);
		return mvpPedido;

    }	
}
