package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;

@Service
public class RecuperaSenhaService {

	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;
		
	public ModelAndView recupera() {
		ModelAndView mvCarrinho = new ModelAndView("ecommerce/recupera-senha");
		mvCarrinho.addObject("pedido",carrinhoService.compra);
		mvCarrinho.addObject("departamentos", departamentoRepository.findAll());
		mvCarrinho.addObject("categorias", categoriaRepository.findAll());
		return mvCarrinho.addObject("compras", carrinhoService.listaCompras);
    }	
}
