/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.companymanager.dbpopulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBRefPopulatorRunner {

    private static Logger logger = LoggerFactory.getLogger(DBRefPopulatorRunner.class);

    public static void main(final String... args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "dbpopulator-ref-datasource-context.xml", "dbpopulator-jpa-context.xml", "services-context.xml",
                "facades-context.xml", "components-context.xml");
        DBPopulator populator = ctx.getBean(DBPopulator.class);
        logger.info("Populating database");
        populator.populate();
        logger.info("Database populated ");

        ctx.close();
    }
}
