document.addEventListener('DOMContentLoaded', () => {
    // Get all the modal buttons and the update modal
    const modalBtns = document.querySelectorAll('.openModalBtn');
    const updateModal = document.getElementById('updateModal');
    const closeUpdateModalBtn = document.getElementsByClassName("close")[0];
    const modalVehicleId = document.getElementById('modalVehicleId');
    const registrationNumberInput = document.getElementById('registrationNumberInput');
    const updateVehicleForm = document.getElementById('updateVehicleForm');

    // Open the update modal
    modalBtns.forEach(btn => {
        btn.onclick = function () {
            // Display the update modal
            updateModal.style.display = "block";

            // Set the registration number in the modal
            const vehicleId = this.getAttribute('data-vehicle-id');
            modalVehicleId.textContent = vehicleId;
            registrationNumberInput.value = vehicleId;
        };
    });

    // Close the update modal when the close button is clicked
    closeUpdateModalBtn.onclick = function () {
        updateModal.style.display = "none";
    };

    // Close the update modal when clicking outside the modal
    window.onclick = function (event) {
        if (event.target == updateModal) {
            updateModal.style.display = "none";
        }
    };

    // Handle form submission for the update modal
    updateVehicleForm.onsubmit = function (event) {
        event.preventDefault(); // Prevent default form submission

        // Simulate an update success without reloading the page
        alert("Vehicle updated successfully!");

        // Close the update modal
        updateModal.style.display = "none";

        // Optional: Update the table dynamically without reloading the page (AJAX example)
        // updateVehicleInTable(); <-- Implement this function if needed
    };
});
