const cart = JSON.parse(localStorage.getItem('cart')) || [];
    
        // Function to add items to the cart
        function addToCart(productId, productName, productPrice, productQuantity) {
            const existingProduct = cart.find(item => item.id === productId);
    
            if (existingProduct) {
                existingProduct.quantity += 1;
            } else {
                cart.push({
                    id: productId,
                    name: productName,
                    price: productPrice,
                    quantity: 1,
                    unitQuantity: productQuantity
                });
            }
    
            localStorage.setItem('cart', JSON.stringify(cart));
            alert(`${productName} has been added to your cart!`);
        }
    
        document.querySelectorAll('.add-btn').forEach(button => {
            button.addEventListener('click', function () {
                const productCard = this.closest('.product-card');
                const productId = parseInt(productCard.getAttribute('data-product-id'));
                const productName = productCard.getAttribute('data-product-name');
                const productPrice = parseFloat(productCard.getAttribute('data-product-price'));
                const productQuantity = productCard.getAttribute('data-product-quantity');
    
                addToCart(productId, productName, productPrice, productQuantity);
            });
        });