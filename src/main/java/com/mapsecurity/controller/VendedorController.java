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

import com.mapsecurity.entities.Vendedor;
import com.mapsecurity.service.VendedorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedor")

public class VendedorController {
    
    private final VendedorService vendedorService;
    
    @Autowired
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }
    //Query Method
    @GetMapping("/nome/{nome}")
    @Operation(summary = "Localiza o vendedor por nome")
    public ResponseEntity<List<Vendedor>> buscarVendedorPorNome(@PathVariable String nome) {
      List<Vendedor> vendedors = vendedorService.buscarVendedorPorNome(nome);
      return ResponseEntity.ok(vendedors);
    }
    
    //@query
    @GetMapping("/salario/{salario}")
    @Operation(summary = "Localiza o vendedor por salario")
    public List<Vendedor> findVendedorPorSalario(@PathVariable Double salario) {
        return vendedorService.buscarPorSalario(salario);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.getVendedorById(id);
        if (vendedor != null) {
            return ResponseEntity.ok(vendedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Vendedor>> getAllVendedors() {
        List<Vendedor> vendedors = vendedorService.getAllVendedors();
        return ResponseEntity.ok(vendedors);
    }

    @PostMapping("/")
    public ResponseEntity<Vendedor> criarVendedor(@RequestBody @Valid Vendedor vendedor) {
        Vendedor criarVendedor = vendedorService.salvarVendedor(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarVendedor);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id, @RequestBody @Valid Vendedor vendedor) {
        Vendedor updatedVendedor = vendedorService.updateVendedor(id, vendedor);
        if (updatedVendedor != null) {
            return ResponseEntity.ok(updatedVendedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vendedor> deleteVendedor(@PathVariable Long id) {
        boolean deleted = vendedorService.deleteVendedor(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
