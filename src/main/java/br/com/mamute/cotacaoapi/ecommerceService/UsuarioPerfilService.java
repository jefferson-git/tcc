package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;

@Service
public class UsuarioPerfilService {
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private listaDesejosRepository desejosRepository;
			
	public ModelAndView perfil() {
		ModelAndView mvPerfil = new ModelAndView("ecommerce/usuario-perfil");
		mvPerfil.addObject("pedido",carrinhoService.compra);
		mvPerfil.addObject("departamentos", departamentoRepository.findAll());
		mvPerfil.addObject("categorias", categoriaRepository.findAll());
		mvPerfil.addObject("compras", carrinhoService.listaCompras);
		mvPerfil.addObject("desejos", desejosRepository.findAll());
		return mvPerfil;
    }	
}
