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
public class UsuarioTiquetesService {
		
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private listaDesejosRepository desejosRepository;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CompraRepository compraRepository;
			
	public ModelAndView tiquete() {
		ModelAndView mvTiquite = new ModelAndView("ecommerce/usuario-tiquete");
		mvTiquite.addObject("cliente", new Cliente());
		mvTiquite.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvTiquite.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvTiquite.addObject("comprasRealizadas", compraRepository.listarCompras(usuarioService.ClienteLogado().getId()));
		mvTiquite.addObject("pedido",carrinhoService.compra);
		mvTiquite.addObject("departamentos", departamentoRepository.findAll());
		mvTiquite.addObject("categorias", categoriaRepository.findAll());
		mvTiquite.addObject("compras", carrinhoService.listaCompras);
		mvTiquite.addObject("desejos", desejosRepository.findAll());
		return mvTiquite;
    }	
}
