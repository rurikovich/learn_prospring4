package dsl.rules

import org.joda.time.DateTime
import org.joda.time.Years
import org.learn.prospring4.chapter14.dsl.rule.Rule
import org.learn.prospring4.chapter14.dsl.rule.factory.RuleFactory
import org.springframework.stereotype.Component
/**
 * Created by User on 26.01.2017.
 */

@Component
class RuleFactoryimpl implements RuleFactory {

    Closure age = {
        birthDate -> Years.yearsBetween(birthDate, new DateTime()).getYears()
    }

    @Override
    Rule getAgeCategoryRule() {
        Rule rule = new Rule()
        rule.singlehit = true
        rule.conditions = [{ object, param -> age(object.birthDate) >= param },
                           { object, param -> age(object.birthDate) <= param }]
        rule.actions = [{ object, param -> object.ageCategory = param }]
        rule.parameters = [
                [0, 1, 'Kid'],
                [1, 20, 'Youth'],
                [21, 40, 'Adult'],
                [41, 60, 'Middle-aged'],
                [61, 120, 'Old'],
        ]
        rule
    }
}
