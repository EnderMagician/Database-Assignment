document.addEventListener('DOMContentLoaded', (event) => {
    // Get all the modal buttons
    const modalBtns = document.querySelectorAll('.openModalBtn');
    const modal = document.getElementById('updateModal');
    const span = document.getElementsByClassName("close")[0];
    const modalAssigningId = document.getElementById('modalAssigningId');
    const modalFormContainer = document.getElementById('modalFormContainer');

    // Add click event listeners to each button
    modalBtns.forEach(btn => {
        btn.onclick = function () {
            modal.style.display = "block";
            const assigningId = this.getAttribute('data-assigning-id');
            modalAssigningId.textContent = assigningId;

            // Create the forms dynamically (You'll need to handle this part)
            // ...
        }
    });

    // Close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});