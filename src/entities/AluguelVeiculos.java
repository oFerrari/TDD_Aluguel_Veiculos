package entities;

import java.time.LocalDate;
import java.time.Period;

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
			this.nomeCliente = nomeCliente;
			this.dataVencimentoCnh = dataVencimentoCnh;
			this.veiculo = veiculo;
			this.valorAluguel = valorAluguel;
			this.dataRetirada = dataRetirada;
			this.dataDevolucao = dataDevolucao;
			this.kmInicial = kmInicial;
			this.kmFinal = kmFinal;
		}
	    private boolean isValidCNH(String cnh) {
	        return cnh != null && cnh.matches("\\d{11}");
	    }

	    private void validateCurrentDate(LocalDate date) {
	        if (date == null || !date.equals(LocalDate.now())) {
	            throw new IllegalArgumentException("Data fornecida não é a data atual");
	        }
	    }

	    public void retirarVeiculo(LocalDate dataRetirada, Long kmInicial) {
	        validateCurrentDate(dataRetirada);
	        if (dataRetirada == null || kmInicial == null || kmInicial < 0) {
	            throw new IllegalArgumentException("Dados de retirada inválidos");
	        }
	        this.dataRetirada = dataRetirada;
	        this.kmInicial = kmInicial;
	        long kmContratado = kmFinal - kmInicial;
	        this.valorAluguel = kmContratado * 2.0;
	    }

	    public void devolverVeiculo(LocalDate dataDevolucao, Long kmFinal) {
	        if (dataDevolucao == null || kmFinal == null || kmFinal < kmInicial) {
	            throw new IllegalArgumentException("Dados de devolução inválidos");
	        }

	        if (dataDevolucao.isAfter(dataVencimentoCnh)) {
	            long diasAtraso = Period.between(dataVencimentoCnh, dataDevolucao).getDays();
	            this.valorAluguel += diasAtraso * (valorAluguel * 0.05); 
	            }

	        long kmContratado = kmFinal - kmInicial;
	        if (kmFinal > this.kmFinal) {
	            this.valorAluguel += (kmFinal - kmContratado) * 0.2;
	        }

	        this.dataDevolucao = dataDevolucao;
	        this.kmFinal = kmFinal;
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
