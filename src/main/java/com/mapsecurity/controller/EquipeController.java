package com.mapsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapsecurity.entities.Equipe;
import com.mapsecurity.service.EquipeService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipe")

public class EquipeController {
    
    private final EquipeService equipeService;
    
    @Autowired
    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }
    //Query Method
    @GetMapping("/nome/{nome}")
    @Operation(summary = "Localiza o equipe por nome")
    public ResponseEntity<List<Equipe>> buscarEquipesPorNOme(@PathVariable String nome) {
      List<Equipe> equipes = equipeService.buscarEquipesPorNome(nome);
      return ResponseEntity.ok(equipes);
    }
    
    //@query
    @GetMapping("/cidade/{cidade}")
    @Operation(summary = "Localiza o equipe por cidade")
    public List<Equipe> findEquipesPorCidade(@PathVariable String cidade) {
        return equipeService.buscarPorCidade(cidade);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable Long id) {
        Equipe equipe = equipeService.getEquipeById(id);
        if (equipe != null) {
            return ResponseEntity.ok(equipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Equipe>> getAllEquipes() {
        List<Equipe> equipes = equipeService.getAllEquipes();
        return ResponseEntity.ok(equipes);
    }

    @PostMapping("/")
    public ResponseEntity<Equipe> criarEquipe(@RequestBody @Valid Equipe equipe) {
        Equipe criarEquipe = equipeService.salvarEquipe(equipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEquipe);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable Long id, @RequestBody @Valid Equipe equipe) {
        Equipe updatedEquipe = equipeService.updateEquipe(id, equipe);
        if (updatedEquipe != null) {
            return ResponseEntity.ok(updatedEquipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Equipe> deleteEquipe(@PathVariable Long id) {
        boolean deleted = equipeService.deleteEquipe(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
