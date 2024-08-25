// Open and close login popup
function openLoginPopup() {
    document.getElementById('loginModal').style.display = 'block';
}

function closeLoginPopup() {
    document.getElementById('loginModal').style.display = 'none';
}

// Open and close signup popup
function openSignupPopup() {
    document.getElementById('signupModal').style.display = 'block';
    closeLoginPopup();
}

function closeSignupPopup() {
    document.getElementById('signupModal').style.display = 'none';
}

// Signup Form Validation
const signupForm = document.getElementById('signupForm');
signupForm.addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const phoneNumber = document.getElementById('phoneNumber').value;

    if (!validateEmail(email)) {
        alert('Please enter a valid email without spaces.');
        return;
    }

    if (!validatePhoneNumber(phoneNumber)) {
        alert('Phone number must start with 6, 7, 8, or 9 and contain 10 digits.');
        return;
    }

    if (!validatePassword(password)) {
        alert('Password must contain at least one capital letter, one digit, one special character, and no spaces.');
        return;
    }

    alert('Signup successful!');
    signupForm.submit();
});

function validateEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email) && !/\s/.test(email);
}

function validatePhoneNumber(phoneNumber) {
    const phoneRegex = /^[6-9]\d{9}$/;
    return phoneRegex.test(phoneNumber);
}

function validatePassword(password) {
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@_-])[A-Za-z\d@_-]{8,}$/;
    return passwordRegex.test(password) && !/\s/.test(password);
}
