-- Create customers table
CREATE TABLE customers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(15),
    PRIMARY KEY (id)
);

-- Initialize customers table
INSERT INTO customers (name, email, password,address,phone) VALUES
('Amit Sharma', 'amit.sharma@example.com', 'e99a18c428cb38d5f260853678922e03','Delhi','9876543210'),
('Priya Singh', 'priya.singh@example.com', '098f6bcd4621d373cade4e832627b4f6','Mumbai','9999999910'),
('Ravi Kumar', 'ravi.kumar@example.com', '5f4dcc3b5aa765d61d8327deb882cf99','Kolkata','9776523220'),
('Sneha Verma', 'sneha.verma@example.com', '202cb962ac59075b964b07152d234b70','Chennai','9877432102'),
('Ankit Mehta', 'ankit.mehta@example.com', '25f9e794323b453885f5181f1b624d0b','Pune','98887775555');

--  Create products table
CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    isavailabel BOOLEAN,
    PRIMARY KEY (id)
);

-- Initialize products table
INSERT INTO products (name, description, price) VALUES
('Coffee', 'Premium quality coffee beans', 299.99,1),
('Tea', 'Assorted tea leaves', 149.50,0),
('Biscuits', 'Assorted biscuits pack', 89.75,1),
('Chocolates', 'Mixed chocolates gift pack', 499.00,1),
('Noodles', 'Instant noodles pack', 59.99,0);


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

--initialize admin table
INSERT INTO admin (name, email, password, adminId, adminRole) VALUES
('Arjun Mehta', 'arjun.mehta@example.com', '5f4dcc3b5aa765d61d8327deb882cf99', 101, 'SuperAdmin'),
('Vikram Desai', 'vikram.desai@example.com', '25f9e794323b453885f5181f1b624d0b', 105, 'SuperAdmin');

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
-- Initialize the orders table
INSERT INTO Orders (order_date, delivery_date, status, customer_id, product_id) VALUES
('2024-08-21', '2024-08-23', 'PENDING', 1, 1),
('2024-08-22', '2024-08-24', 'SHIPPED', 2, 3),
('2024-08-20', '2024-08-22', 'DELIVERED', 3, 5),
('2024-08-19', '2024-08-21', 'CANCELLED', 4, 2),
('2024-08-23', '2024-08-25', 'PENDING', 5, 4);
