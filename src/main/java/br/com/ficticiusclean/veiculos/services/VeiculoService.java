package br.com.ficticiusclean.veiculos.services;

import br.com.ficticiusclean.veiculos.models.Previsao;
import br.com.ficticiusclean.veiculos.models.Veiculo;
import br.com.ficticiusclean.veiculos.models.VeiculoRanking;

import java.util.List;

public interface VeiculoService{

    Veiculo cadastrar(Veiculo veiculo);

    List<Veiculo> buscarTodos();

    Veiculo buscarPorId(Long id);

    void atualizar(Long id, Veiculo veiculo);

    void deletar(Long id);

    List<VeiculoRanking> previsaoRanking(Previsao previsao);
}
