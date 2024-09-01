document.addEventListener('DOMContentLoaded', () => {
    // Initial render of the cart
    renderCart();

    // Event listener for quantity increase and decrease
    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('increase-quantity')) {
            console.log("Increase quantity button clicked");
            updateQuantity(Number(event.target.dataset.id), 1);
        } else if (event.target.classList.contains('decrease-quantity')) {
            console.log("Decrease quantity button clicked");
            updateQuantity(Number(event.target.dataset.id), -1);
        } else if (event.target.classList.contains('remove-item')) {
            console.log("Remove item button clicked");
            removeFromCart(Number(event.target.dataset.id));
        }
    });
});

// Function to render the cart
function renderCart() {
    const cartItemsContainer = document.querySelector('.cart-items');
    const totalElement = document.querySelector('.cart-total');
    
    if (!cartItemsContainer || !totalElement) {
        console.error("Cart items container or total element not found");
        return;
    }
    
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    let total = 0;

    cartItemsContainer.innerHTML = cart.map(item => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;     
        return `
            <div class="cart-items">
            
                <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;"/>
                <h4>${item.name}</h4>
                <p>ID: ${item.id}</p>
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
    console.log(cart);
    
    totalElement.innerText = `Total: ₹${total.toFixed(2)}`;
}

// Function to update the quantity of an item
function updateQuantity(id, change) {
    console.log(`Update quantity called for product ${id} with change ${change}`);
    
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const itemIndex = cart.findIndex(item => item.id === id);
    
    if (itemIndex > -1) {
        cart[itemIndex].quantity += change;
        if (cart[itemIndex].quantity <= 0) {
            cart.splice(itemIndex, 1);
        }
        localStorage.setItem('cart', JSON.stringify(cart));
        renderCart();
    } else {
        console.error(`Item with ID ${id} not found in cart`);
    }
}

// Function to remove an item from the cart
function removeFromCart(id) {
        // localStorage.clear();
        // console.log("Local storage cleared!");
    
    console.log(`Remove from cart called for product ${id}`);
    
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const newCart = cart.filter(item => item.id !== id);
    
    if (newCart.length < cart.length) {
        localStorage.setItem('cart', JSON.stringify(newCart));
        renderCart();
    } else {
        console.error(`Item with ID ${id} not found in cart`);
    }
}



// document.addEventListener('DOMContentLoaded', () => {
//     // Initial render of the cart
//     renderCart();

//     // Event listener for quantity increase and decrease
//     document.addEventListener('click', function(event) {
//         if (event.target.classList.contains('increase-quantity')) {
//             updateQuantity(event.target.dataset.id, 1);
//         } else if (event.target.classList.contains('decrease-quantity')) {
//             updateQuantity(event.target.dataset.id, -1);
//         } else if (event.target.classList.contains('remove-item')) {
//             removeFromCart(event.target.dataset.id);
//         }
//     });
// });

// // Function to render the cart
// function renderCart() {
//     const cartItemsContainer = document.querySelector('.cart-items');
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     let total = 0;

//     if (cartItemsContainer) {
//         cartItemsContainer.innerHTML = cart.map(item => {
//             const itemTotal = item.price * item.quantity;
//             total += itemTotal;
//             return `
//                 <div class="cart-item">
//                     <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
//                     <h4>${item.name}</h4>
//                     <p>ID: ${item.id}</p>
//                     <p>Price: ₹${item.price}</p>
//                     <p>Quantity: ${item.quantity}</p>
//                     <p>Total: ₹${itemTotal.toFixed(2)}</p>
//                     <button class="increase-quantity" data-id="${item.id}">+</button>
//                     <button class="decrease-quantity" data-id="${item.id}">-</button>
//                     <button class="remove-item" data-id="${item.id}">Remove</button>
//                     <hr>
//                 </div>
//             `;
//         }).join('');

//         const totalElement = document.querySelector('.cart-total');
//         if (totalElement) {
//             totalElement.innerText = `Total: ₹${total.toFixed(2)}`;
//         }
//     }
// }

// // Function to update the quantity of an item
// function updateQuantity(id, change) {
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const itemIndex = cart.findIndex(item => item.id === id);
//     if (itemIndex > -1) {
//         cart[itemIndex].quantity += change;
//         if (cart[itemIndex].quantity <= 0) {
//             cart.splice(itemIndex, 1);
//         }
//         localStorage.setItem('cart', JSON.stringify(cart));
//         renderCart();
//     }
// }

// // Function to remove an item from the cart
// function removeFromCart(id) {
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const newCart = cart.filter(item => item.id !== id);
//     localStorage.setItem('cart', JSON.stringify(newCart));
//     renderCart();
// }

// // Function to add an item to the cart
// function addToCart(id, name, price, image, quantity) {
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
    
//     // Check if item already exists in the cart
//     const existingItemIndex = cart.findIndex(item => item.id === id);
//     if (existingItemIndex > -1) {
//         // Update quantity if item already exists
//         cart[existingItemIndex].quantity += quantity;
//     } else {
//         // Add new item to cart
//         cart.push({ id, name, price, image, quantity });
//     }

//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart();
// }





// document.addEventListener('DOMContentLoaded', () => {
//     renderCart();
// });

// function renderCart() {
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
//                 <p>ID: ${item.id}</p>
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
//     console.log(cart);
//     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

//     // Reattach event listeners after rendering
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

// function updateQuantity(id, change) {
//     console.log(`Update quantity called for product ${id} with change ${change}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     let product = cart.find(item => item.id === parseInt(id));

//     if (product) {
//         product.quantity += change;

//         if (product.quantity <= 0) {
//             cart = cart.filter(item => item.id !== parseInt(id));
//         }

//         localStorage.setItem('cart', JSON.stringify(cart));
//         renderCart();
//     }
// }

// function removeFromCart(id) {
//     console.log(`Remove from cart called for product ${id}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     cart = cart.filter(item => item.id !== parseInt(id));
//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart(); // Ensure cart view updates
// }







// // Sample Cart Structure
// let cart = [];

// // Function to save cart to localStorage
// function saveCart() {
//     localStorage.setItem("cart", JSON.stringify(cart));
// }

// // Function to load cart from localStorage
// function loadCart() {
//     const savedCart = localStorage.getItem("cart");
//     if (savedCart) {
//         cart = JSON.parse(savedCart);
//     }
// }

// // Function to render the cart items
// function renderCart() {
//     const cartItemsContainer = document.getElementById("cart-items");
//     const cartTotalContainer = document.getElementById("cart-total");

//     let total = 0;

//     if (cartItemsContainer) {
//         cartItemsContainer.innerHTML = cart.map(item => {
//             const itemTotal = item.price * item.quantity;
//             total += itemTotal;
//             return `
//                 <div class="cart-item">
//                     <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
//                     <h4>${item.name}</h4>
//                     <p>ID: ${item.id}</p>
//                     <p>Price: ₹${item.price.toFixed(2)}</p>
//                     <p>Quantity: ${item.quantity}</p>
//                     <p>Total: ₹${itemTotal.toFixed(2)}</p>
//                     <button class="increase-quantity" data-id="${item.id}">+</button>
//                     <button class="decrease-quantity" data-id="${item.id}">-</button>
//                     <button class="remove-item" data-id="${item.id}">Remove</button>
//                     <hr>
//                 </div>
//             `;
//         }).join('');
//     }

//     if (cartTotalContainer) {
//         cartTotalContainer.innerText = `Total: ₹${total.toFixed(2)}`;
//     }
// }

// // Function to add item to the cart
// function addToCart(product) {
//     const existingItem = cart.find(item => item.id === product.id);
//     if (existingItem) {
//         existingItem.quantity++;
//     } else {
//         product.quantity = 1;
//         cart.push(product);
//     }
//     saveCart();
//     renderCart();
// }

// // Function to update item quantity
// function updateQuantity(productId, change) {
//     const item = cart.find(item => item.id === productId);
//     if (item) {
//         item.quantity += change;
//         if (item.quantity <= 0) {
//             removeFromCart(productId);
//         }
//         saveCart();
//         renderCart();
//     }
// }

// // Function to remove item from the cart
// function removeFromCart(productId) {
//     cart = cart.filter(item => item.id !== productId);
//     saveCart();
//     renderCart();
// }

// // Event listeners for cart buttons
// document.addEventListener("click", function(event) {
//     const target = event.target;
//     const productId = target.getAttribute("data-id");

//     if (target.classList.contains("increase-quantity")) {
//         updateQuantity(productId, 1);
//     }

//     if (target.classList.contains("decrease-quantity")) {
//         updateQuantity(productId, -1);
//     }

//     if (target.classList.contains("remove-item")) {
//         removeFromCart(productId);
//     }
// });

// // Load and render the cart on page load
// window.addEventListener("load", function() {
//     loadCart();
//     renderCart();
// });




// document.addEventListener('DOMContentLoaded', () => {
//     // Ensure cart is rendered correctly on page load
//     renderCart();

//     // Add event listeners for "Add to Cart" buttons
//     document.querySelectorAll('.add-to-cart').forEach(button => {
//         button.addEventListener('click', (event) => {
//             const cardBody = event.target.closest('.card-body');
            
//             if (cardBody) {
//                 const id = cardBody.getAttribute('data-id');
//                 const productName = cardBody.getAttribute('data-name');
//                 const productPrice = parseFloat(cardBody.getAttribute('data-price'));
//                 const productImage = cardBody.getAttribute('data-image');

//                 console.log('Add to Cart:', { id, productName, productPrice, productImage });

//                 if (id && productName) {
//                     addToCart(id, productName, productPrice, productImage);
//                 }
//             }
//         });
//     });
    
// });

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
//                 <p>ID: ${item.id}</p>
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

//     console.log(cart);

//     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

//     // Reattach event listeners after rendering
//     document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
//         button.addEventListener('click', () => {
//             updateQuantity(button.dataset.id, 1);
//         });
//     });

//     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
//         button.addEventListener('click', () => {
//             updateQuantity(button.dataset.id, -1);
//         });
//     });

//     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
//         button.addEventListener('click', () => {
//             removeFromCart(button.dataset.id);
//         });
//     });
// }

// function updateQuantity(id, change) {
//     console.log(`Update quantity called for product ${id} with change ${change}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     let product = cart.find(item => item.id === parseInt(id));

//     if (product) {
//         product.quantity += change;

//         if (product.quantity <= 0) {
//             cart = cart.filter(item => item.id !== parseInt(id));
//         }

//         localStorage.setItem('cart', JSON.stringify(cart));
//         renderCart();
//     }
// }

// function removeFromCart(id) {
//     console.log(`Remove from cart called for product ${id}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     cart = cart.filter(item => item.id !== parseInt(id));
//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart(); // Ensure cart view updates
// }



// document.addEventListener('DOMContentLoaded', () => {
//     // Ensure cart is rendered correctly on page load
//     renderCart();

//     // Add event listeners for "Add to Cart" buttons
//     document.querySelectorAll('.add-to-cart').forEach(button => {
//         button.addEventListener('click', (event) => {
//             const cardBody = event.target.closest('.card-body');
            
//             if (cardBody) {
//                 const id = cardBody.getAttribute('data-id');                            
//                 const productName = cardBody.getAttribute('data-name');
//                 const productPrice = parseFloat(cardBody.getAttribute('data-price'));
//                 const productImage = cardBody.getAttribute('data-image');

//                 console.log('Add to Cart:', { id, productName, productPrice, productImage });

//                 if (id && productName) {
//                     addToCart(id, productName, productPrice, productImage);
//                 }
//             }
//         });
//     });
// });

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
//         button.addEventListener('click', () => {
//             updateQuantity(button.dataset.id, 1);
//         });
//     });

//     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
//         button.addEventListener('click', () => {
//             updateQuantity(button.dataset.id, -1);
//         });
//     });

//     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
//         button.addEventListener('click', () => {
//             removeFromCart(button.dataset.id);
//         });
//     });
// }

// // Update product quantity in the cart
// function updateQuantity(id, change) {
//     console.log(`Update quantity called for product ${id} with change ${change}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     let product = cart.find(item => item.id === parseInt(id));

//     if (product) {
//         product.quantity += change;

//         if (product.quantity <= 0) {
//             cart = cart.filter(item => item.id !== parseInt(id));
//         }

//         localStorage.setItem('cart', JSON.stringify(cart));
//         renderCart();
//     }
// }

// function removeFromCart(id) {
//     console.log(`Remove from cart called for product ${id}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     cart = cart.filter(item => item.id !== parseInt(id));
//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart(); // Ensure cart view updates
// }







// document.addEventListener('DOMContentLoaded', () => {
    

//     // Add event listeners for "Add to Cart" buttons
//     document.querySelectorAll('.add-to-cart').forEach(button => {
//         button.addEventListener('click', (event) => {
//             const card = event.target.closest('.card');
//             if (card) {
//                 const cardBody = card.querySelector('.card-body');
                
//                 const id = cardBody ? cardBody.getAttribute('data-id') : null;                            
//                 const productName = cardBody ? cardBody.getAttribute('data-name') : null;
//                 const productPrice = cardBody ? parseFloat(cardBody.getAttribute('data-price')) : 0;
//                 const productImage = cardBody ? cardBody.getAttribute('data-image') : '';            
//                 console.log('Add to Cart:', { id, productName, productPrice, productImage });

//                 if (id && productName) {
//                     addToCart(id, productName, productPrice, productImage);
//                 }
//             }
//         });
//     });
//     // Ensure cart is rendered correctly on page load
//     renderCart();

//     //  document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
//     //     button.addEventListener('click', () => {
//     //         console.log('Increase quantity for product ID:', button.dataset.id);
//     //         updateQuantity(button.dataset.id, 1);
//     //     });
//     // });

//     // document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
//     //     button.addEventListener('click', () => {
//     //         console.log('Decrease quantity for product ID:', button.dataset.id);
//     //         updateQuantity(button.dataset.id, -1);
//     //     });
//     // });

//     // document.querySelectorAll('.cart-item .remove-item').forEach(button => {
//     //     button.addEventListener('click', () => {
//     //         console.log('Remove from cart for product ID:', button.dataset.id);
//     //         removeFromCart(button.dataset.id);
//     //     });
//     // });
//     // // Attach event listeners for cart buttons after rendering
//     // renderCart(); // Reattach event listeners after rendering
// });

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
//     console.log(cart)
//     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

//     // Reattach event listeners after rendering
//     document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
//         button.addEventListener('click', () => {
//             console.log('Increase quantity for product ID:', button.dataset.id);
//             updateQuantity(button.dataset.id, 1);
//         });
//     });

//     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
//         button.addEventListener('click', () => {
//             console.log('Decrease quantity for product ID:', button.dataset.id);
//             updateQuantity(button.dataset.id, -1);
//         });
//     });

//     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
//         button.addEventListener('click', () => {
//             console.log('Remove from cart for product ID:', button.dataset.id);
//             removeFromCart(button.dataset.id);
//         });
//     });
// }

// // Update product quantity in the cart
// function updateQuantity(id, change) {
//     console.log(`Update quantity called for product ${id} with change ${change}`);
//     const cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const product = cart.find(item => item.id === parseInt(id));

//     if (product) {
//         product.quantity += change;

//         if (product.quantity <= 0) {
//             cart = cart.filter(item => item.id !== parseInt(id));
//             // removeFromCart(productId);
//         }
//         //  else {
//             localStorage.setItem('cart', JSON.stringify(cart));
//             renderCart();
//         // }
//     }
// }

// function removeFromCart(id) {
//     console.log(`Remove from cart called for product ${id}`);
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     cart = cart.filter(item => item.id !== parseInt(id));
//     localStorage.setItem('cart', JSON.stringify(cart));
//     renderCart(); // Ensure cart view updates
// }



// // document.addEventListener('DOMContentLoaded', () => {
// //     // Ensure cart is rendered correctly on page load
// //     renderCart();

// //     // Add event listeners for "Add to Cart" buttons
// //     document.querySelectorAll('.add-to-cart').forEach(button => {
// //         button.addEventListener('click', (event) => {
// //             const card = event.target.closest('.card');
// //             if (card) {
// //                 const cardBody = card.querySelector('.card-body');
// //                 const productId = cardBody.getAttribute('data-id');
// //                 const productName = cardBody.getAttribute('data-name');
// //                 const productPrice = parseFloat(cardBody.getAttribute('data-price'));
// //                 const productImage = cardBody.getAttribute('data-image');

// //                 addToCart(productId, productName, productPrice, productImage);
// //             }
// //         });
// //     });

// //     // Attach event listeners for cart buttons
// //     document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
// //         button.addEventListener('click', () => updateQuantity(button.dataset.id, 1));
// //     });

// //     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
// //         button.addEventListener('click', () => updateQuantity(button.dataset.id, -1));
// //     });

// //     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
// //         button.addEventListener('click', () => removeFromCart(button.dataset.id));
// //     });
// // });

// // // Add product to the cart
// // function addToCart(id, name, price, image) {
// //     let cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     let product = cart.find(item => item.id === parseInt(id));

// //     if (product) {
// //         product.quantity += 1; // Increase quantity if already in cart
// //     } else {
// //         cart.push({
// //             id: parseInt(id),
// //             name: name,
// //             price: price,
// //             image: image,
// //             quantity: 1
// //         });
// //     }

// //     localStorage.setItem('cart', JSON.stringify(cart));
// //     renderCart(); // Ensure cart view updates
// // }

// // // Render cart items and total price
// // function renderCart() {
// //     console.log('Render cart called');
// //     const cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     const cartItemsContainer = document.getElementById('cart-items');
// //     const cartTotalContainer = document.getElementById('total-price');

// //     if (!cartItemsContainer || !cartTotalContainer) {
// //         console.error('Cart items container or total price container not found.');
// //         return;
// //     }

// //     if (cart.length === 0) {
// //         cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
// //         cartTotalContainer.innerHTML = '';
// //         return;
// //     }

// //     let total = 0;
// //     cartItemsContainer.innerHTML = cart.map(item => {
// //         const itemTotal = item.price * item.quantity;
// //         total += itemTotal;
// //         return `
// //             <div class="cart-item">
// //                 <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
// //                 <h4>${item.name}</h4>
// //                 <p>Price: ₹${item.price}</p>
// //                 <p>Quantity: ${item.quantity}</p>
// //                 <p>Total: ₹${itemTotal.toFixed(2)}</p>
// //                 <button class="increase-quantity" data-id="${item.id}">+</button>
// //                 <button class="decrease-quantity" data-id="${item.id}">-</button>
// //                 <button class="remove-item" data-id="${item.id}">Remove</button>
// //                 <hr>
// //             </div>
// //         `;
// //     }).join('');

// //     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

// //     // Reattach event listeners after rendering
// //     document.querySelectorAll('.cart-item .increase-quantity').forEach(button => {
// //         button.addEventListener('click', () => updateQuantity(button.dataset.id, 1));
// //     });

// //     document.querySelectorAll('.cart-item .decrease-quantity').forEach(button => {
// //         button.addEventListener('click', () => updateQuantity(button.dataset.id, -1));
// //     });

// //     document.querySelectorAll('.cart-item .remove-item').forEach(button => {
// //         button.addEventListener('click', () => removeFromCart(button.dataset.id));
// //     });
// // }

// // // Update product quantity in the cart
// // function updateQuantity(productId, change) {
// //     console.log(`Update quantity called for product ${productId} with change ${change}`);
// //     const cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     const product = cart.find(item => item.id === parseInt(productId));

// //     if (product) {
// //         product.quantity += change;

// //         if (product.quantity <= 0) {
// //             removeFromCart(productId);
// //         } else {
// //             localStorage.setItem('cart', JSON.stringify(cart));
// //             renderCart();
// //         }
// //     }
// // }

// // // Remove product from the cart
// // function removeFromCart(productId) {
// //     console.log(`Remove from cart called for product ${productId}`);
// //     let cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     cart = cart.filter(item => item.id !== parseInt(productId));
// //     localStorage.setItem('cart', JSON.stringify(cart));
// //     renderCart(); // Ensure cart view updates
// // }



// // function renderCart() {
// //     console.log('Render cart called');
// //     const cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     const cartItemsContainer = document.getElementById('cart-items');
// //     const cartTotalContainer = document.getElementById('total-price');

// //     if (!cartItemsContainer || !cartTotalContainer) {
// //         console.error('Cart items container or total price container not found.');
// //         return;
// //     }

// //     if (cart.length === 0) {
// //         cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
// //         cartTotalContainer.innerHTML = '';
// //         return;
// //     }

// //     let total = 0;
// //     cartItemsContainer.innerHTML = cart.map(item => {
// //         const itemTotal = item.price * item.quantity;
// //         total += itemTotal;
// //         return `
// //             <div class="cart-item">
// //                 <img src="../images/${item.image}" alt="${item.name}" style="width: 100px;" />
// //                 <h4>${item.name}</h4>
// //                 <p>Price: ₹${item.price}</p>
// //                 <p>Quantity: ${item.quantity}</p>
// //                 <p>Total: ₹${itemTotal.toFixed(2)}</p>
// //                 <button class="increase-quantity" data-id="${item.id}">+</button>
// //                 <button class="decrease-quantity" data-id="${item.id}">-</button>
// //                 <button class="remove-item" data-id="${item.id}">Remove</button>
// //                 <hr>
// //             </div>
// //         `;
// //     }).join('');

// //     cartTotalContainer.innerHTML = `<h3>Total: ₹${total.toFixed(2)}</h3>`;

// //     // Reattach event listeners after rendering
// //     attachEventListeners();
// // }

// // function updateQuantity(productId, change) {
// //     console.log(`Update quantity called for product ${productId} with change ${change}`);
// //     const cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     const product = cart.find(item => item.id === parseInt(productId));

// //     if (product) {
// //         product.quantity += change;

// //         if (product.quantity <= 0) {
// //             removeFromCart(productId);
// //         } else {
// //             localStorage.setItem('cart', JSON.stringify(cart));
// //             renderCart();  // Ensure cart view updates
// //         }
// //     }
// // }

// // function removeFromCart(productId) {
// //     console.log(`Remove from cart called for product ${productId}`);
// //     let cart = JSON.parse(localStorage.getItem('cart')) || [];
// //     cart = cart.filter(item => item.id !== parseInt(productId));
// //     localStorage.setItem('cart', JSON.stringify(cart));
// //     renderCart();  // Ensure cart view updates
// // }

// // function attachEventListeners() {
// //     console.log('Attaching event listeners');
// //     document.querySelectorAll('.increase-quantity').forEach(button => {
// //         button.addEventListener('click', () => updateQuantity(button.dataset.id, 1));
// //     });

// //     document.querySelectorAll('.decrease-quantity').forEach(button => {
// //         button.addEventListener('click', () => updateQuantity(button.dataset.id, -1));
// //     });

// //     document.querySelectorAll('.remove-item').forEach(button => {
// //         button.addEventListener('click', () => removeFromCart(button.dataset.id));
// //     });
// // }

// // document.addEventListener('DOMContentLoaded', () => {
// //     renderCart();
// // });