package org.learn.prospring4.chapter12.rest.jmx;

import org.learn.prospring4.chapter12.rest.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 27.01.2017.
 */
public class AppStatisticsImpl implements AppStatistics {
    @Autowired
    private ContactService contactService;

    @Override
    public int getTotalContactCount() {
        return contactService.findAll().size();
    }
}
