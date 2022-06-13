ALTER TABLE `order`
RENAME COLUMN expiration_day TO expiration_date;

ALTER TABLE sale
RENAME COLUMN expiration_day TO expiration_date;

ALTER TABLE trade
RENAME COLUMN signing_date TO matching_date;