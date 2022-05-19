package br.com.mamute.cotacaoapi.ecommerceService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CartaoDeCreditoRepository;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.FormaPagamentoRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class PagamentoService {

	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";		
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CartaoDeCreditoRepository cartaoDeCreditoRepository;
	@Autowired private FormaPagamentoRepository formaPagamentoRepository;
	
		
	public ModelAndView pagamento() {
		ModelAndView mvPagamento = new ModelAndView("ecommerce/pagamento");
		mvPagamento.addObject("cliente", new Cliente());
		mvPagamento.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvPagamento.addObject("formaPagamentos", formaPagamentoRepository.findAll());
		mvPagamento.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvPagamento.addObject("pedido",carrinhoService.compra);
		mvPagamento.addObject("cartao",cartaoDeCreditoRepository.findById(usuarioService.ClienteLogado().getId()));
		mvPagamento.addObject("departamentos", departamentoRepository.findAll());
		mvPagamento.addObject("categorias", categoriaRepository.findAll());
		mvPagamento.addObject("compras", carrinhoService.listaCompras);
		return mvPagamento;
    }	
	
	public String pagamentoCartao(Long id, RedirectAttributes attributes) {
		carrinhoService.finalizaCompra(id, attributes);
		return "redirect:/usuario/pedido-finalizado";
    }
		
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
