document.addEventListener("DOMContentLoaded", () => {
  fetch('../html/AdminNavbar.html')
      .then(response => response.text())
      .then(html => {
          document.getElementById('main-navbar').innerHTML = html;

          // Highlight the active link
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
