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
    public void deveLancarIllegalArgumentExceptionQuandoCnhInvalida() {
        // Testando CNH inválida
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AluguelVeiculos("Pedro da Silva", "123", LocalDate.of(2025, 2, 12), "Fiat Uno", 15.0, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 5), 100L, 200L);
        });
    }
	
	
	
	
}

