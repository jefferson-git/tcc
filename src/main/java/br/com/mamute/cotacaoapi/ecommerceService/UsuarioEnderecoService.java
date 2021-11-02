package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;

@Service
public class UsuarioEnderecoService {
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private listaDesejosRepository desejosRepository;
			
	public ModelAndView endereco() {
		ModelAndView mvEndereco = new ModelAndView("ecommerce/usuario-endereco");
		mvEndereco.addObject("pedido",carrinhoService.compra);
		mvEndereco.addObject("departamentos", departamentoRepository.findAll());
		mvEndereco.addObject("categorias", categoriaRepository.findAll());
		mvEndereco.addObject("compras", carrinhoService.listaCompras);
		mvEndereco.addObject("desejos", desejosRepository.findAll());
		return mvEndereco;
    }	
}
