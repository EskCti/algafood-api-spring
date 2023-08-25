ALTER TABLE tab_payments_type
    ADD updated_at datetime null;
UPDATE tab_payments_type
    SET updated_at = utc_timestamp;
ALTER TABLE tab_payments_type
    MODIFY updated_at datetime not null