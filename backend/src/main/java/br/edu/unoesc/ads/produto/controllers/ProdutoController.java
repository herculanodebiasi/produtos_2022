package br.edu.unoesc.ads.produto.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.unoesc.ads.produto.entities.Produto;
import br.edu.unoesc.ads.produto.repositories.ProdutoRepository;

@Controller
@CrossOrigin(origins = "*")
public class ProdutoController {
	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/produtos")
	public String listarProdutos(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		Calendar cal = Calendar.getInstance();
		String sDataAux;
		dateFormat.applyPattern("dd 'de' MMMM 'de' yyyy");
		sDataAux = dateFormat.format(cal.getTime());
	
		model.addAttribute("data", LocalDateTime.now());
		model.addAttribute("dataExtenso", "<b>" + sDataAux + "</b>");
		model.addAttribute("produtos", produtoRepository.findAll());
		return "lista_produtos";
	}

	@GetMapping("/incluir_produto")
	public String formProduto(Model model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("adicionar", true);
		return "form_produto";
	}

	@PostMapping("/processa_incluir_produto")
	public String processaFormIncluirProduto(@Valid Produto produto, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return "form_produto";
		}
		produtoRepository.save(produto);
		return "redirect:/produtos";
	}
	
	@GetMapping("/alterar_produto/{id}")
	public String alterar(@PathVariable("id") long id, Model model) {
		Produto produto = produtoRepository.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Produto inválido Id:" + id));
		model.addAttribute("produto", produto);
		model.addAttribute("adicionar", false);
		return "form_produto";
	}
	
	@PostMapping("/processa_alterar_produto/{id}")
	public String processaFormAlterarProduto(@PathVariable("id") long id, @Valid Produto produto, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			produto.setId(id);
			return "form_produto";
		}
		produtoRepository.save(produto);
		return "redirect:/produtos";
	}
	
	@GetMapping("/excluir_produto/{id}")
	public String remover(@PathVariable("id") long id, Model model) {
		Produto produto = produtoRepository.findById(id).get();
		produtoRepository.delete(produto);
		return "redirect:/produtos";
	}
}
