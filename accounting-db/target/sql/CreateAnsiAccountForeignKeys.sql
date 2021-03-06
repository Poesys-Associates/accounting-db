--
-- Copyright 2016 Poesys Associates. All rights reserved.
--
-- Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
-- Template: SubsystemAnsiSchemaForeignKeys.vsl

-- Create all the foreign keys for the Account schema

-- Foreign keys for FiscalYearAccount

ALTER TABLE FiscalYearAccount ADD CONSTRAINT group FOREIGN KEY (accountType, groupName) REFERENCES AccountGroup(accountType, groupName) ON DELETE CASCADE;
ALTER TABLE FiscalYearAccount ADD CONSTRAINT group FOREIGN KEY (accountType, groupName) REFERENCES AccountGroup(accountType, groupName) ON DELETE CASCADE;

-- Foreign keys for Account

ALTER TABLE Account ADD CONSTRAINT entity FOREIGN KEY (entityName) REFERENCES Entity(entityName) ON DELETE CASCADE;

-- Foreign keys for AccountGroup

ALTER TABLE AccountGroup ADD CONSTRAINT type FOREIGN KEY (accountType) REFERENCES AccountType(accountType) ON DELETE CASCADE;

-- Foreign keys for CapitalAccount

ALTER TABLE CapitalAccount ADD CONSTRAINT CapitalAccountPkFk FOREIGN KEY (accountName) REFERENCES Account(accountName) ON DELETE CASCADE;
ALTER TABLE CapitalAccount ADD CONSTRAINT capitalEntity FOREIGN KEY (capitalEntityName) REFERENCES CapitalEntity(capitalEntityName);

-- Foreign keys for DistributionAccount

ALTER TABLE DistributionAccount ADD CONSTRAINT capitalEntity FOREIGN KEY (capitalEntityName) REFERENCES CapitalEntity(capitalEntityName);
ALTER TABLE DistributionAccount ADD CONSTRAINT DistributionAccountPkFk FOREIGN KEY (accountName) REFERENCES Account(accountName) ON DELETE CASCADE;

-- Foreign keys for SimpleAccount

ALTER TABLE SimpleAccount ADD CONSTRAINT SimpleAccountPkFk FOREIGN KEY (accountName) REFERENCES Account(accountName) ON DELETE CASCADE;

