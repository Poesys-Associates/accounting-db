/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Query.vsl

package com.poesys.accounting.db.transaction.sql;


/**
 * <p>
 * A single-object query of a Transaction using its primary key. This class
 * is the concrete subclass of the generated abstract class AbstractQueryTransaction. 
 * Make any changes to query behavior by overriding methods here rather than 
 * changing the abstract superclass; AndroMDA will overwrite that class when you
 * run it but will never overwrite this concrete subclass.
 * </p>
* <p>
 * This class contains the specification of the SQL statement that queries a
 * single Transaction object from the database.
 * </p>
${dto.getDocumentation}
 * 
 * @author Poesys/DB Cartridge
 */
public class QueryTransaction extends AbstractQueryTransaction {
}