package com.senai.projeto_escola.application.service;

import com.senai.projeto_escola.application.dto.CoordenadorDto;
import com.senai.projeto_escola.application.dto.ProfessorDto;
import com.senai.projeto_escola.domain.entity.Coordenador;
import com.senai.projeto_escola.domain.entity.Professor;
import com.senai.projeto_escola.domain.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepo;

    public void salvar(CoordenadorDto coordenadorDto){
        Coordenador coordenador = new Coordenador();
        coordenador.setNome(coordenadorDto.nome());
        coordenador.setIdade(coordenadorDto.idade());
        coordenador.setEquipeProfessores(mapEquipe(coordenadorDto.equipe()));
        coordenadorRepo.save(coordenador);
    }

    private List<Professor> mapEquipe(List<ProfessorDto> professorDtos){
        return professorDtos.stream().map(professorDto -> {
            Professor professor = new Professor();
            professor.setNome(professorDto.nome());
            professor.setIdade(professorDto.idade());
            professor.setTurma(professorDto.turma());
            professor.setUnidadesCurriculares(professorDto.unidadesCurriculares());
            return professor;
        }).toList();
    }

    public List<CoordenadorDto> listar(){
        return coordenadorRepo.findAll().stream().map(c -> new CoordenadorDto(
                c.getId(),
                c.getNome(),
                c.getIdade(),
                c.getEquipeProfessores().stream().map(p -> new ProfessorDto(
                        p.getId(), p.getNome(), p.getIdade(), p.getTurma(),p.getUnidadesCurriculares()
                ) ).toList()
        )).toList();
    }
}
