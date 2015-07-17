package org.zhubao.model;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TableCreateTest {

    @Test
    public void createTable () {
        new SchemaExport(new Configuration().configure("/hibernate.xml")).create(true, true);
    }
}
