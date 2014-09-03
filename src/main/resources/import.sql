INSERT INTO business_categories values (1,"FOOD");
INSERT INTO business_categories values (2,"CLOTHS");
INSERT INTO business_categories values (3,"BEAUTY");

INSERT INTO authorities values (1,"ROLE_USER");
INSERT INTO authorities values (2,"ROLE_CUSTOMER");
INSERT INTO authorities values (3,"ROLE_ADMIN");

INSERT INTO customers values (1,"customer@cuponza.co","Uri",sysdate(),"5551212","Naor","3201233232","secret");

INSERT INTO businesses values (1,"santafe@cuponza.co","Santa fe","www.business.com","5551212","3201233232",false,1,1);
INSERT INTO businesses values (2,"oviedo@cuponza.co","Oviedo","www.business.com","5551212","3201233232",false,1,1);

INSERT INTO coordinates values (1,6.197911,-75.574379,1);
INSERT INTO coordinates values (2,6.198802,-75.574159,2);

INSERT INTO cupons values (1,true,sysdate(),"Santa fe descuento grande",3,"SantaFe CC",12,sysdate()+30,"/pictures/ss/2",1);
INSERT INTO cupons values (2,true,sysdate(),"Oviedo descuento bueno",3,"Oviedo CC",16,sysdate()+30,"/pictures/ss/3",2);

