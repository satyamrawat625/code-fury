document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault(); 
    const uname = document.getElementById('username').value.trim();
    const pwd = document.getElementById('password').value.trim();

    if (uname === "admin" && pwd === "pass") {
        window.location.href = "../../admin/admin.html";
    } 
    else if (uname === "user" && pwd === "password") {
        window.location.href = "../html/custlogout.html"; 
    } 
    else {
        alert("Invalid username or password!"); 
    }
});