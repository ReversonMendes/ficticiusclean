package br.com.ficticiusclean.veiculos.controllers;

import br.com.ficticiusclean.veiculos.models.Previsao;
import br.com.ficticiusclean.veiculos.models.Veiculo;
import br.com.ficticiusclean.veiculos.models.VeiculoRanking;
import br.com.ficticiusclean.veiculos.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping(value = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@RequestBody @Valid Veiculo veiculo){
        return veiculoService.cadastrar(veiculo);
    }

    @GetMapping()
    public List<Veiculo> listarVeiculos(){
        return veiculoService.buscarTodos();
    }

    @GetMapping("{id}")
    public Veiculo buscarPorId(@PathVariable Long id){
        return veiculoService.buscarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Veiculo veiculo){
        veiculoService.atualizar(id, veiculo);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        veiculoService.deletar(id);
    }

    @PostMapping(value = "/ranking")
    public List<VeiculoRanking> ranking(@RequestBody Previsao previsao){
        return veiculoService.previsaoRanking(previsao);
    }


}
