package org.learn.prospring4.chapter14.dsl.service;

import org.learn.prospring4.chapter14.dsl.pojo.ContactDSL;

/**
 * Created by User on 26.01.2017.
 */
public interface ContactDSLService {
    void applyRule(ContactDSL contact);
}
