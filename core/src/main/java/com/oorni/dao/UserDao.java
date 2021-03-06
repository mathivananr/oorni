package com.oorni.dao;

import com.oorni.common.OorniException;
import com.oorni.model.BankAccount;
import com.oorni.model.OnlineWallet;
import com.oorni.model.Payment;
import com.oorni.model.User;

import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Gets users information based on login name.
     * @param username the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
     * found in database
     */
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Gets users information based on login name and password.
     * @param username the user's username
     * @param password the user's password
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
     * found in database
     */
    @Transactional
    UserDetails loadUser(String username, String password)
			throws UsernameNotFoundException;
			
    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List populated list of users
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     * @param user the object to be saved
     * @return the persisted User object
     */
    User saveUser(User user);

    /**
     * Retrieves the password in DB for a user
     * @param userId the user's id
     * @return the password in DB, if the user is already persisted
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword(Long userId);
    
    /**
     * 
     */
    List<User> getUserSuggestion(String name)
			throws OorniException;	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	List<Map<String, String>> getUserSuggestionByStoreName(String name)
			throws OorniException;
	
	/*BankAccount saveBankDetails(BankAccount bankAccount) throws OorniException;
	
	OnlineWallet saveWalletDetails(OnlineWallet onlineWallet) throws OorniException;
	
	BankAccount getBankDetail(Long accountId) throws OorniException;
	
	OnlineWallet getWalletDetail(Long walletId) throws OorniException;
	
	BankAccount getBankDetail(Long accountId, Long ownerId) throws OorniException;
	
	OnlineWallet getWalletDetail(Long walletId, Long ownerId) throws OorniException;*/
	
	Payment getPaymentRequest(Long paymentId) throws OorniException;
	
	Payment getPaymentRequest(Long paymentId, String username) throws OorniException;
	
	Payment savePayment(Payment payment) throws OorniException;
	
	List<Payment> getAllPayments();
	
	List<Payment> getPayments(String username);
}
