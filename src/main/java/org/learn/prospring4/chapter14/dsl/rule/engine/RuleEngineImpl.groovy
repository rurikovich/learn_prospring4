package org.learn.prospring4.chapter14.dsl.rule.engine

import org.learn.prospring4.chapter14.dsl.rule.Rule
import org.springframework.stereotype.Component

/**
 * Created by User on 26.01.2017.
 */
@Component
class RuleEngineImpl implements RuleEngine {
    @Override
    void run(Rule rule, Object object) {
        println "Executing rule"
        def exit = false
        rule.parameters.each { ArrayList params ->
            def paramindex = 0
            def success = true
            if (!exit) {
                rule.conditions.each {
                    println "Condition Param index: " + paramindex
                    success = success && it(object, params[paramindex])
                    println "Condi tion success: " + success
                    paramindex++
                }
                if (success && !exit) {
                    rule.actions.each {
                        println "Action Param index: " + paramindex
                        it(object, params[paramindex])
                        paramindex++
                    }
                    if (rule.singlehit) {
                        exit = true
                    }
                }
            }
        }
    }


}
