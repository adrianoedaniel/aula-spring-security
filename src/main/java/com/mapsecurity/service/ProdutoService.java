package com.mapsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapsecurity.entities.Produto;
import com.mapsecurity.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }
    //Query Method 
    public List<Produto> buscarProdutosPorDescricao(String descricao) {
        return produtoRepository.findByDescricao(descricao);
      }
    //@query
    public List<Produto> buscarPorPreco(Double preco) {
        return produtoRepository.findByPreco(preco);
    }
  
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Long id, Produto updatedProduto) {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isPresent()) {
            updatedProduto.setId(id);
            return produtoRepository.save(updatedProduto);
        }
        return null;
    }

    public boolean deleteProduto(Long id) {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
