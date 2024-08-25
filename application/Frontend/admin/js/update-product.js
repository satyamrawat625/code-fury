document.addEventListener('DOMContentLoaded', function () {
    const fetchProductBtn = document.getElementById('fetchProductBtn');
    const updateProductForm = document.getElementById('updateProductForm');
    const productDetails = document.getElementById('productDetails');
    const subscriptionsContainer = document.getElementById('subscriptionsContainer');
    const subscriptionOptions = ['weekly', 'bi-weekly', 'monthly'];
    const searchProductName = document.getElementById('searchProductName');
    
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
        // Add more dummy products as needed
    };

    // Function to fetch product details
    function fetchProductDetails(productName) {
        return new Promise((resolve, reject) => {
            // Simulate an API call with a timeout
            setTimeout(() => {
                const productData = dummyProducts[productName];
                if (productData) {
                    resolve(productData);
                } else {
                    reject('Product not found.');
                }
            }, 500); // Simulate network delay
        });
    }

    fetchProductBtn.addEventListener('click', async function () {
        const productName = searchProductName.value.trim();

        if (!productName) {
            alert('Please enter a product name.');
            return;
        }

        try {
            const product = await fetchProductDetails(productName);

            // Populate the form with fetched data
            document.getElementById('productName').value = product.name;
            document.getElementById('description').value = product.description;
            document.getElementById('price').value = product.price;
            document.getElementById('quantity').value = product.quantity;

            // Clear existing subscriptions
            subscriptionsContainer.innerHTML = '';

            // Add existing subscriptions
            if (product.subscriptions && product.subscriptions.length > 0) {
                product.subscriptions.forEach(sub => {
                    addSubscription(sub.type, sub.startDate, sub.endDate, sub.discount);
                });
            }

            // Show product details section
            productDetails.classList.remove('hidden');
        } catch (error) {
            alert(error);
        }
    });

    updateProductForm.addEventListener('submit', function (e) {
        e.preventDefault();
        // Handle form submission (e.g., send data to server)
        alert('Product updated successfully.');
    });

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

        // Handle removal of subscription blocks
        subscriptionGroup.querySelector('.remove-subscription-btn').addEventListener('click', function () {
            subscriptionGroup.remove();
        });

        // Date validation: Ensure "end date" is greater than "start date"
        const endDateInput = subscriptionGroup.querySelector('input[name="endDate"]');
        const startDateInput = subscriptionGroup.querySelector('input[name="startDate"]');

        endDateInput.addEventListener('change', function () {
            const startDate = new Date(startDateInput.value);
            const endDate = new Date(endDateInput.value);

            if (endDate <= startDate) {
                alert('End date must be greater than start date.');
                endDateInput.value = ''; // Clear the invalid end date
            }
        });
    }

    function capitalize(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }
});
