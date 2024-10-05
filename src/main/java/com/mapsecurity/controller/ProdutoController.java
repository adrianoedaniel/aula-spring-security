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

import com.mapsecurity.entities.Produto;
import com.mapsecurity.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")

public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    //Query Method
    @GetMapping("/descricao/{descricao}")
    @Operation(summary = "Localiza o produto por descrição")
    public ResponseEntity<List<Produto>> buscarProdutosPorDescricao(@PathVariable String descricao) {
      List<Produto> produtos = produtoService.buscarProdutosPorDescricao(descricao);
      return ResponseEntity.ok(produtos);
    }
    
    //@query
    @GetMapping("/preco/{preco}")
    @Operation(summary = "Localiza o produto por preço")
    public List<Produto> findProdutosPorPreco(@PathVariable Double preco) {
        return produtoService.buscarPorPreco(preco);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProductById(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/")
    public ResponseEntity<Produto> criarProduto(@RequestBody @Valid Produto produto) {
        Produto criarProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody @Valid Produto produto) {
        Produto updatedProduto = produtoService.updateProduto(id, produto);
        if (updatedProduto != null) {
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable Long id) {
        boolean deleted = produtoService.deleteProduto(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
