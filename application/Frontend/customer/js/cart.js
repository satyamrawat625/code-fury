document.addEventListener('DOMContentLoaded', () => {
    // Ensure cart is rendered correctly on page load
    renderCart();

    // Add event listeners for "Add to Cart" buttons
    document.querySelectorAll('.add-to-cart').forEach(button => {
        button.addEventListener('click', (event) => {
            const card = event.target.closest('.card');
            if (card) {
                const cardBody = card.querySelector('.card-body');
                
                const productId = cardBody ? cardBody.getAttribute('data-id') : null;                            
                const productName = cardBody ? cardBody.getAttribute('data-name') : null;
                const productPrice = cardBody ? parseFloat(cardBody.getAttribute('data-price')) : 0;
                const productImage = cardBody ? cardBody.getAttribute('data-image') : '';            
                console.log('Add to Cart:', { productId, productName, productPrice, productImage });

                if (productId && productName) {
                    addToCart(productId, productName, productPrice, productImage);
                }
            }
        });
    });

    // Attach event listeners for cart buttons after rendering
    renderCart(); // Reattach event listeners after rendering
});

function addToCart(id, name, price, image) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let product = cart.find(item => item.id === parseInt(productId));

    if (product) {
        product.quantity += 1; // Increase quantity if already in cart
    } else {
        cart.push({
            id: parseInt(id),
            name: name,
            price: price,
            image: image,
            quantity: 1
        });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    renderCart(); // Ensure cart view updates
}

// Render cart items and total price
function renderCart() {
    
    console.log('Render cart called');
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const cartItemsContainer = document.getElementById('cart-items');
    const cartTotalContainer = document.getElementById('total-price');

    if (!cartItemsContainer || !cartTotalContainer) {
        console.error('Cart items container or total price container not found.');
        return;
    }

    if (cart.length === 0) {
        cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
        cartTotalContainer.innerHTML = '';
        return;
    }

    let total = 0;
    cartItemsContainer.innerHTML = cart.map(item => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        return `
            <div class="cart-item">
                <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
                <h4>${item.name}</h4>
                <p>Price: ₹${item.price}</p>
                <p>Quantity: ${item.quantity}</p>
                <p>Total: ₹${itemTotal.toFixed(2)}</p>
                <button class="increase-quantity" data-id="${item.id}">+</button>
                <button class="decrease-quantity" data-id="${item.id}">-</button>
                <button class="remove-item" data-id="${item.id}">Remove</button>
                <hr>
            </div>
        `;
    }).join('');
    console.log(cart)
    cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

    // Reattach event listeners after rendering
    document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
        button.addEventListener('click', () => {
            console.log('Increase quantity for product ID:', button.dataset.id);
            updateQuantity(button.dataset.id, 1);
        });
    });

    document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
        button.addEventListener('click', () => {
            console.log('Decrease quantity for product ID:', button.dataset.id);
            updateQuantity(button.dataset.id, -1);
        });
    });

    document.querySelectorAll('.cart-item .remove-item').forEach(button => {
        button.addEventListener('click', () => {
            console.log('Remove from cart for product ID:', button.dataset.id);
            removeFromCart(button.dataset.id);
        });
    });
}

// Update product quantity in the cart
function updateQuantity(productId, change) {
    console.log(`Update quantity called for product ${productId} with change ${change}`);
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const product = cart.find(item => item.id === parseInt(productId));

    if (product) {
        product.quantity += change;

        if (product.quantity <= 0) {
            removeFromCart(productId);
        } else {
            localStorage.setItem('cart', JSON.stringify(cart));
            renderCart();
        }
    }
}

function removeFromCart(productId) {
    console.log(`Remove from cart called for product ${productId}`);
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart = cart.filter(item => item.id !== parseInt(productId));
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCart(); // Ensure cart view updates
}



// document.addEventListener('DOMContentLoaded', () => {
//     // Ensure cart is rendered correctly on page load
//     renderCart();

//     // Add event listeners for "Add to Cart" buttons
//     document.querySelectorAll('.add-to-cart').forEach(button => {
//         button.addEventListener('click', (event) => {
//             const card = event.target.closest('.card');
//             if (card) {
//                 const cardBody = card.querySelector('.card-body');
//                 const productId = cardBody.getAttribute('data-id');
//                 const productName = cardBody.getAttribute('data-name');
//                 const productPrice = parseFloat(cardBody.getAttribute('data-price'));
//                 const productImage = cardBody.getAttribute('data-image');

//                 addToCart(productId, productName, productPrice, productImage);
//             }
//         });
//     });

//     // Attach event listeners for cart buttons
//     document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
//         button.addEventListener('click', () => updateQuantity(button.dataset.id, 1));
//     });

//     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
//         button.addEventListener('click', () => updateQuantity(button.dataset.id, -1));
//     });

//     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
//         button.addEventListener('click', () => removeFromCart(button.dataset.id));
//     });
// });

// // Add product to the cart
// function addToCart(id, name, price, image) {
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     let product = cart.find(item => item.id === parseInt(id));

