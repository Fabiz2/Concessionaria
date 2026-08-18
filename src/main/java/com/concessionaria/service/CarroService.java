package com.concessionaria.service;
import com.concessionaria.dto.CarroRequest;
import com.concessionaria.dto.CarroResponse;

import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroResponse cadastrar(CarroRequest carroRequest) {
        if (carroRepository.existsByChassi(carroRequest.chassi())) {
            throw new RegistroDuplicadoException("Já existe um carro cadastrado com esse chassi");
        }
        if (carroRequest.placa() != null && carroRepository.existsByPlaca(carroRequest.placa())) {
            throw new RegistroDuplicadoException("Já existe um carro cadastrado com essa placa");
        }

        Carro carro = new Carro();
        carro.setModelo(carroRequest.modelo());
        carro.setMarca(carroRequest.marca());
        carro.setDtFabricacao(carroRequest.dtFabricacao());
        carro.setDtModelo(carroRequest.dtModelo());
        carro.setCor(carroRequest.cor());
        carro.setPlaca(carroRequest.placa());
        carro.setChassi(carroRequest.chassi());
        carro.setKilometragem(carroRequest.kilometragem());
        carro.setPreco(carroRequest.preco());
        carro.setStatus(carroRequest.status());
        carro.setCondicao(carroRequest.condicao());

        return toDto(carroRepository.save(carro));
    }

    public CarroResponse buscarPorId(Integer id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));
        return toDto(carro);
    }

    public List<CarroResponse> listarTodos() {
        return carroRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public CarroResponse atualizar(Integer id, CarroRequest carroRequest) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));

        carroRepository.findByChassi(carroRequest.chassi())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new RegistroDuplicadoException("Já existe um carro cadastrado com esse chassi");
                });

        if (carroRequest.placa() != null) {
            carroRepository.findByPlaca(carroRequest.placa())
                    .filter(outro -> !outro.getId().equals(id))
                    .ifPresent(outro -> {
                        throw new RegistroDuplicadoException("Já existe um carro cadastrado com essa placa");
                    });
        }

        carro.setModelo(carroRequest.modelo());
        carro.setMarca(carroRequest.marca());
        carro.setDtFabricacao(carroRequest.dtFabricacao());
        carro.setDtModelo(carroRequest.dtModelo());
        carro.setCor(carroRequest.cor());
        carro.setPlaca(carroRequest.placa());
        carro.setChassi(carroRequest.chassi());
        carro.setKilometragem(carroRequest.kilometragem());
        carro.setPreco(carroRequest.preco());
        carro.setStatus(carroRequest.status());
        carro.setCondicao(carroRequest.condicao());

        return toDto(carroRepository.save(carro));
    }

    public void deletar(Integer id) {
        carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));
        carroRepository.deleteById(id);
    }

    private CarroResponse toDto(Carro carro) {
        return new CarroResponse(
                carro.getId(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getPlaca(),
                carro.getCor(),
                carro.getChassi(),
                carro.getKilometragem(),
                carro.getPreco(),
                carro.getDtModelo(),
                carro.getDtFabricacao(),
                carro.getStatus(),
                carro.getCondicao()
        );
    }

    public List<CarroResponse> buscarComFiltro(String cor, String dtFabricacao) {
        List<Carro> carros;

        if (cor != null && dtFabricacao != null) {
            carros = carroRepository.findByCorAndDtFabricacao(cor, dtFabricacao);
        } else if (cor != null) {
            carros = carroRepository.findByCor(cor);
        } else if (dtFabricacao != null) {
            carros = carroRepository.findBydtFabricacao(dtFabricacao);
        } else {
            carros = carroRepository.findAll();
        }

        return carros.stream()
                .map(this::toDto)
                .toList();
    }
}
