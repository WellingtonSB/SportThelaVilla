package com.StoreSport.SportThelaVilla.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StoreSport.SportThelaVilla.Model.Loja;
import com.StoreSport.SportThelaVilla.Model.Produto;
import com.StoreSport.SportThelaVilla.Model.UserLogin;
import com.StoreSport.SportThelaVilla.Model.Usuario;
import com.StoreSport.SportThelaVilla.Repository.UsuarioRepository;
import com.StoreSport.SportThelaVilla.Service.ProdutoService;
import com.StoreSport.SportThelaVilla.Service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	
	@Autowired
	private ProdutoService produtoService;

	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return service.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		Optional<Usuario> user = service.CadastrarUsuario(usuario);
		try {
			return ResponseEntity.ok(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
		
	@DeleteMapping("/compra/produtos/{idUsuario}/usuarios/{idProduto}")
	public void postCompra(@PathVariable long idProduto, @PathVariable long idUsuario){
		
		produtoService.excluirProduto(idProduto, idUsuario);
	}

}