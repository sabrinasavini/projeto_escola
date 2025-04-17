package com.senai.projeto_escola.application.service;

import com.senai.projeto_escola.application.dto.CoordenadorDto;
import com.senai.projeto_escola.application.dto.ProfessorDto;
import com.senai.projeto_escola.domain.entity.Professor;
import com.senai.projeto_escola.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepo;

    public void salvar(ProfessorDto professorDto){
        Professor professor = new Professor();
        professor.setNome(professorDto.nome());
        professor.setIdade(professorDto.idade());
        professor.setUnidadesCurriculares(professorDto.unidadesCurriculares());
        professorRepo.save(professor);
    }

    public List<ProfessorDto> listar(){
        return professorRepo.findAll().stream().map(p -> new ProfessorDto(
                p.getId(),
                p.getNome(),
                p.getIdade(),
                p.getTurma(),
                p.getUnidadesCurriculares()
        ) ).toList();
    }


        }


