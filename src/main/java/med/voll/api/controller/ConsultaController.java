package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("consultas")
public class ConsultaController {


    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println(dados);
    return ResponseEntity.ok(new DadosDetalhamentoConsulta((Long) null, (long) 1L, 1L, (LocalDateTime) null));
    }
}
