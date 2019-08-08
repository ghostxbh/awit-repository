package main.comparison.entity;

public class ComparisonAbstractDetail {

	private Integer id;

	private String voucherCode;

	private String companyName;

	private String departmentName;

	private String ticketType;

	private String voucherCount;

	private String priority;

	private Integer checkState;

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVoucherCount() {
		return voucherCount;
	}

	public void setVoucherCount(String voucherCount) {
		this.voucherCount = voucherCount;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "ComparisonAbstractDetail [id=" + id + ", voucherCode=" + voucherCode + ", companyName=" + companyName
				+ ", departmentName=" + departmentName + ", ticketType=" + ticketType + ", voucherCount=" + voucherCount
				+ ", priority=" + priority + ", checkState=" + checkState + "]";
	}

}
