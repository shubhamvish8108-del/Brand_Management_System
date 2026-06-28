-- Brand Management System - Database Setup
CREATE DATABASE IF NOT EXISTS brand_management_db;
USE brand_management_db;

-- Tables are auto-created by Hibernate
-- Run this for sample data only:

INSERT INTO customer_groups (group_name, is_active, created_at, updated_at) VALUES
('Persian Darbar Group', TRUE, NOW(), NOW()),
('Golden Palace Group', TRUE, NOW(), NOW()),
('Silver Dragon Group', TRUE, NOW(), NOW());

INSERT INTO chain (company_name, group_id, gst_number, is_active, created_at, updated_at) VALUES
('Persian Darbar Restaurant', 1, 'GST123456789', TRUE, NOW(), NOW()),
('Golden Palace Hotel', 2, 'GST987654321', TRUE, NOW(), NOW()),
('Silver Dragon Cafe', 3, 'GST456789123', TRUE, NOW(), NOW());

INSERT INTO brand (brand_name, chain_id, is_active, created_at, updated_at) VALUES
('Persian Darbar Main', 1, TRUE, NOW(), NOW()),
('Persian Darbar Express', 1, TRUE, NOW(), NOW()),
('Golden Palace Restaurant', 2, TRUE, NOW(), NOW()),
('Golden Palace Banquet', 2, TRUE, NOW(), NOW()),
('Silver Dragon Coffee', 3, FALSE, NOW(), NOW());

INSERT INTO zone (zone_name, brand_id, is_active, created_at, updated_at) VALUES
('North Zone', 1, TRUE, NOW(), NOW()),
('South Zone', 1, TRUE, NOW(), NOW()),
('East Zone', 2, TRUE, NOW(), NOW());
