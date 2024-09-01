document.getElementById('signupForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    const phoneNumber = document.getElementById('phoneNumber').value.trim();
    const address = document.getElementById('address').value.trim();

    if (username.length < 3) {
        alert("Username must be at least 3 characters long.");
        return;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address.");
        return;
    }

    const passwordPattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
    if (!passwordPattern.test(password)) {
        alert("Password must be at least 8 characters long and include at least one number and one special character.");
        return;
    }

    const phonePattern = /^\d{10}$/;
    if (!phonePattern.test(phoneNumber)) {
        alert("Phone number must be 10 digits long.");
        return;
    }

    if (address === "") {
        alert("Address cannot be empty.");
        return;
    }

    alert("Sign up successful!");
});