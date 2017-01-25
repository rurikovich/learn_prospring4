package org.learn.prospring4.chapter13.annotations;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

/**
 * Created by rurik on 25.01.2017.
 */
public class ServiceTestExecutionListener implements TestExecutionListener {
    private IDatabaseTester databaseTester;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {

    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {

    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        DataSets dataSetAnnotation = testContext.getTestMethod().getAnnotation(DataSets.class);
        if (dataSetAnnotation == null) {
            return;
        }
        String dataSetName = dataSetAnnotation.setUpDataSet();
        if (!dataSetName.equals("")) {
            ApplicationContext applicationContext = testContext.getApplicationContext();
            databaseTester = (IDatabaseTester) applicationContext.getBean("databaseTester");
            XlsDataFileLoader xlsDataFileLoader = (XlsDataFileLoader) applicationContext.getBean("xlsDataFileLoader");
            IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
        }

    }


    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (databaseTester != null) {
            databaseTester.onTearDown();
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {

    }
}
