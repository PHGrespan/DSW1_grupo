package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.ClienteDetails;
import br.ufscar.dc.dsw.service.spec.ICompraService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@Controller
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private ICompraService service;
	
	@Autowired
	private IPacoteService PacoteService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Compra compra) {
		compra.setCliente(this.getCliente());
		return "compra/cadastro";
	}
	
	private Cliente getCliente() {
		ClienteDetails ClienteDetails = (ClienteDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ClienteDetails.getCliente();
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {			
		model.addAttribute("compras",service.buscarTodosPorCliente(this.getCliente()));
		
		//System.out.println("\n\n\n\nOPAAAAAAAAAAAAAAAA\n\n\n\n\n\n" + service.buscarTodosPorCliente(this.getCliente()).get(0).getPacote().getNome());
		return "compra/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "compra/cadastro";
		}

		Compra compra = new Compra();
		
		compra.setCliente(this.getCliente());
		compra.setPacote(pacote);
		compra.setPreco(pacote.getPreco());
		
		service.salvar(compra);
		attr.addFlashAttribute("sucess", "Compra inserida com sucesso.");
		return "redirect:/compras/listar";
	}
	
	@ModelAttribute("pacotes")
	public List<Pacote> listaPacotes() {
		return PacoteService.buscarTodos();
	}
}
