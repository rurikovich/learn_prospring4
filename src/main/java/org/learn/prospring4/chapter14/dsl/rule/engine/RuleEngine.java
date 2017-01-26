package org.learn.prospring4.chapter14.dsl.rule.engine;

import org.learn.prospring4.chapter14.dsl.rule.Rule;

/**
 * Created by User on 26.01.2017.
 */
public interface RuleEngine {
    void run(Rule rule, Object object);
}
