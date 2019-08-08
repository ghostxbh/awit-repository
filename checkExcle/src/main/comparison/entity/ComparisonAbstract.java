package main.comparison.entity;

import java.util.Date;

public class ComparisonAbstract {

	private Integer id;

	private String voucherAbstract;

	private String voucherCode;

	private String serviceName;

	private String debitAccount;

	private String creditAccount;

	private Date createdTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVoucherAbstract() {
		return voucherAbstract;
	}

	public void setVoucherAbstract(String voucherAbstract) {
		this.voucherAbstract = voucherAbstract;
	}

	public String getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "ComparisonAbstract [id=" + id + ", voucherAbstract=" + voucherAbstract + ", voucherCode=" + voucherCode
				+ ", serviceName=" + serviceName + ", debitAccount=" + debitAccount + ", creditAccount=" + creditAccount
				+ ", createdTime=" + createdTime + "]";
	}

}
