document.getElementById('adminLoginBtn').addEventListener('click', () => {
    event.preventDefault();
    localStorage.setItem('Role', 'ADMIN'); 
    window.location.href = "../html/AdminHome.html";
});

document.getElementById('userLoginBtn').addEventListener('click', () => {
    event.preventDefault();
    localStorage.setItem('Role', 'USER'); 
    window.location.href = "../html/UserHome.html";
});