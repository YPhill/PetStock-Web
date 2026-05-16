package br.com.petstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petstock.model.Estoque;
import br.com.petstock.repository.EstoqueRepository;

/*
 * Service responsável pelas regras de negócio do estoque.
 */
@Service
public class EstoqueService {

    /*
     * Injeta automaticamente o EstoqueRepository.
     */
    @Autowired
    private EstoqueRepository estoqueRepository;

    /*
     * Lista todos os registros de estoque.
     */
    public List<Estoque> listarTodos() {

        return estoqueRepository.findAll();
    }

    /*
     * Busca um estoque pelo ID do produto.
     */
    public Estoque buscarPorId(int idProduto) {

        return estoqueRepository.findById(idProduto)
                .orElseThrow(() ->
                        new RuntimeException("Estoque não encontrado."));
    }

    /*
     * Realiza entrada de estoque.
     * Soma a quantidade informada ao estoque atual.
     */
    public void entrada(int idProduto, int quantidade) {

        /*
         * Valida quantidade inválida.
         */
        if (quantidade <= 0) {
            throw new RuntimeException(
                    "A quantidade de entrada deve ser maior que zero.");
        }

        /*
         * Busca estoque atual.
         */
        Estoque estoque = buscarPorId(idProduto);

        /*
         * Soma quantidade ao estoque.
         */
        estoque.setQuantidadeAtual(
                estoque.getQuantidadeAtual() + quantidade);

        /*
         * Salva atualização.
         */
        estoqueRepository.save(estoque);
    }

    /*
     * Realiza saída de estoque.
     * Subtrai a quantidade do estoque atual.
     */
    public void saida(int idProduto, int quantidade) {

        /*
         * Valida quantidade inválida.
         */
        if (quantidade <= 0) {
            throw new RuntimeException(
                    "A quantidade de saída deve ser maior que zero.");
        }

        /*
         * Busca estoque atual.
         */
        Estoque estoque = buscarPorId(idProduto);

        /*
         * Valida estoque negativo.
         */
        if (estoque.getQuantidadeAtual() - quantidade < 0) {

            throw new RuntimeException(
                    "O estoque não pode ficar negativo.");
        }

        /*
         * Subtrai quantidade do estoque.
         */
        estoque.setQuantidadeAtual(
                estoque.getQuantidadeAtual() - quantidade);

        /*
         * Salva atualização.
         */
        estoqueRepository.save(estoque);
    }

    /*
     * Ajusta diretamente a quantidade do estoque.
     * Muito usado em inventário.
     */
    public void ajustar(int idProduto, int novaQuantidade) {

        /*
         * Valida quantidade inválida.
         */
        if (novaQuantidade < 0) {

            throw new RuntimeException(
                    "A quantidade não pode ser negativa.");
        }

        /*
         * Busca estoque atual.
         */
        Estoque estoque = buscarPorId(idProduto);

        /*
         * Atualiza quantidade.
         */
        estoque.setQuantidadeAtual(novaQuantidade);

        /*
         * Salva atualização.
         */
        estoqueRepository.save(estoque);
    }
}