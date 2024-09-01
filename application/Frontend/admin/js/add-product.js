document.addEventListener('DOMContentLoaded', function () {
    const addSubscriptionBtn = document.getElementById('addSubscription');
    const subscriptionsContainer = document.getElementById('subscriptionsContainer');
    const subscriptionOptions = ['weekly', 'bi-weekly', 'monthly'];

    // Existing code for adding subscriptions
    addSubscriptionBtn.addEventListener('click', function () {
        const remainingOptions = subscriptionOptions.filter(option => {
            return !Array.from(document.querySelectorAll('select[name="subscriptionType"]')).some(select => select.value === option);
        });

        if (remainingOptions.length === 0) {
            alert('All subscription options have been added.');
            return;
        }

        const subscriptionGroup = document.createElement('div');
        subscriptionGroup.classList.add('subscription-group');

        subscriptionGroup.innerHTML = `
            <div class="form-group">
                <label for="subscriptionType">Subscription Type</label>
                <select name="subscriptionType" required>
                    ${remainingOptions.map(option => `<option value="${option}">${capitalize(option)}</option>`).join('')}
                </select>
            </div>

            <div class="form-group">
                <label for="startDate">Start Date</label>
                <input type="date" name="startDate" required>
            </div>

            <div class="form-group">
                <label for="endDate">End Date</label>
                <input type="date" name="endDate" required>
            </div>

            <div class="form-group">
                <label for="discount">Discount (%)</label>
                <input type="number" name="discount" placeholder="Enter discount percentage" required min="0">
            </div>

            <button type="button" class="remove-subscription-btn">Remove Subscription</button>
        `;

        subscriptionsContainer.appendChild(subscriptionGroup);

        subscriptionGroup.querySelector('.remove-subscription-btn').addEventListener('click', function () {
            subscriptionGroup.remove();
        });

        const endDateInput = subscriptionGroup.querySelector('input[name="endDate"]');
        const startDateInput = subscriptionGroup.querySelector('input[name="startDate"]');

        endDateInput.addEventListener('change', function () {
            const startDate = new Date(startDateInput.value);
            const endDate = new Date(endDateInput.value);

            if (endDate <= startDate) {
                alert('End date must be greater than start date.');
                endDateInput.value = "";
            }
        });
    });

    // Non-negative integer validation for price, quantity, and discount
    document.querySelector('input[name="price"]').setAttribute('min', '0');
    document.querySelector('input[name="quantity"]').setAttribute('min', '0');
    document.querySelectorAll('input[name="discount"]').forEach(discountField => {
        discountField.setAttribute('min', '0');
    });

    function capitalize(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    // New code for update functionality
    const fetchProductBtn = document.getElementById('fetchProductBtn');
    const searchProductName = document.getElementById('searchProductName');
    const updateProductForm = document.getElementById('updateProductForm');
    const deleteProductForm = document.getElementById('deleteProductForm');
    const updateProductButton = document.querySelector('button[type="submit"]');

    // Dummy product data
    const dummyProducts = {
        'Apple': {
            name: 'Apple',
            description: 'Fresh fruit',
            price: 100,
            quantity: 10,
            subscriptions: [
                { type: 'weekly', startDate: '2024-01-01', endDate: '2024-12-31', discount: 2 }
            ]
        },
        'Banana': {
            name: 'Banana',
            description: 'Tropical fruit',
            price: 50,
            quantity: 20,
            subscriptions: []
        }
    };

    function fetchProductDetails(productName) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                const productData = dummyProducts[productName];
                if (productData) {
                    resolve(productData);
                } else {
                    reject('Product not found.');
                }
            }, 500);
        });
    }

    if (fetchProductBtn) {
        fetchProductBtn.addEventListener('click', async function () {
            const productName = searchProductName.value.trim();

            if (!productName) {
                alert('Please enter a product name.');
                return;
            }

            try {
                const product = await fetchProductDetails(productName);

                document.getElementById('productName').value = product.name;
                document.getElementById('description').value = product.description;
                document.getElementById('price').value = product.price;
                document.getElementById('quantity').value = product.quantity;

                subscriptionsContainer.innerHTML = '';

                if (product.subscriptions && product.subscriptions.length > 0) {
                    product.subscriptions.forEach(sub => {
                        addSubscription(sub.type, sub.startDate, sub.endDate, sub.discount);
                    });
                }

                updateProductForm.classList.remove('hidden');
                fetchProductBtn.classList.add('hidden');
            } catch (error) {
                alert(error);
            }
        });
    }

    if (updateProductButton) {
        updateProductForm.addEventListener('submit', function (e) {
            e.preventDefault();
            alert('Product updated successfully.');
        });
    }

    function addSubscription(type, startDate = '', endDate = '', discount = '') {
        const remainingOptions = subscriptionOptions.filter(option => {
            return !Array.from(document.querySelectorAll('select[name="subscriptionType"]')).some(select => select.value === option);
        });

        if (remainingOptions.length === 0 && !type) {
            alert('All subscription options have been added.');
            return;
        }

        const subscriptionGroup = document.createElement('div');
        subscriptionGroup.classList.add('subscription-group');

        subscriptionGroup.innerHTML = `
            <div class="form-group">
                <label for="subscriptionType">Subscription Type</label>
                <select name="subscriptionType" required>
                    ${subscriptionOptions.map(option => `<option value="${option}" ${option === type ? 'selected' : ''}>${capitalize(option)}</option>`).join('')}
                </select>
            </div>

            <div class="form-group">
                <label for="startDate">Start Date</label>
                <input type="date" name="startDate" value="${startDate}" required>
            </div>

            <div class="form-group">
                <label for="endDate">End Date</label>
                <input type="date" name="endDate" value="${endDate}" required>
            </div>

            <div class="form-group">
                <label for="discount">Discount (%)</label>
                <input type="number" name="discount" placeholder="Enter discount percentage" value="${discount}" required min="0">
            </div>

            <button type="button" class="remove-subscription-btn">Remove Subscription</button>
        `;

        subscriptionsContainer.appendChild(subscriptionGroup);

        subscriptionGroup.querySelector('.remove-subscription-btn').addEventListener('click', function () {
            subscriptionGroup.remove();
        });

        const endDateInput = subscriptionGroup.querySelector('input[name="endDate"]');
        const startDateInput = subscriptionGroup.querySelector('input[name="startDate"]');

        endDateInput.addEventListener('change', function () {
            const startDate = new Date(startDateInput.value);
            const endDate = new Date(endDateInput.value);

            if (endDate <= startDate) {
                alert('End date must be greater than start date.');
                endDateInput.value = '';
            }
        });
    }
});
document.addEventListener('DOMContentLoaded', function () {
    const orderHistoryTableBody = document.querySelector('#orderHistoryTable tbody');

    // Dummy order history data
    const orderHistoryData = [
        {
            orderID: 'ORD001',
            orderDate: '2024-08-01',
            productID: 'P001',
            productName: 'Apple',
            customerID: 'C001',
            customerName: 'John Doe',
            subscriptionType: 'weekly',
            quantityOrdered: 5,
            totalPrice: 500,
            deliveryDate: '2024-08-07'
        },
        {
            orderID: 'ORD002',
            orderDate: '2024-08-02',
            productID: 'P002',
            productName: 'Banana',
            customerID: 'C002',
            customerName: 'Jane Smith',
            subscriptionType: 'monthly',
            quantityOrdered: 10,
            totalPrice: 500,
            deliveryDate: '2024-08-30'
        },
        {
            orderID: 'ORD003',
            orderDate: '2024-08-03',
            productID: 'P003',
            productName: 'Orange',
            customerID: 'C003',
            customerName: 'Alice Johnson',
            subscriptionType: 'bi-weekly',
            quantityOrdered: 7,
            totalPrice: 350,
            deliveryDate: '2024-08-17'
        }
    ];

    // Function to generate table rows
    function generateTableRows(data) {
        return data.map(order => `
            <tr>
                <td>${order.orderID}</td>
                <td>${order.orderDate}</td>
                <td>${order.productID}</td>
                <td>${order.productName}</td>
                <td>${order.customerID}</td>
                <td>${order.customerName}</td>
                <td>${order.subscriptionType}</td>
                <td>${order.quantityOrdered}</td>
                <td>${order.totalPrice}</td>
                <td>${order.deliveryDate}</td>
            </tr>
        `).join('');
    }

    // Insert rows into the table body
    orderHistoryTableBody.innerHTML = generateTableRows(orderHistoryData);
});

document.getElementById('createProductBtn').addEventListener('click', function () {
    window.location.href = './html/add-product.html';
});

function deleteSubscription(button) {
    // Find the row of the button clicked
    var row = button.closest('tr');
    // Remove the row from the table
    row.remove();
}
