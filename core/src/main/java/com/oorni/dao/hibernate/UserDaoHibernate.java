package com.oorni.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.dao.UserDao;
import com.oorni.model.BankAccount;
import com.oorni.model.Merchant;
import com.oorni.model.OnlineWallet;
import com.oorni.model.Payment;
import com.oorni.model.User;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *         Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *         Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with
 *         the new BaseDaoHibernate implementation that uses generics.
 *         Modified by jgarcia (updated to hibernate 4)
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        getSession().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (UserDetails) users.get(0);
        }
    }

	/**
	 * {@inheritDoc}
	 */
	public UserDetails loadUser(String username, String password)
			throws UsernameNotFoundException {
		List users = getSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).list();
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("user '" + username
					+ "' not found...");
		} else {
			return (UserDetails) users.get(0);
		}
	}
    
    /**
     * {@inheritDoc}
     */
    public String getUserPassword(Long userId) {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where id=?", String.class, userId);
    }
    
    /**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getUserSuggestion(String name)
			throws OorniException {
		List<User> users = getSession()
				.createCriteria(User.class)
				.add(Restrictions.like("username", name, MatchMode.ANYWHERE))
				.add(Restrictions.eq("enabled", true)).list();
		if (users != null) {
			return users;
		} else {
			return new ArrayList<User>();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Map<String,String>> getUserSuggestionByStoreName(String name)
			throws OorniException {
		Criteria c = getSession().createCriteria(User.class, "user");
		c.createAlias("user.store", "store");
		c.add(Restrictions.like("store.storeName", name, MatchMode.ANYWHERE));
		c.add(Restrictions.eq("user.enabled", true));
		c.setProjection(Projections.projectionList()
			.add(Projections.property("id"), "ownerId")
			.add(Projections.property("store.storeName"), "storeName"))
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> users = c.list();
		if (users != null) {
			return users;
		} else {
			return new ArrayList<Map<String,String>>();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @throws OorniException 
	 *//*
	@Transactional
	public BankAccount saveBankDetails(BankAccount bankAccount) throws OorniException {
		try {
			bankAccount = (BankAccount) getSession().merge(bankAccount);
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
		return bankAccount;
	}
	
	*//**
	 * {@inheritDoc}
	 * @throws OorniException 
	 *//*
	@Transactional
	public OnlineWallet saveWalletDetails(OnlineWallet onlineWallet) throws OorniException {
		try {
			onlineWallet = (OnlineWallet) getSession().merge(onlineWallet);
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
		return onlineWallet;
	}
	
	*//**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 *//*
	@SuppressWarnings("unchecked")
	@Transactional
	public BankAccount getBankDetail(Long accountId) throws OorniException {
		List<BankAccount> accounts = getSession().createCriteria(BankAccount.class)
				.add(Restrictions.eq("bankAccountId", accountId)).list();
		if (accounts != null && accounts.size() > 0) {
			return accounts.get(0);
		} else {
			throw new OorniException("No Bank details found for id " + accountId);
		}
	}
	
	*//**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 *//*
	@SuppressWarnings("unchecked")
	@Transactional
	public BankAccount getBankDetail(Long accountId, Long ownerId) throws OorniException {
		List<BankAccount> accounts = getSession().createCriteria(BankAccount.class)
				.add(Restrictions.eq("bankAccountId", accountId))
				.add(Restrictions.eq("owner.id", ownerId)).list();
		if (accounts != null && accounts.size() > 0) {
			return accounts.get(0);
		} else {
			throw new OorniException("No Bank details found for id " + accountId);
		}
	}
	
	*//**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 *//*
	@SuppressWarnings("unchecked")
	@Transactional
	public OnlineWallet getWalletDetail(Long walletId) throws OorniException {
		List<OnlineWallet> wallets = getSession().createCriteria(OnlineWallet.class)
				.add(Restrictions.eq("walletId", walletId)).list();
		if (wallets != null && wallets.size() > 0) {
			return wallets.get(0);
		} else {
			throw new OorniException("No Wallet details found for id " + walletId);
		}
	}
	
	*//**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 *//*
	@SuppressWarnings("unchecked")
	@Transactional
	public OnlineWallet getWalletDetail(Long walletId, Long ownerId) throws OorniException {
		List<OnlineWallet> wallets = getSession().createCriteria(OnlineWallet.class)
				.add(Restrictions.eq("walletId", walletId))
				.add(Restrictions.eq("owner.id", ownerId)).list();
		if (wallets != null && wallets.size() > 0) {
			return wallets.get(0);
		} else {
			throw new OorniException("No Wallet details found for id " + walletId);
		}
	}*/
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Payment getPaymentRequest(Long paymentId) throws OorniException {
		List<Payment> payments = getSession().createCriteria(Payment.class)
				.add(Restrictions.eq("paymentId", paymentId)).list();
		if (payments != null && payments.size() > 0) {
			return payments.get(0);
		} else {
			throw new OorniException("No payment details found for id " + paymentId);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Payment getPaymentRequest(Long paymentId, String username) throws OorniException {
		List<Payment> payments = getSession().createCriteria(Payment.class)
				.add(Restrictions.eq("paymentId", paymentId))
				.add(Restrictions.eq("createdBy", username)).list();
		if (payments != null && payments.size() > 0) {
			return payments.get(0);
		} else {
			throw new OorniException("No payment details found for id " + paymentId);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @throws OorniException 
	 */
	@Transactional
	public Payment savePayment(Payment payment) throws OorniException {
		try {
			payment = (Payment) getSession().merge(payment);
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
		return payment;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Payment> getAllPayments() {
		return getSession().createCriteria(Payment.class).list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Payment> getPayments(String username) {
		return getSession().createCriteria(Payment.class)
		.add(Restrictions.eq("createdBy", username)).list();
	}
}
