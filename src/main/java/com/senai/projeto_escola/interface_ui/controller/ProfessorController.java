package com.senai.projeto_escola.interface_ui.controller;


import com.senai.projeto_escola.application.dto.ProfessorDto;
import com.senai.projeto_escola.application.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<String> salvar (@RequestBody ProfessorDto professorDto){
        professorService.salvar(professorDto);
        return ResponseEntity.ok("Professor salvo com sucesso!");
    }
    @GetMapping
    public ResponseEntity<List<ProfessorDto>> listarProfessores(){
        return ResponseEntity.ok(professorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> buscarPorId(@PathVariable Long id){
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ProfessorDto professorDto){
        if (professorService.atualizar(id, professorDto)){
            return ResponseEntity.ok("Professor atualizado com sucesso!");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        if (professorService.deletar(id)){
            return ResponseEntity.ok("Dados do professor deletados com sucesso");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
