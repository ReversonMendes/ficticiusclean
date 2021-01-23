package br.com.ficticiusclean.veiculos.services;

import br.com.ficticiusclean.veiculos.exceptions.idNaoExiste;
import br.com.ficticiusclean.veiculos.models.Previsao;
import br.com.ficticiusclean.veiculos.models.Veiculo;
import br.com.ficticiusclean.veiculos.models.VeiculoRanking;
import br.com.ficticiusclean.veiculos.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public Veiculo cadastrar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @Override
    public List<Veiculo> buscarTodos() {
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(idNaoExiste::new);
    }

    @Override
    public void atualizar(Long id, Veiculo veiculo) {
        buscarPorId(id);
        veiculo.setId(id);
        veiculoRepository.save(veiculo);
    }

    @Override
    public void deletar(Long id) {
        buscarPorId(id);
        veiculoRepository.deleteById(id);
    }

    @Override
    public List<VeiculoRanking> previsaoRanking(Previsao previsao) {

        List<VeiculoRanking> veiculosRanqueados = new ArrayList<>();
        List<Veiculo> veiculos = buscarTodos();

        for (Veiculo veiculo : veiculos) {

            VeiculoRanking veiculoRanking = new VeiculoRanking();

            veiculoRanking.setNome(veiculo.getNome());
            veiculoRanking.setMarca(veiculo.getMarca());
            veiculoRanking.setModelo(veiculo.getModelo());

            LocalDate dateFab = LocalDate.ofInstant(veiculo.getDataFabricacao().toInstant(), ZoneId.systemDefault());
            veiculoRanking.setAno(dateFab.getYear());

            BigDecimal qtdGastoCidade = calculaGastoCombustivel(previsao.getKmCidade(), veiculo.getConsumoCidade());
            BigDecimal qtdGastoRodovia = calculaGastoCombustivel(previsao.getKmRodovia(), veiculo.getConsumoRodovia());

            veiculoRanking.setQtdCombustivelGasto(qtdGastoCidade.add(qtdGastoRodovia).setScale(2, RoundingMode.CEILING));

            veiculoRanking.setValorTotalGasto(calculaValorPrevisto(qtdGastoCidade, qtdGastoRodovia, previsao.getPrecoGasolina()));

            veiculosRanqueados.add(veiculoRanking);
        }

        //Ordena por valor total do gasto de combustível
        veiculosRanqueados.sort(Comparator.comparing(VeiculoRanking::getValorTotalGasto));

        return veiculosRanqueados;
    }

    private BigDecimal calculaGastoCombustivel(BigDecimal km, BigDecimal mediaVeiculo) {

        if (mediaVeiculo.doubleValue() > 0 && km != null) {
            BigDecimal valorPrevisto = km.divide(mediaVeiculo, MathContext.DECIMAL32);
            return valorPrevisto;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal calculaValorPrevisto(BigDecimal qtdCidade, BigDecimal qtdRodovia, BigDecimal precoGasolina) {

        return qtdCidade.multiply(precoGasolina).add(qtdRodovia.multiply(precoGasolina)).setScale(2, RoundingMode.UP);
    }
}
