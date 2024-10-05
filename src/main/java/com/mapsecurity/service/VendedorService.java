package com.mapsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapsecurity.entities.Vendedor;
import com.mapsecurity.repository.VendedorRepository;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;
    
    @Autowired
    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<Vendedor> getAllVendedors() {
        return vendedorRepository.findAll();
    }

    public Vendedor getVendedorById(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor.orElse(null);
    }
    //Query Method 
    public List<Vendedor> buscarVendedorPorNome(String nome) {
        return vendedorRepository.findByNome(nome);
      }
    //@query
    public List<Vendedor> buscarPorSalario(Double salario) {
        return vendedorRepository.findBySalario(salario);
    }
  
    public Vendedor salvarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor updateVendedor(Long id, Vendedor updatedVendedor) {
        Optional<Vendedor> existingVendedor = vendedorRepository.findById(id);
        if (existingVendedor.isPresent()) {
            updatedVendedor.setId(id);
            return vendedorRepository.save(updatedVendedor);
        }
        return null;
    }

    public boolean deleteVendedor(Long id) {
        Optional<Vendedor> existingVendedor = vendedorRepository.findById(id);
        if (existingVendedor.isPresent()) {
            vendedorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
