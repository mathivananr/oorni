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
public class OnlineWallet extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3617859655330969141L;
    
    private String walletName;
    private String walletProfileName;
    private String walletMobileNumber;
    private String walletEmail;
    
    public OnlineWallet() {
		super();
	}
	
	@Column(name = "wallet_name")
	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	@Column(name = "profile_name")
	public String getWalletProfileName() {
		return walletProfileName;
	}

	public void setWalletProfileName(String walletProfileName) {
		this.walletProfileName = walletProfileName;
	}

	@Column(name = "mobile_number")
	public String getWalletMobileNumber() {
		return walletMobileNumber;
	}

	public void setWalletMobileNumber(String walletMobileNumber) {
		this.walletMobileNumber = walletMobileNumber;
	}

	@Column(name = "email")
	public String getWalletEmail() {
		return walletEmail;
	}

	public void setWalletEmail(String walletEmail) {
		this.walletEmail = walletEmail;
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
        if (!(o instanceof OnlineWallet)) {
            return false;
        }

        final OnlineWallet wallet1 = (OnlineWallet) o;

        return this.hashCode() == wallet1.hashCode();
    }

    /**
     * Overridden hashCode method - compares on address, city, province, country and postal code.
     *
     * @return hashCode
     */
    public int hashCode() {
        int result;
        result = (walletName != null ? walletName.hashCode() : 0);
        return result;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("walletName", this.walletName).toString();
    }
}
