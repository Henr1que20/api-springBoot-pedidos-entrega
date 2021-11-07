package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exceptions.NegocioExeception;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findAllById(clienteId)
                .orElseThrow(() -> new NegocioExeception("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente){
        boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(email -> !email.equals(cliente));

        if (emailExistente){
            throw new NegocioExeception("Ja existe um cliente cadastrado com esse email!!");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.findAllById(clienteId);
    }

}
