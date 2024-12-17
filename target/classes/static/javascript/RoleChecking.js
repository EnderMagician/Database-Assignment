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

                // Ensure active link logic runs *after* navbar is fully loaded
                setTimeout(() => {
                    highlightActiveLink();
                }, 0);
            })
            .catch(err => console.error('Failed to load navbar:', err));
    }

    function highlightActiveLink() {
        const currentPage = window.location.pathname.split('/').pop() || 'index.html';
        const links = document.querySelectorAll('#main-navbar a');

        links.forEach(link => {
            const linkHref = link.getAttribute('href').split('/').pop();
            if (linkHref === currentPage) {
                link.classList.add('active');
            } else {
                link.classList.remove('active');
            }
        });
    }
});
