package br.com.ficticiusclean.veiculos.repositories;

import br.com.ficticiusclean.veiculos.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
