package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.CompraRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class UsuarioPedidoService {
	
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private listaDesejosRepository desejosRepository;		
	@Autowired UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CompraRepository compraRepository;
	 
	public ModelAndView pedido() {
		ModelAndView mvPedido = new ModelAndView("ecommerce/usuario-pedido");
		mvPedido.addObject("cliente", new Cliente());
		mvPedido.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvPedido.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvPedido.addObject("comprasRealizadas", compraRepository.listarCompras(usuarioService.ClienteLogado().getId()));
		mvPedido.addObject("pedido",carrinhoService.compra);
		mvPedido.addObject("departamentos", departamentoRepository.findAll());
		mvPedido.addObject("categorias", categoriaRepository.findAll());
		mvPedido.addObject("compras", carrinhoService.listaCompras);
		mvPedido.addObject("desejos", desejosRepository.findAll());
		return mvPedido;
    }	
}
