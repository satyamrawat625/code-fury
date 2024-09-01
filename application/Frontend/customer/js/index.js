function addToCart(product) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    const existingProductIndex = cart.findIndex(item => item.id === product.id);
    // const existingProductIndex = cart.findIndex(item => item.name === product.name);
    if (existingProductIndex > -1) {
        cart[existingProductIndex].quantity += 1;
    } else {
        product.quantity = 1;
        cart.push(product);
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    alert(`${product.name} added to cart!`);

}

document.querySelectorAll('.add-to-cart').forEach(button => {
    button.addEventListener('click', function () {
        const productCard = this.closest('.card-body');
        const product = {
            id: parseInt(productCard.dataset.id),
            name: productCard.dataset.name,
            price: parseFloat(productCard.dataset.price),
            image: productCard.dataset.image,
            quantity: 1
        };
        console.log(product);
        addToCart(product);
    });
});

function redirectToPayment(planName, price) {
    window.location.href = `payment.html?plan=${encodeURIComponent(planName)}&price=${price}`;
}


// function addToCart(product) {
//     let cart = JSON.parse(localStorage.getItem('cart')) || [];
//     const existingProductIndex = cart.findIndex(item => item.name === product.name);
//     if (existingProductIndex > -1) {
//         cart[existingProductIndex].quantity += 1;
//     } else {
//         product.quantity = 1;
//         cart.push(product);
//     }

//     localStorage.setItem('cart', JSON.stringify(cart));
//     alert(`${product.name} added to cart!`);
// }

// document.querySelectorAll('.add-to-cart').forEach(button => {
//     button.addEventListener('click', function () {
//         const productCard = this.closest('.card-body');
//         const product = {
//             id: parseInt(productCard.dataset.id),
//             name: productCard.dataset.name,
//             price: parseFloat(productCard.dataset.price),
//             image: productCard.dataset.image
//         };
//         addToCart(product);
//     });
// });


// function redirectToPayment(planName, price) {
//     window.location.href = `payment.html?plan=${encodeURIComponent(planName)}&price=${price}`;
// }       