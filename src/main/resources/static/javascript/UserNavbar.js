document.addEventListener("DOMContentLoaded", () => {
    fetch('../html/UserNavbar.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-navbar').innerHTML = html;

            const currentPage = window.location.pathname.split('/').pop();
            const links = document.querySelectorAll('#main-navbar a');

            links.forEach(link => {
                if (link.getAttribute('href') === currentPage) {
                    link.parentElement.classList.add('active');
                } else {
                    link.parentElement.classList.remove('active');
                }
            });
        })
        .catch(err => console.warn('Failed to load navbar', err));
});
