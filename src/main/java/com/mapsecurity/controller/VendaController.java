package com.mapsecurity.controller;

import java.sql.Date;
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

import com.mapsecurity.entities.Venda;
import com.mapsecurity.service.VendaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/venda")

public class VendaController {
    
    private final VendaService vendaService;
    
    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }
    //Query Method
    @GetMapping("/data/{data}")
    @Operation(summary = "Localiza o venda por data")
    public ResponseEntity<List<Venda>> buscarVendasPorData(@PathVariable Date data) {
      List<Venda> vendas = vendaService.buscarVendasPorData(data);
      return ResponseEntity.ok(vendas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
        Venda venda = vendaService.getVendaById(id);
        if (venda != null) {
            return ResponseEntity.ok(venda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Venda>> getAllVendas() {
        List<Venda> vendas = vendaService.getAllVendas();
        return ResponseEntity.ok(vendas);
    }

    @PostMapping("/")
    public ResponseEntity<Venda> criarVenda(@RequestBody @Valid Venda venda) {
        Venda criarVenda = vendaService.salvarVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarVenda);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id, @RequestBody @Valid Venda venda) {
        Venda updatedVenda = vendaService.updateVenda(id, venda);
        if (updatedVenda != null) {
            return ResponseEntity.ok(updatedVenda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Venda> deleteVenda(@PathVariable Long id) {
        boolean deleted = vendaService.deleteVenda(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
