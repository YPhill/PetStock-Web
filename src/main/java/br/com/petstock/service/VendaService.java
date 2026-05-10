package br.com.petstock.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petstock.model.ItensVenda;
import br.com.petstock.model.Produto;
import br.com.petstock.model.Venda;
import br.com.petstock.repository.ProdutoRepository;
import br.com.petstock.repository.VendaRepository;

/*
 * Service responsável pelas regras de negócio de Venda.
 */
@Service
public class VendaService {

    /*
     * Injeta automaticamente o VendaRepository.
     */
    @Autowired
    private VendaRepository vendaRepository;

    /*
     * Injeta automaticamente o ProdutoRepository.
     */
    @Autowired
    private ProdutoRepository produtoRepository;

    /*
     * Lista todas as vendas cadastradas.
     */
    public List<Venda> listarTodas() {

        return vendaRepository.findAll();
    }

    /*
     * Salva uma venda no banco.
     */
    public Venda salvar(Venda venda) {

        /*
         * Verifica se existem itens na venda.
         */
        if (venda.getItens() == null || venda.getItens().isEmpty()) {

            throw new RuntimeException("A venda precisa possuir ao menos um item.");
        }

        /*
         * Define automaticamente a data da venda.
         */
        venda.setDataVenda(LocalDate.now());

        /*
         * Variável usada para calcular o valor total.
         */
        BigDecimal total = BigDecimal.ZERO;

        /*
         * Percorre todos os itens da venda.
         */
        for (ItensVenda item : venda.getItens()) {

            /*
             * Verifica se existe produto.
             */
            if (item.getProduto() == null) {

                throw new RuntimeException("Todos os itens precisam possuir um produto.");
            }

            /*
             * Verifica quantidade inválida.
             */
            if (item.getQuantidade() <= 0) {

                throw new RuntimeException("A quantidade do item deve ser maior que zero.");
            }

            /*
             * Busca o produto atualizado no banco.
             */
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

            /*
             * Verifica se existe estoque suficiente.
             */
            if (produto.getEstoque().getQuantidadeAtual() < item.getQuantidade()) {

                throw new RuntimeException(
                        "Estoque insuficiente para o produto: " + produto.getNome());
            }

            /*
             * Atualiza o preço unitário do item.
             */
            item.setPrecoUnitario(produto.getPreco());

            /*
             * Calcula subtotal do item.
             */
            BigDecimal subtotal = produto.getPreco()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));

            /*
             * Soma subtotal ao total da venda.
             */
            total = total.add(subtotal);

            /*
             * Remove produtos do estoque.
             */
            produto.getEstoque().setQuantidadeAtual(
                    produto.getEstoque().getQuantidadeAtual() - item.getQuantidade());

            /*
             * Relaciona item à venda.
             */
            item.setVenda(venda);
        }

        /*
         * Define valor total da venda.
         */
        venda.setValorTotal(total);

        /*
         * Salva a venda no banco.
         */
        return vendaRepository.save(venda);
    }

    /*
     * Exclui venda pelo ID.
     */
    public void excluir(int id) {

        vendaRepository.deleteById(id);
    }
}