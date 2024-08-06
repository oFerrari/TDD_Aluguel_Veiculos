package entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AluguelVeiculos {
	  private String nomeCliente;
	    private LocalDate dataVencimentoCnh;
	    private String veiculo;
	    private Double valorAluguel;
	    private LocalDate dataRetirada;
	    private LocalDate dataDevolucao;
	    private Long kmInicial;
	    private Long kmFinal;
	        
	    public AluguelVeiculos() {
	    	
		}

		public AluguelVeiculos(String nomeCliente, LocalDate dataVencimentoCnh, String veiculo, Double valorAluguel,
				LocalDate dataRetirada, LocalDate dataDevolucao, Long kmInicial, Long kmFinal) {
			if (dataVencimentoCnh.isBefore(LocalDate.now())) {
	            throw new IllegalArgumentException("A CNH está vencida.");
	        }
			this.nomeCliente = nomeCliente;
			this.dataVencimentoCnh = dataVencimentoCnh;
			this.veiculo = veiculo;
			this.valorAluguel = valorAluguel;
			this.dataRetirada = dataRetirada;
			this.dataDevolucao = dataDevolucao;
			this.kmInicial = kmInicial;
			this.kmFinal = kmFinal;
		}
		
		public void retirarVeiculo(LocalDate dataRetirada, Long kmInicial) {
		    if (dataRetirada == null || kmInicial == null || kmInicial < 0 || !dataRetirada.equals(LocalDate.now())) {
		        throw new IllegalArgumentException("Dados de retirada inválidos ou data não é a data atual");
		    }
		    this.dataRetirada = dataRetirada;
		    this.kmInicial = kmInicial;
		}


	    
	    public void devolverVeiculo(LocalDate dataDevolucao, Long kmFinal) {
	        if (dataDevolucao == null || kmFinal == null || kmFinal < kmInicial) {
	            throw new IllegalArgumentException("Dados de devolução inválidos");
	        }
	        this.dataDevolucao = dataDevolucao;
	        this.kmFinal = kmFinal;
	        
	        calcularValorAluguel();
	    }
    
	    public void calcularValorAluguel() {
	    	if (this.kmFinal != null && this.kmInicial != null) {
	    		long kmContratado = this.kmFinal - this.kmInicial;
	    		this.valorAluguel = kmContratado * 2.0;
	    		
	    		long kmExcedido = (this.kmFinal - this.kmInicial) - (this.kmFinal - this.kmInicial);
	    		if (kmExcedido > 0) {
	    			this.valorAluguel += kmExcedido * 2.0 * 0.2;
	    		}
	    	}
	    }
	    
	    
	    private void calcularValorAtraso() {
	        if (dataRetirada != null && dataDevolucao != null && dataDevolucao.isAfter(dataRetirada)) {
	            long diasAtraso = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
	            double valorDiario = this.valorAluguel / (this.kmFinal - this.kmInicial);
	            double acrescimoAtraso = valorDiario * 0.05 * diasAtraso;
	            this.valorAluguel += acrescimoAtraso;
	        }
	    }

		public String getNomeCliente() {
	        return nomeCliente;
	    }

	    public void setNomeCliente(String nomeCliente) {
	        this.nomeCliente = nomeCliente;
	    }

	    public LocalDate getdataVencimentoCnh() {
	        return dataVencimentoCnh;
	    }

	    public void setdataVencimentoCnh(LocalDate dataVencimentoCnh) {
	        this.dataVencimentoCnh = dataVencimentoCnh;
	    }

	    public String getVeiculo() {
	        return veiculo;
	    }

	    public void setVeiculo(String veiculo) {
	        this.veiculo = veiculo;
	    }

	    public Double getValorAluguel() {
	        return valorAluguel;
	    }

	    public void setValorAluguel(Double valorAluguel) {
	        this.valorAluguel = valorAluguel;
	    }

	    public LocalDate getDataRetirada() {
	        return dataRetirada;
	    }

	    public void setDataRetirada(LocalDate dataRetirada) {
	        this.dataRetirada = dataRetirada;
	    }

	    public LocalDate getDataDevolucao() {
	        return dataDevolucao;
	    }

	    public void setDataDevolucao(LocalDate dataDevolucao) {
	        this.dataDevolucao = dataDevolucao;
	    }

	    public Long getKmInicial() {
	        return kmInicial;
	    }

	    public void setKmInicial(Long kmInicial) {
	        this.kmInicial = kmInicial;
	    }

	    public Long getKmFinal() {
	        return kmFinal;
	    }

	    public void setKmFinal(Long kmFinal) {
	        this.kmFinal = kmFinal;
	    }

	    public void aluguelRetirada(LocalDate dataRetirada, Long kmInicial) {
	        this.dataRetirada = dataRetirada;
	        this.kmInicial = kmInicial;
	    }

	    public void aluguelDevolucao(LocalDate dataDevolucao, Long kmFinal) {
	        this.dataDevolucao = dataDevolucao;
	        this.kmFinal = kmFinal;
	    }
	}
