// ----------------------
// LOAD DOCTOR
// ----------------------

const tableBody = document.querySelector(".table-body");

window.onload = loadDoctors();

function loadDoctors() {
    
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const jsonText = this.responseText;
            const jsonResult = JSON.parse(jsonText);
            displayDoctors(jsonResult);
        }
    }
    
    xhttp.open("GET", "./loadDoctors", true);
    xhttp.send();
}

function displayDoctors(doctorJson) {
 for(const doctor of doctorJson) {
    tableBody.innerHTML += `
    <tr>
        <td>${doctor["doctor_id"]}</td>
        <td>${doctor["doctor_name"]}</td>
        <td>${doctor["department_name"]}</td>
        <td><img class="edit-icon" src="./Images/icon-edit.png"></td>
    </tr>
    `;
 };
}

// ----------------------
// ADD DOCTOR 
// ----------------------

const doctorNameInput = document.querySelector(".doctor-name-input");
const departmentInput = document.querySelector(".department-input");
const doctorPhotoInput = document.querySelector(".doctor-photo-input");
const addDoctorSubmitButton = document.querySelector(".add-doctor-submit");
// const addDoctorContainer = document.querySelector(".add-doctor-container");

addDoctorSubmitButton.addEventListener("click", event => {
    event.preventDefault();
    const doctorName = doctorNameInput.value;
    const department = departmentInput.value;
    const doctorPhoto = doctorPhotoInput.files[0];

    const formData = new FormData();
    formData.append("doctorName", doctorName);
    formData.append("department", department);
    formData.append("doctorPhoto", doctorPhoto);

    if (doctorName == "") {
        doctorNameInput.style.border = "2px solid red";
        doctorNameInput.style.color = "red";
        doctorNameInput.setAttribute("placeholder", "Cannot be empty!");
    }
    if (doctorName != "") {
        doctorNameInput.style.border = "1px solid black";
        doctorNameInput.style.color = "black";

        
        sendAddDoctorRequest();
    }
})

function sendAddDoctorRequest() {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const responseText = this.responseText;
            const responseMessage = document.createElement("p");
            responseMessage.innerHTML = responseText;
            responseMessage.style.fontSize = "2rem";
            responseMessage.style.marginTop = "20px";
            // addDoctorContainer.appendChild(responseMessage);
        }
    }
    xhttp.open("POST", "./addDoctor", true);
    xhttp.send(formData);
}