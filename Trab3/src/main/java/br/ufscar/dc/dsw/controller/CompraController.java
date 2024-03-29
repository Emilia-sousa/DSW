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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ICompraService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@Controller
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private ICompraService service;
	
	@Autowired
	private IPacoteService PacoteTuristicoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(ModelMap model, Compra compra) {
		String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		compra.setUsuario(this.getUsuario());
		compra.setData(data);
        model.addAttribute("pacotes", PacoteTuristicoService.buscarTodosAtivos());
		return "compra/cadastro";
	}
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
					
		model.addAttribute("compras",service.buscarTodosPorUsuario(this.getUsuario()));
		
		return "compra/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Compra compra, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "compra/cadastro";
		}
		
		service.salvar(compra);
		attr.addFlashAttribute("sucess", "Compra inserida com sucesso.");
		return "redirect:/compras/listar";
	}
	
	@ModelAttribute("PacoteTuristicos")
	public List<PacoteTuristico> listaPacoteTuristicos() {
		return PacoteTuristicoService.buscarTodos();
	}
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addFlashAttribute("sucess", "compra.delete.sucess");
        return "redirect:/compras/listar";
    }
}
