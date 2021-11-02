package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;

@Service
public class UsuarioPedidoService {
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private listaDesejosRepository desejosRepository;
			
	public ModelAndView pedido() {
		ModelAndView mvPedido = new ModelAndView("ecommerce/usuario-pedido");
		mvPedido.addObject("pedido",carrinhoService.compra);
		mvPedido.addObject("departamentos", departamentoRepository.findAll());
		mvPedido.addObject("categorias", categoriaRepository.findAll());
		mvPedido.addObject("compras", carrinhoService.listaCompras);
		mvPedido.addObject("desejos", desejosRepository.findAll());
		return mvPedido;
    }	
}
