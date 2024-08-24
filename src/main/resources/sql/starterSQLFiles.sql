-- Create customers table
CREATE TABLE customers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Initialize customers table
INSERT INTO customers (name, email, password) VALUES
('Amit Sharma', 'amit.sharma@example.com', 'e99a18c428cb38d5f260853678922e03'),
('Priya Singh', 'priya.singh@example.com', '098f6bcd4621d373cade4e832627b4f6'),
('Ravi Kumar', 'ravi.kumar@example.com', '5f4dcc3b5aa765d61d8327deb882cf99'),
('Sneha Verma', 'sneha.verma@example.com', '202cb962ac59075b964b07152d234b70'),
('Ankit Mehta', 'ankit.mehta@example.com', '25f9e794323b453885f5181f1b624d0b');

--  Create products table
CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

-- Initialize products table
INSERT INTO products (name, description, price) VALUES
('Coffee', 'Premium quality coffee beans', 299.99),
('Tea', 'Assorted tea leaves', 149.50),
('Biscuits', 'Assorted biscuits pack', 89.75),
('Chocolates', 'Mixed chocolates gift pack', 499.00),
('Noodles', 'Instant noodles pack', 59.99);


-- Create subscriptions table
CREATE TABLE subscriptions (
    id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    frequency VARCHAR(50) NOT NULL,
    is_active TINYINT(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Initialize subscription table
INSERT INTO subscriptions (customer_id, product_id, frequency, is_active) VALUES
(1, 1, 'Monthly', 1),
(2, 2, 'Bi-weekly', 1),
(3, 3, 'Weekly', 0),
(4, 4, 'Monthly', 1),
(5, 5, 'Quarterly', 0);


-- Create Admin table
CREATE TABLE admin (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    adminId INT NOT NULL,
    adminRole VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

-- Create Orders table
CREATE TABLE Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE NOT NULL,
    delivery_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
