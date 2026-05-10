package br.com.petstock.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petstock.model.Produto;
import br.com.petstock.repository.ProdutoRepository;

/*
 * Service responsável pelas regras de negócio de Produto.
 */
@Service
public class ProdutoService {

    /*
     * Injeta automaticamente o ProdutoRepository.
     */
    @Autowired
    private ProdutoRepository produtoRepository;

    /*
     * Lista todos os produtos cadastrados.
     */
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    /*
     * Salva um produto no banco.
     */
    public Produto salvar(Produto produto) {

        /*
         * Valida o nome do produto.
         */
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome do produto é obrigatório.");
        }

        /*
         * Valida se o preço foi preenchido.
         */
        if (produto.getPreco() == null) {
            throw new RuntimeException("O preço do produto é obrigatório.");
        }

        /*
         * Valida se o preço é maior que zero.
         */
        if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O preço do produto deve ser maior que zero.");
        }

        /*
         * Valida se existe estoque.
         */
        if (produto.getEstoque() == null) {
            throw new RuntimeException("O estoque do produto é obrigatório.");
        }

        /*
         * Valida se a quantidade em estoque é negativa.
         */
        if (produto.getEstoque().getQuantidadeAtual() < 0) {
            throw new RuntimeException("A quantidade em estoque não pode ser negativa.");
        }

        /*
         * Relaciona o estoque ao produto antes de salvar.
         */
        produto.getEstoque().setProduto(produto);

        /*
         * Salva o produto no banco.
         */
        return produtoRepository.save(produto);
    }

    /*
     * Exclui produto pelo ID.
     */
    public void excluir(int id) {
        produtoRepository.deleteById(id);
    }
}