const product = document.getElementById('searchProductName').text;
        if (product != null) {
            alert('Product deleted successfully.');
        }

document.addEventListener('DOMContentLoaded', function () {
    const fetchProductBtn = document.getElementById('fetchProductBtn');
    const searchProductName = document.getElementById('searchProductName');
    const deleteProductForm = document.getElementById('deleteProductForm');
    const deleteProductBtn = document.getElementById('deleteProductBtn');

    // Dummy product data
    const dummyProducts = {
        'Apple': {
            name: 'Apple',
            description: 'Fresh fruit',
            price: 100,
            quantity: 10
        },
        'Banana': {
            name: 'Banana',
            description: 'Tropical fruit',
            price: 50,
            quantity: 20
        }
    };

    // Function to fetch product details
    function fetchProductDetails(productName) {
        return new Promise((resolve, reject) => {
            const productData = dummyProducts[productName];
            if (productData) {
                resolve(productData);
            } else {
                reject('Product not found.');
            }
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

            // Displaying the fetched product details as static content
            document.getElementById('productName').textContent = product.name;
            document.getElementById('description').textContent = product.description;
            document.getElementById('price').textContent = `₹${product.price}`;
            document.getElementById('quantity').textContent = product.quantity;

            // deleteProductForm.classList.remove('hidden');
        } catch (error) {
            alert(error);
        }
    });

    deleteProductBtn.addEventListener('click', function () {
        const productName = document.getElementById('productName').textContent;

        if (dummyProducts[productName]) {
            delete dummyProducts[productName];
            alert('Product deleted successfully.');
            deleteProductForm.classList.add('hidden');
            searchProductName.value = '';  // Clear search field
        } else {
            alert('Error deleting the product.');
        }
    });
});
