package desafio_picpay.com.controller;

import desafio_picpay.com.dto.UsuarioDTO;
import desafio_picpay.com.dto.UsuarioUpdate;
import desafio_picpay.com.entity.usuario.Usuario;
import desafio_picpay.com.entity.usuario.UsuarioResponse;
import desafio_picpay.com.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable Long id) {
        UsuarioResponse usuarioResponse = usuarioService.buscarUsuario(id);
        return ResponseEntity.ok().body(usuarioResponse);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioResponse>> buscarTodosUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.criarUsuario(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioUpdate usuarioUpdate) {
        usuarioService.atualizarUsuario(id, usuarioUpdate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
