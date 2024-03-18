package application.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import application.model.Aluno;
import application.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;


    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }
    

    public List<Aluno> findAll() {
        return (List<Aluno>) alunoRepository.findAll(); 
    }

    public Aluno findById(Long id) {
        try {
            if (id != null) {
                return alunoRepository.findById(id).get();
            }
            return null;

        } catch (RuntimeException e) {
            throw new RuntimeException("Aluno com o ID: " + id + "não encontrado.");
        }
    }

    public Aluno create(Aluno aluno) {
        try {
            if (aluno != null) {
                return alunoRepository.save(aluno);
            }
            return null;

        } catch(RuntimeException e) {
            throw new RuntimeException("Erro ao criar aluno.");
        }
    }

    public Aluno update(Aluno aluno, Long id) {
        try {
            Aluno findAluno = alunoRepository.findById(id).get();
            if (findAluno != null) {

                findAluno.setCurso(aluno.getCurso());
                findAluno.setIdade(aluno.getIdade());
                findAluno.setNome(aluno.getNome());
                return alunoRepository.save(findAluno);
            }

            return null;

        } catch(RuntimeException e) {
            throw new RuntimeException("Aluno com o ID: " + id + "não encontrado.");
        }
    }

    public void delete(Long id) {
        try {
            Aluno findAluno = alunoRepository.findById(id).get();
            if (findAluno != null) {
                alunoRepository.deleteById(id);
            }

        } catch(RuntimeException e) {
            throw new RuntimeException("Aluno com o ID: " + id + "não encontrado.");
        }
    }




}
