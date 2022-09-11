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
public class ClienteRestController {

    @Autowired
    private IUsuarioService uservice;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
     }

    private void parse(Usuario usuario, JSONObject json) {

        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                usuario.setId(((Integer) id).longValue());
            } else {
                usuario.setId((Long) id);
            }
        }
        usuario.setUsername((String) json.get("username"));
        usuario.setCPF((String) json.get("cpf"));
        usuario.setPassword((String) json.get("password"));
        usuario.setName((String) json.get("name"));
        usuario.setDataNascimento((String) json.get("dataNascimento"));
        usuario.setEmail((String) json.get("email"));
        usuario.setSexo((String) json.get("sexo"));
        usuario.setTelefone((String) json.get("telefone"));
        usuario.setRole("ROLE_USER");
        usuario.setEnabled(true);

    }

    @GetMapping(path = "/clientes")
    public ResponseEntity<List<Usuario>> lista() {
        List<Usuario> lista = uservice.buscarTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Usuario> usuariosRemover = new ArrayList<>();

        for (Usuario usuario : lista) {
            if (usuario.getRole().equals("ROLE_USER") ){
                usuario.setPassword(null);
            }
            else {
                System.out.println(usuario.getRole());
                usuariosRemover.add(usuario);
            }
        }

        lista.removeAll(usuariosRemover);
        return ResponseEntity.ok(lista);
        
    }

    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<Usuario> lista(@PathVariable("id") long id) {
        Usuario u = uservice.buscarPorId(id);
        if(u == null || (!u.getRole().equals("ROLE_USER"))) {
            return ResponseEntity.notFound().build();
        }

        u.setPassword(null);
        return ResponseEntity.ok(u);

    }

    @PostMapping(path = "/clientes")
    @ResponseBody
    public ResponseEntity<Usuario> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Usuario usuario = new Usuario();
                parse(usuario, json);
                
                uservice.salvar(usuario);
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
    
    @PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Usuario> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Usuario usuario = uservice.buscarPorId(id);
                if(usuario == null || (!usuario.getRole().equals("ROLE_USER"))) {
					return ResponseEntity.notFound().build();
				} else {
					parse(usuario, json);
					uservice.salvar(usuario);
					return ResponseEntity.ok(usuario);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@DeleteMapping(path = "/clientes/{id}")
 public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Usuario usuario = uservice.buscarPorId(id);
		if(usuario == null || (!usuario.getRole().equals("ROLE_USER"))) {
			return ResponseEntity.notFound().build();
		} else {
			uservice.excluir(id);
			return ResponseEntity.noContent().build();
		}
	} 
}
