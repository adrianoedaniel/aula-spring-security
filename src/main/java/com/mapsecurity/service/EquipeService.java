package com.mapsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapsecurity.entities.Equipe;
import com.mapsecurity.repository.EquipeRepository;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;
    
    @Autowired
    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Equipe getEquipeById(Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        return equipe.orElse(null);
    }
    //Query Method 
    public List<Equipe> buscarEquipesPorNome(String nome) {
        return equipeRepository.findByNome(nome);
      }
    //@query
    public List<Equipe> buscarPorCidade(String cidade) {
        return equipeRepository.findByCidade(cidade);
    }
  
    public Equipe salvarEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe updateEquipe(Long id, Equipe updatedEquipe) {
        Optional<Equipe> existingEquipe = equipeRepository.findById(id);
        if (existingEquipe.isPresent()) {
            updatedEquipe.setId(id);
            return equipeRepository.save(updatedEquipe);
        }
        return null;
    }

    public boolean deleteEquipe(Long id) {
        Optional<Equipe> existingEquipe = equipeRepository.findById(id);
        if (existingEquipe.isPresent()) {
            equipeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
