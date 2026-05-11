package br.com.petstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petstock.model.Cliente;
import br.com.petstock.repository.ClienteRepository;

/*
 * Service responsável pelas regras de negócio de Cliente.
 */
@Service
public class ClienteService {

    /*
     * Injeta automaticamente o ClienteRepository.
     */
    @Autowired
    private ClienteRepository clienteRepository;

    /*
     * Lista todos os clientes cadastrados.
     */
    public List<Cliente> listarTodos() {

        return clienteRepository.findAll();
    }

    /*
     * Salva um novo cliente.
     */
    public Cliente salvar(Cliente cliente) {

        // =========================
        // VALIDAÇÕES
        // =========================

        /*
         * Verifica se o nome foi preenchido.
         */
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {

            throw new RuntimeException("O nome do cliente é obrigatório.");
        }

        /*
         * Verifica se o endereço existe.
         */
        if (cliente.getEndereco() == null) {

            throw new RuntimeException("O endereço do cliente é obrigatório.");
        }

        /*
         * Verifica se a rua foi preenchida.
         */
        if (cliente.getEndereco().getRua() == null
                || cliente.getEndereco().getRua().trim().isEmpty()) {

            throw new RuntimeException("A rua é obrigatória.");
        }

        /*
         * Verifica se a cidade foi preenchida.
         */
        if (cliente.getEndereco().getCidade() == null
                || cliente.getEndereco().getCidade().trim().isEmpty()) {

            throw new RuntimeException("A cidade é obrigatória.");
        }

        /*
         * Verifica se o estado foi preenchido.
         */
        if (cliente.getEndereco().getEstado() == null
                || cliente.getEndereco().getEstado().trim().isEmpty()) {

            throw new RuntimeException("O estado é obrigatório.");
        }

        /*
         * Verifica se o CEP foi preenchido.
         */
        if (cliente.getEndereco().getCep() == null
                || cliente.getEndereco().getCep().trim().isEmpty()) {

            throw new RuntimeException("O CEP é obrigatório.");
        }

        /*
         * Relaciona contato ao cliente antes de salvar.
         */
        if (cliente.getContato() != null) {

            cliente.getContato().setCliente(cliente);
        }

        /*
         * Salva o cliente no banco.
         */
        return clienteRepository.save(cliente);
    }

    /*
     * Exclui um cliente pelo ID.
     */
    public void excluir(int id) {

        clienteRepository.deleteById(id);
    }
    
    /*
     * Busca um cliente pelo ID.
     * Usado para carregar os dados na tela de edição.
     */
    public Cliente buscarPorId(int id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    /*
     * Exclui vários clientes selecionados.
     * Usado quando o usuário marca várias linhas na tabela.
     */
    public void excluirSelecionados(List<Integer> ids) {

        clienteRepository.deleteAllById(ids);
    }
}