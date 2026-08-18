package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {
    boolean existsByChassi(String chassi);
    boolean existsByPlaca(String placa);
    Optional<Carro> findByChassi(String chassi);
    Optional<Carro> findByPlaca(String placa);

    List<Carro> findByCor(String cor);
    List<Carro> findBydtFabricacao(String dtFabricacao);
    List<Carro> findByCorAndDtFabricacao(String cor, String dtFabricacao);
}
