

const floatingContainers = document.querySelectorAll(".floating-container");
const overlay = document.querySelector(".overlay");
const addPatientButton = document.querySelector(".add-patient-button");
const addPatientContainer = document.querySelector(".add-patient-container");
const addDoctorButton = document.querySelector(".add-doctor-button");
const addDoctorContainer = document.querySelector(".add-doctor-container");
const editIcons = document.querySelectorAll(".edit-icon");

overlay.addEventListener('click', event => {
    overlay.style.display = "none";
    for(let i = 0; i < floatingContainers.length; i++) {
        const item = floatingContainers.item(i);
        item.style.display = "none";
    }
});

try {
    addPatientButton.addEventListener('click', event => {
        overlay.style.display = "block";
        addPatientContainer.style.display = "block";
    });
} catch(ex) {

}

try {
    addDoctorButton.addEventListener('click', event => {
        overlay.style.display = "block";
        addDoctorContainer.style.display = "block";
    });
} catch(ex) {

}

try {
    for (const editIcon of editIcons) {
        editIcon.addEventListener('click', event => {
            event.preventDefault();

            const editDeleteContainer = document.createElement('div');
            editDeleteContainer.innerHTML = `
            <div class='edit-button'>Edit</div>
            <div class='delete-button'>Delete</div>
            `
            editDeleteContainer.style.display = 'flex';
            editDeleteContainer.style.flexDirection = 'column';
            editDeleteContainer.style.alignItems = 'center';
            editDeleteContainer.style.position = 'absolute';

            editIcon.appendChild(editDeleteContainer);
        })
    }
} catch(ex) {

}