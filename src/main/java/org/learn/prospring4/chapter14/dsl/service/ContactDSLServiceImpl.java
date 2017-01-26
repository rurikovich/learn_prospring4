package org.learn.prospring4.chapter14.dsl.service;

import org.learn.prospring4.chapter14.dsl.pojo.ContactDSL;
import org.learn.prospring4.chapter14.dsl.rule.Rule;
import org.learn.prospring4.chapter14.dsl.rule.engine.RuleEngine;
import org.learn.prospring4.chapter14.dsl.rule.factory.RuleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by User on 26.01.2017.
 */
@Service("contactDSLService")
public class ContactDSLServiceImpl implements ContactDSLService {
    @Autowired
    ApplicationContext ctx;

    @Autowired
    private RuleFactory ruleFactory;

    @Autowired
    private RuleEngine ruleEngine;

    @Override
    public void applyRule(ContactDSL contact) {
        Rule ageCategoryRule = ruleFactory.getAgeCategoryRule();
        ruleEngine.run(ageCategoryRule, contact);
    }
}
