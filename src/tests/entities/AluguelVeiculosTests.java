package tests.entities;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.AluguelVeiculos;

public class AluguelVeiculosTests {
	
	@Test
	public void vendaDeIngressoDeveriaCriarObjetoComDadosCorretosQuandoDadosValidos(){
		
		AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno",15.0, LocalDate.of(2025, 2, 1),LocalDate.of(2025, 2, 5),100L, 200L);
		Assertions.assertTrue(vi.equals(vi));
	}
	
	 @Test
	    public void deveriaLancarExcecaoQuandoCnhInvalida(){
	        Assertions.assertThrows(IllegalArgumentException.class, () -> {
	            new AluguelVeiculos("Pedro da Silva", LocalDate.of(2020, 2, 12), "Fiat Uno", 15.0, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 5), 100L, 200L);
	        });
	  }
	 
	 @Test
	 public void deveriaRealizarRetiradaQuandoDadosValidos() {
	     AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno", 0.0, LocalDate.now(), LocalDate.of(2025, 2, 5), 0L, 0L);
	     vi.retirarVeiculo(LocalDate.now(), 100L);
	     Assertions.assertEquals(LocalDate.now(), vi.getDataRetirada());
	     Assertions.assertEquals(100L, vi.getKmInicial());
	 }


	    @Test
	    public void deveriaCalcularValorAluguelCorretamente() {
	        AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno", 0.0, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 5), 100L, 200L);
	        vi.calcularValorAluguel();
	        Assertions.assertEquals(200.0, vi.getValorAluguel());
	    }
	    
	    @Test
	    public void deveriaLancarExcecaoQuandoDataRetiradaNaoForAtual() {
	        AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno", 0.0, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 5), 0L, 0L);
	        Assertions.assertThrows(IllegalArgumentException.class, () -> {
	            vi.retirarVeiculo(LocalDate.of(2025, 1, 1), 100L);
	        });
	    }
	    
	    
	    @Test
	    public void deveriaRealizarDevolucaoQuandoDadosValidos() {
	        AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno", 0.0, LocalDate.now(), null, 100L, 0L);
	        vi.retirarVeiculo(LocalDate.now(), 100L);
	        vi.devolverVeiculo(LocalDate.of(2025, 2, 5), 200L);
	        Assertions.assertEquals(LocalDate.of(2025, 2, 5), vi.getDataDevolucao());
	        Assertions.assertEquals(200L, vi.getKmFinal());
	        Assertions.assertEquals(200.0, vi.getValorAluguel());
	    }


	    @Test
	    public void deveriaAcrescentar20PorCentoQuandoKmFinalMaiorQueKmInicial() {
	        AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno", 0.0, LocalDate.now(), null, 100L, 200L);
	        vi.retirarVeiculo(LocalDate.now(), 100L);
	        vi.devolverVeiculo(LocalDate.of(2025, 2, 5), 250L); // kmFinal > kmInicial + kmContratado

	        Assertions.assertEquals(LocalDate.of(2025, 2, 5), vi.getDataDevolucao());
	        Assertions.assertEquals(250L, vi.getKmFinal());
	        double valorEsperado = (200 - 100) * 2.0 + (50 * 2.0 * 0.2); // 100 km contratados e 50 km excedidos
	        Assertions.assertEquals(valorEsperado, vi.getValorAluguel());
	    }



	    @Test
	    public void deveriaAcrescentar5PorCentoPorDiaDeAtraso() {
	        AluguelVeiculos vi = new AluguelVeiculos("Pedro da Silva", LocalDate.of(2025, 2, 12), "Fiat Uno", 0.0, LocalDate.now(), null, 100L, 200L);
	        vi.retirarVeiculo(LocalDate.now(), 100L);
	        vi.devolverVeiculo(LocalDate.of(2025, 2, 10), 250L);
	        Assertions.assertEquals(LocalDate.of(2025, 2, 10), vi.getDataDevolucao());
	        Assertions.assertEquals(250L, vi.getKmFinal());
	        double valorBase = (150 * 2.0) + (50 * 2.0 * 0.2);
	        double valorDiario = (150 * 2.0) / 150;
	        double acrescimoAtraso = valorDiario * 0.05 * 5; 
	        double valorEsperado = valorBase + acrescimoAtraso;
	        Assertions.assertEquals(valorEsperado, vi.getValorAluguel(), 0.01);
	    }
	
	
}

