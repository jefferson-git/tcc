package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;

@Service
public class UsuarioMedotoPagamentoService {
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private listaDesejosRepository desejosRepository;
			
	public ModelAndView MedotoPagamento() {
		ModelAndView mvMedotoPagamento = new ModelAndView("ecommerce/usuario-metodo-pagamento");
		mvMedotoPagamento.addObject("pedido",carrinhoService.compra);
		mvMedotoPagamento.addObject("departamentos", departamentoRepository.findAll());
		mvMedotoPagamento.addObject("categorias", categoriaRepository.findAll());
		mvMedotoPagamento.addObject("compras", carrinhoService.listaCompras);
		mvMedotoPagamento.addObject("desejos", desejosRepository.findAll());
		return mvMedotoPagamento;
    }	
}
