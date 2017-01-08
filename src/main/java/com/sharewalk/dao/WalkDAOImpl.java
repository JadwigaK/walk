package com.sharewalk.dao;

import com.sharewalk.model.Walk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Jadwiga on 2017-01-07.
 */

@Repository
public class WalkDAOImpl implements WalkDAO {

    private static final Logger logger = LoggerFactory.getLogger(WalkDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Walk> listWalks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Walk> walksList = session.createQuery("from com.sharewalk.model.Walk").list();
        for (Walk p : walksList) {
            logger.info("Walks List::" + p);
        }
        return walksList;
    }
}
