package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;

@Service
public class UsuarioTiquetesService {
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private listaDesejosRepository desejosRepository;
			
	public ModelAndView tiquete() {
		ModelAndView mvTiquite = new ModelAndView("ecommerce/usuario-tiquete");
		mvTiquite.addObject("pedido",carrinhoService.compra);
		mvTiquite.addObject("departamentos", departamentoRepository.findAll());
		mvTiquite.addObject("categorias", categoriaRepository.findAll());
		mvTiquite.addObject("compras", carrinhoService.listaCompras);
		mvTiquite.addObject("desejos", desejosRepository.findAll());
		return mvTiquite;
    }	
}
