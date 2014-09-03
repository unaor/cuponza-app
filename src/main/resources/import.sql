INSERT INTO business_categories values (1,"FOOD");
INSERT INTO business_categories values (2,"CLOTHS");
INSERT INTO business_categories values (3,"BEAUTY");

INSERT INTO authorities values (1,"ROLE_USER");
INSERT INTO authorities values (2,"ROLE_CUSTOMER");
INSERT INTO authorities values (3,"ROLE_ADMIN");

INSERT INTO customers values (1,"customer@cuponza.co","Uri",sysdate(),"5551212","Naor","3201233232","secret");

INSERT INTO businesses values (1,"business@cuponza.co","Cool Business","www.business.com","5551212","3201233232",false,1,1);


