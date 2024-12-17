document.getElementById('adminLoginBtn').addEventListener('click', () => {
    event.preventDefault();
    window.location.href = "../static/html/AdminHome.html";
});

document.getElementById('userLoginBtn').addEventListener('click', () => {
    event.preventDefault();
    window.location.href = "../static/html/UserHome.html";
});