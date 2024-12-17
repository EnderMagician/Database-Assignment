document.addEventListener("DOMContentLoaded", () => {
    const role = localStorage.getItem('Role');
    const bodyElement = document.body;

    if (role === 'ADMIN') {
        bodyElement.style.paddingTop = '170px';
        loadNavbar('../html/AdminNavbar.html');
    } else {
        bodyElement.style.paddingTop = '110px';
        loadNavbar('../html/UserNavbar.html');
    }

    function loadNavbar(navbarPath) {
        fetch(navbarPath)
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.text();
            })
            .then(html => {
                document.getElementById('main-navbar').innerHTML = html;

                const currentPage = window.location.pathname.split('/').pop();
                const links = document.querySelectorAll('#main-navbar a');
                links.forEach(link => {
                    if (link.getAttribute('href') === currentPage) {
                        link.classList.add('active');
                    } else {
                        link.classList.remove('active');
                    }
                });
            })
            .catch(err => console.error('Failed to load navbar:', err));
    }
});