package main.comparison.entity;

import java.util.Date;
public class ComparisonAccount {

	private Integer id;

	private String accountCode;

	private String accountName;

	private String fullName;

	private String accountType;

	private String accountDirect;

	private Integer accountParentId;

	private Date createdTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountDirect() {
		return accountDirect;
	}

	public void setAccountDirect(String accountDirect) {
		this.accountDirect = accountDirect;
	}

	public Integer getAccountParentId() {
		return accountParentId;
	}

	public void setAccountParentId(Integer accountParentId) {
		this.accountParentId = accountParentId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public ComparisonAccount() {
		super();
	}

	/**
	 * 构造器对象
	 * 
	 * @param id
	 * @param accountCode
	 * @param accountName
	 * @param fullName
	 * @param accountType
	 * @param accountDirect
	 * @param accountParentId
	 */
	public ComparisonAccount(String accountCode, String accountName, String fullName, String accountType,
			String accountDirect, Integer accountParentId) {
		super();
		this.accountCode = accountCode;
		this.accountName = accountName;
		this.fullName = fullName;
		this.accountType = accountType;
		this.accountDirect = accountDirect;
		this.accountParentId = accountParentId;
	}

	@Override
	public String toString() {
		return "ComparisonAccount [id=" + id + ", accountCode=" + accountCode + ", accountName=" + accountName
				+ ", fullName=" + fullName + ", accountType=" + accountType + ", accountDirect=" + accountDirect
				+ ", accountParentId=" + accountParentId + "]";
	}

}
