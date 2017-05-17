package com.lph.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lph.app.dao.AbstractDao;
import com.lph.app.dao.AppUserDao;
import com.lph.app.entity.AppUser;

@Repository("appUserDaoImpl")
public class AppUserDaoImpl extends AbstractDao<Integer, AppUser> implements AppUserDao {

    @Override
    public AppUser findById(int id) {
	AppUser user = getByKey(id);
	if (user != null) {
	    Hibernate.initialize(user.getUserProfiles());
	}
	return user;
    }

    @Override
    public AppUser findBySSO(String sso) {
	System.out.println("SSO : " + sso);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("ssoId", sso));
	AppUser user = (AppUser) crit.uniqueResult();
	if (user != null) {
	    Hibernate.initialize(user.getUserProfiles());
	}
	return user;
    }

    @Override
    public void save(AppUser user) {
	persist(user);
    }

    @Override
    public void deleteBySSO(String sso) {
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("ssoId", sso));
	AppUser user = (AppUser) crit.uniqueResult();
	delete(user);
    }

    @Override
    public List<AppUser> findAllUsers() {
	Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
								     // duplicates.
	List<AppUser> users = (List<AppUser>) criteria.list();

	// No need to fetch userProfiles since we are not showing them on list
	// page. Let them lazy load.
	// Uncomment below lines for eagerly fetching of userProfiles if you
	// want.
	/*
	 * for(User user : users){ Hibernate.initialize(user.getUserProfiles());
	 * }
	 */
	return users;
    }

}
