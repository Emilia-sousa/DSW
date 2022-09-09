package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import javax.validation.Valid;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;
import br.ufscar.dc.dsw.service.spec.ICompraService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

//import java.io.IOException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class PacoteRestController {

    @Autowired
    private IAgenciaService aservice;
    
    @Autowired
    private IPacoteService pservice;

    @Autowired
    private ICompraService cservice;

    @Autowired
    private IUsuarioService uservice;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
     }

	private void parse(PacoteTuristico pacote, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				pacote.setId(((Integer) id).longValue());
			} else {
				pacote.setId((Long) id);
			}
     	}

		pacote.setDestino((String) json.get("destino"));
        pacote.setDataPartida((String) json.get("dataPartida"));
        pacote.setPreco(BigDecimal.valueOf((double) json.get("preco")));
        pacote.setAtivo(true);
    }

	@GetMapping(path = "/pacotes")
	public ResponseEntity<List<PacoteTuristico>> lista() {
		List<PacoteTuristico> lista = pservice.buscarTodos();
        
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

        for (PacoteTuristico pacote : lista) {
            Agencia agencia = pacote.getAgencia();
            agencia.setPassword(null);
            agencia.setPacotes(null);
            pacote.setAgencia(agencia);
        }
		return ResponseEntity.ok(lista);
    }

	@GetMapping(path = "/pacotes/clientes/{id}")
	public ResponseEntity<List<PacoteTuristico>> lista(@PathVariable("id") long id) {
        Usuario u = uservice.buscarPorId(id);
        if(u == null) {
            return ResponseEntity.notFound().build();
        }        

	    List<Compra> compra = cservice.buscarTodosPorUsuario(u);
        
		if (compra == null) {
            System.out.println("sem pacotes para este id");
			return ResponseEntity.notFound().build();
		}

        List<PacoteTuristico> lista = new ArrayList<>();

        for (Compra c : compra){
            PacoteTuristico pacote = c.getPacote();
            Agencia agencia = pacote.getAgencia();
            agencia.setPacotes(null);
            agencia.setPassword(null);
            pacote.setAgencia(agencia);
            lista.add(pacote);
        }
		return ResponseEntity.ok(lista);
    }

	@PostMapping(path = "/pacotes/agencias/{id}")
	@ResponseBody
	public ResponseEntity<PacoteTuristico> cria(@RequestBody JSONObject json, @PathVariable("id") long id) {
		try {
			if (isJSONValid(json.toString())) {
				PacoteTuristico pacote = new PacoteTuristico();
				parse(pacote, json);
                Agencia agencia = aservice.buscarPorId(id);
                if (agencia == null) {
                    return ResponseEntity.notFound().build();
                }

                pacote.setAgencia(agencia);
				pservice.salvar(pacote);
				return ResponseEntity.ok(pacote);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
    }

    @GetMapping(path = "/pacotes/agencias/{id}")
    public ResponseEntity<List<PacoteTuristico>> listaPorAgencia(@PathVariable("id") long id) {
        Agencia a = aservice.buscarPorId(id);
        if(a == null) {
            return ResponseEntity.notFound().build();
        }

        List<PacoteTuristico> lista = pservice.buscarPorAgencia(a);

        if (lista == null) {
            System.out.println("sem pacotes para este id");
            return ResponseEntity.notFound().build();
        }

        for (PacoteTuristico pacote : lista) {
            Agencia agencia = pacote.getAgencia();
            agencia.setPassword(null);
            agencia.setPacotes(null);
            pacote.setAgencia(agencia); 
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/pacotes/destinos/{nome}")
    public ResponseEntity<List<PacoteTuristico>> listaPorAgencia(@PathVariable("nome") String nome) {
        if(nome == null) {
            return ResponseEntity.notFound().build();
        }

        List<PacoteTuristico> lista = pservice.getByKeyword(nome);

        if (lista == null) {
            System.out.println("sem pacotes para este destino");
            return ResponseEntity.notFound().build();
        }

        for (PacoteTuristico pacote : lista) {
            Agencia agencia = pacote.getAgencia();
            agencia.setPassword(null);
            agencia.setPacotes(null);
            pacote.setAgencia(agencia);
        }

        return ResponseEntity.ok(lista);
    }

}
