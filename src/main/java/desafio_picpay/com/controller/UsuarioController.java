package desafio_picpay.com.controller;

import desafio_picpay.com.dto.UsuarioDTO;
import desafio_picpay.com.dto.UsuarioUpdate;
import desafio_picpay.com.entity.Usuario;
import desafio_picpay.com.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
