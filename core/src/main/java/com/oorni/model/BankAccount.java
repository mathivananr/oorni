package com.oorni.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This class is used to represent an address with address,
 * city, province and postal-code information.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Embeddable
public class BankAccount extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3617859655330969141L;
    
    private String bankName;
    private String accountNo;
    private String accountName;
    private String branch;
    private String ifscCode;
    private String city;
    private String country;
    
    public BankAccount() {
		super();
	}
	

	@Column(name = "bank_name")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "account_no")
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "account_name")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "branch")
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(name = "ifsc_code")
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
     * Overridden equals method for object comparison. Compares based on hashCode.
     *
     * @param o Object to compare
     * @return true/false based on hashCode
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }

        final BankAccount account1 = (BankAccount) o;

        return this.hashCode() == account1.hashCode();
    }

    /**
     * Overridden hashCode method - compares on address, city, province, country and postal code.
     *
     * @return hashCode
     */
    public int hashCode() {
        int result;
        result = (accountNo != null ? accountNo.hashCode() : 0);
        return result;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("accountNo", this.accountNo).toString();
    }
}
