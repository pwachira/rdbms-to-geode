-- triggers
DROP TRIGGER IF EXISTS `CUSTOMER_AFTER_INSERT`;
DROP TRIGGER IF EXISTS `CUSTOMER_AFTER_UPDATE`;
DROP TRIGGER IF EXISTS `CUSTOMER_AFTER_DELETE`;
DROP TRIGGER IF EXISTS `CUSTOMER_ORDER_AFTER_INSERT`;
DROP TRIGGER IF EXISTS `CUSTOMER_ORDER_AFTER_UPDATE`;
DROP TRIGGER IF EXISTS `CUSTOMER_ORDER_AFTER_DELETE`;
DROP TRIGGER IF EXISTS `ITEM_AFTER_INSERT`;
DROP TRIGGER IF EXISTS `ITEM_AFTER_UPDATE`;
DROP TRIGGER IF EXISTS `ITEM_AFTER_DELETE`;
DROP TRIGGER IF EXISTS `ORDER_ITEM_AFTER_INSERT`;
DROP TRIGGER IF EXISTS `ORDER_ITEM_AFTER_UPDATE`;
DROP TRIGGER IF EXISTS `ORDER_ITEM_AFTER_DELETE`;

-- CUSTOMER triggers
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `CUSTOMER_AFTER_INSERT` AFTER INSERT ON `CUSTOMER` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(NEW.ID, 'customer', 'I', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `CUSTOMER_AFTER_UPDATE` AFTER UPDATE ON `CUSTOMER` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(NEW.ID, 'customer', 'U', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `CUSTOMER_AFTER_DELETE` AFTER DELETE ON `CUSTOMER` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(OLD.ID, 'customer', 'D', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

-- CUSTOMER_ORDER triggers
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `CUSTOMER_ORDER_AFTER_INSERT` AFTER INSERT ON `CUSTOMER_ORDER` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(NEW.ID, 'customerOrder', 'I', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `CUSTOMER_ORDER_AFTER_UPDATE` AFTER UPDATE ON `CUSTOMER_ORDER` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(NEW.ID, 'customerOrder', 'U', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `CUSTOMER_ORDER_AFTER_DELETE` AFTER DELETE ON `CUSTOMER_ORDER` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(OLD.ID, 'customerOrder', 'D', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

-- ITEM triggers
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ITEM_AFTER_INSERT` AFTER INSERT ON `ITEM` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(NEW.ID, 'item', 'I', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ITEM_AFTER_UPDATE` AFTER UPDATE ON `ITEM` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(NEW.ID, 'item', 'U', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ITEM_AFTER_DELETE` AFTER DELETE ON `ITEM` FOR EACH ROW
BEGIN
	INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
	VALUES(OLD.ID, 'item', 'D', '0', CURRENT_TIMESTAMP);
END$$
DELIMITER ;

-- ORDER_ITEM triggers
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ORDER_ITEM_AFTER_INSERT` AFTER INSERT ON `ORDER_ITEM` FOR EACH ROW
	BEGIN
		INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
		VALUES(NEW.CUSTOMER_ORDER_ID, 'customerOrder', 'U', '0', CURRENT_TIMESTAMP);
	END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ORDER_ITEM_AFTER_UPDATE` AFTER UPDATE ON `ORDER_ITEM` FOR EACH ROW
	BEGIN
		INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
		VALUES(NEW.CUSTOMER_ORDER_ID, 'customerOrder', 'U', '0', CURRENT_TIMESTAMP);
	END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ORDER_ITEM_AFTER_DELETE` AFTER DELETE ON `ORDER_ITEM` FOR EACH ROW
	BEGIN
		INSERT INTO DB_EVENTS(SRC_KEY, SRC_GROUP, ACTION_CODE, STATUS_CODE, EVENT_TS)
		VALUES(OLD.CUSTOMER_ORDER_ID, 'customerOrder', 'U', '0', CURRENT_TIMESTAMP);
	END$$
DELIMITER ;
