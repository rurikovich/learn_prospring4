package org.learn.prospring4.chapter12.rest.jmx;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

/**
 * Created by User on 27.01.2017.
 */
@Component
public class HibernateStatisticsFactoryBean implements FactoryBean<Statistics> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    @Override
    public Statistics getObject() throws Exception {
        final SessionFactory sessionFactory = ((HibernateEntityManagerFactory) entityManagerFactory).getSessionFactory();
        return sessionFactory.getStatistics();
    }

    @Override
    public Class<?> getObjectType() {
        return Statistics.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}