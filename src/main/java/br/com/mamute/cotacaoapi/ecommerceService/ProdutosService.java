package br.com.mamute.cotacaoapi.ecommerceService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Produto;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;

@Service
public class ProdutosService {

	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
	
	@Autowired
	private ProdutoRepository produtoRepository;
		
	public ModelAndView descricao(Long id) {
		ModelAndView mvDetalhe = new ModelAndView("ecommerce/fragmentos/modal-detalhe-produto-fragmento");
		Optional<Produto> produtOptional = produtoRepository.findById(id);
		Produto produto = produtOptional.get();
		mvDetalhe.addObject("produto", produto);
		return mvDetalhe;
    }	
	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
