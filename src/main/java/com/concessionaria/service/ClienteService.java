package com.concessionaria.service;
import com.concessionaria.dto.ClienteRequest;
import com.concessionaria.dto.ClienteResponse;
import com.concessionaria.exception.CpfInvalidoException;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponse cadastrar(ClienteRequest dto) {
        validarCpfSoNumeros(dto.cpf());

        if (clienteRepository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException("Já existe um cliente cadastrado com esse CPF");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        return toDto(clienteRepository.save(cliente));
    }

    public ClienteResponse buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return toDto(cliente);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ClienteResponse atualizar(Integer id, ClienteRequest clienteRequest) {
        validarCpfSoNumeros(clienteRequest.cpf());

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        clienteRepository.findByCpf(clienteRequest.cpf())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new RegistroDuplicadoException("Já existe um cliente cadastrado com esse CPF");
                });

        cliente.setNome(clienteRequest.nome());
        cliente.setCpf(clienteRequest.cpf());
        cliente.setTelefone(clienteRequest.telefone());
        cliente.setEmail(clienteRequest.email());

        return toDto(clienteRepository.save(cliente));
    }

    public void deletar(Integer id) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        clienteRepository.deleteById(id);
    }

    private void validarCpfSoNumeros(String cpf) {
        for (char c : cpf.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new CpfInvalidoException("CPF deve conter somente números, sem letras ou símbolos");
            }
        }
    }

    private ClienteResponse toDto(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }
}