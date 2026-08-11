package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public Carro cadastroCarro(@RequestBody Carro carro){ return carroRepository.save(carro); }

    @GetMapping("/{id}")
    public Carro mostrarCarro(@PathVariable("id") Integer id){ return carroRepository.findById(id).orElse(null); }

    @GetMapping
    public List<Carro> todosCarros(){ return carroRepository.findAll(); }

    @PutMapping("/{id}")
    public Carro atualizarCarro(@PathVariable("id") Integer id, @RequestBody Carro carro){
        carro.setId(id);
        return carroRepository.save(carro);
    }

    @DeleteMapping
    public void deletarCarro(@PathVariable("id") Integer id){
        carroRepository.deleteById(id);
    }
}
