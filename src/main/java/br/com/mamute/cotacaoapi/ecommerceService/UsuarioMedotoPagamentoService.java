package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;

@Service
public class UsuarioMedotoPagamentoService {
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
			
	public ModelAndView MedotoPagamento() {
		ModelAndView mvLogin = new ModelAndView("ecommerce/usuario-metodo-pagamento");
		mvLogin.addObject("pedido",carrinhoService.compra);
		mvLogin.addObject("departamentos", departamentoRepository.findAll());
		mvLogin.addObject("categorias", categoriaRepository.findAll());
		return mvLogin.addObject("compras", carrinhoService.listaCompras);
    }	
}
