package com.concessionaria.controller;

import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente cliente){ return clienteRepository.save(cliente); }

    @GetMapping("/{id}")
    public Cliente mostrarCliente(@PathVariable("id") Integer id){ return clienteRepository.findById(id).orElse(null); }

    @GetMapping
    public List<Cliente> todosClientes(){ return clienteRepository.findAll(); }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping
    public void deletarCliente(@PathVariable("id") Integer id){
        clienteRepository.deleteById(id);
    }
}