//     if (product) {
//         product.quantity += 1; // Increase quantity if already in cart
//     } else {
//         cart.push({
//             id: parseInt(id),
//             name: name,
//             price: price,
//             image: image,
//             quantity: 1
//         });
//     }

//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart(); // Ensure cart view updates
// }

// // Render cart items and total price
// function renderCart() {
//     console.log('Render cart called');
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const cartItemsContainer = document.getElementById('cart-items');
//     const cartTotalContainer = document.getElementById('total-price');

//     if (!cartItemsContainer || !cartTotalContainer) {
//         console.error('Cart items container or total price container not found.');
//         return;
//     }

//     if (cart.length === 0) {
//         cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
//         cartTotalContainer.innerHTML = '';
//         return;
//     }

//     let total = 0;
//     cartItemsContainer.innerHTML = cart.map(item => {
//         const itemTotal = item.price * item.quantity;
//         total += itemTotal;
//         return `
//             <div class="cart-item">
//                 <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
//                 <h4>${item.name}</h4>
//                 <p>Price: ₹${item.price}</p>
//                 <p>Quantity: ${item.quantity}</p>
//                 <p>Total: ₹${itemTotal.toFixed(2)}</p>
//                 <button class="increase-quantity" data-id="${item.id}">+</button>
//                 <button class="decrease-quantity" data-id="${item.id}">-</button>
//                 <button class="remove-item" data-id="${item.id}">Remove</button>
//                 <hr>
//             </div>
//         `;
//     }).join('');

//     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

//     // Reattach event listeners after rendering
//     document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
//         button.addEventListener('click', () => updateQuantity(button.dataset.id, 1));
//     });

//     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
//         button.addEventListener('click', () => updateQuantity(button.dataset.id, -1));
//     });

//     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
//         button.addEventListener('click', () => removeFromCart(button.dataset.id));
//     });
// }

// // Update product quantity in the cart
// function updateQuantity(productId, change) {
//     console.log(`Update quantity called for product ${productId} with change ${change}`);
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const product = cart.find(item => item.id === parseInt(productId));

//     if (product) {
//         product.quantity += change;

//         if (product.quantity <= 0) {
//             removeFromCart(productId);
//         } else {
//             localStorage.setItem('cart', JSON.stringify(cart));
//             renderCart();
//         }
//     }
// }

// // Remove product from the cart
// function removeFromCart(productId) {
//     console.log(`Remove from cart called for product ${productId}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     cart = cart.filter(item => item.id !== parseInt(productId));
//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart(); // Ensure cart view updates
// }



// function renderCart() {
//     console.log('Render cart called');
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const cartItemsContainer = document.getElementById('cart-items');
//     const cartTotalContainer = document.getElementById('total-price');

//     if (!cartItemsContainer || !cartTotalContainer) {
//         console.error('Cart items container or total price container not found.');
//         return;
//     }

//     if (cart.length === 0) {
//         cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
//         cartTotalContainer.innerHTML = '';
//         return;
//     }

//     let total = 0;
//     cartItemsContainer.innerHTML = cart.map(item => {
//         const itemTotal = item.price * item.quantity;
//         total += itemTotal;
//         return `
//             <div class="cart-item">
//                 <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
//                 <h4>${item.name}</h4>
//                 <p>Price: ₹${item.price}</p>
//                 <p>Quantity: ${item.quantity}</p>
//                 <p>Total: ₹${itemTotal.toFixed(2)}</p>
//                 <button class="increase-quantity" data-id="${item.id}">+</button>
//                 <button class="decrease-quantity" data-id="${item.id}">-</button>
//                 <button class="remove-item" data-id="${item.id}">Remove</button>
//                 <hr>
//             </div>
//         `;
//     }).join('');

//     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

//     // Reattach event listeners after rendering
//     attachEventListeners();
// }

// function updateQuantity(productId, change) {
//     console.log(`Update quantity called for product ${productId} with change ${change}`);
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const product = cart.find(item => item.id === parseInt(productId));

//     if (product) {
//         product.quantity += change;

//         if (product.quantity <= 0) {
//             removeFromCart(productId);
//         } else {
//             localStorage.setItem('cart', JSON.stringify(cart));
//             renderCart();  // Ensure cart view updates
//         }
//     }
// }

// function removeFromCart(productId) {
//     console.log(`Remove from cart called for product ${productId}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     cart = cart.filter(item => item.id !== parseInt(productId));
//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart();  // Ensure cart view updates
// }

// function attachEventListeners() {
//     console.log('Attaching event listeners');
//     document.querySelectorAll('.increase-quantity').forEach(button => {
//         button.addEventListener('click', () => updateQuantity(button.dataset.id, 1));
//     });

//     document.querySelectorAll('.decrease-quantity').forEach(button => {
//         button.addEventListener('click', () => updateQuantity(button.dataset.id, -1));
//     });

//     document.querySelectorAll('.remove-item').forEach(button => {
//         button.addEventListener('click', () => removeFromCart(button.dataset.id));
//     });
// }

// document.addEventListener('DOMContentLoaded', () => {
//     renderCart();
// });