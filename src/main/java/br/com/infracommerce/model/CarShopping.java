package br.com.infracommerce.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="infra_car_shopping")
public class CarShopping implements Serializable {
	
	private static final long serialVersionUID = 5523588493773369699L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="TRANSACAO", nullable=false)
	private long transactionId;
	
	@ManyToOne
	private Product product;
	
	@NotNull
	@Column(name="QUANTIDADE", nullable=false)
	private int quantidade;
	
	@Column(name="DATA_COMPRA", nullable=false)
	private Calendar dataCompra;
	
	@Column(name="VALOR_TOTAL", nullable=false)
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Calendar getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CarShopping [id=" + id + ", transactionId=" + transactionId + ", product=" + product + ", quantidade="
				+ quantidade + ", dataCompra=" + dataCompra + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCompra == null) ? 0 : dataCompra.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantidade;
		result = prime * result + (int) (transactionId ^ (transactionId >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarShopping other = (CarShopping) obj;
		if (dataCompra == null) {
			if (other.dataCompra != null)
				return false;
		} else if (!dataCompra.equals(other.dataCompra))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	
}
