const tableBody = document.querySelector(".table-body");

window.onload = loadPatients();

function loadPatients() {
    
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const jsonText = this.responseText;
            const jsonResult = JSON.parse(jsonText);
            displayPatients(jsonResult);
            createEventListenersForRows(jsonResult);
        }
    }
    
    xhttp.open("GET", "./loadPatients", true);
    xhttp.send();
}

function displayPatients(patientJson) {
 for(const patient of patientJson) {
    tableBody.innerHTML += `
    <tr>
        <td class="id-table-data">${patient["patient_id"]}</td>
        <td>${patient["patient_name"]}</td>
        <td>${patient["date_of_hospitalization"]}</td>
        <td>${patient["date_of_discharge"]}</td>
        <td>${patient["diagnosis_value"]}</td>
        <td>${patient["doctor_name"]}</td>
        <td><div class="edit-icon"><img src="./Images/icon-edit.png"></div></td>
    </tr>
    `;
 }
}

// ----------------------
// DISPLAY PATIENT INFO
// ----------------------

// let overlay = document.querySelector(".overlay");
let patientInfoContainer = document.querySelector(".patient-info-container");

function createEventListenersForRows(jsonResult) {

    const tableRows = document.querySelectorAll("#main tr");
    for (const tableRow of tableRows) {
        tableRow.addEventListener('click', event => {
            event.preventDefault();

            const patientIdInfo = tableRow.querySelector(".id-table-data").innerHTML;

            let patientInfoObject;
            for (patientObject of jsonResult) {
                if (patientObject["patient_id"] == patientIdInfo) {
                    patientInfoObject = patientObject;
                }
            }

            const patientNameInfo = patientInfoObject["patient_name"];
            const dateofHospitalizationInfo = patientInfoObject["date_of_hospitalization"];
            const dateOfDischargeInfo = patientInfoObject["date_of_discharge"];
            const symptomsInfo = patientInfoObject["symptoms"];
            const diagnosisInfo = patientInfoObject["diagnosis_value"];
            const treatmentCourseInfo = patientInfoObject["treatment_course_value"];

            overlay.style.display = "block";
            patientInfoContainer.style.display = "block";

            patientInfoContainer.innerHTML = `
            <div class="name patient-name">
                ${patientNameInfo}
            </div>
            <div class="patient-info">
                <div class="patient-info-left">
                    <p>Patient ID: ${patientIdInfo}</p>
                    <p>Date of Hospitalization: ${dateofHospitalizationInfo}</p>
                    <p>Date of Discharge: ${dateOfDischargeInfo}</p>
                </div>
                <div class="patient-info-right">
                    <p>Symptoms: ${symptomsInfo}</p>
                    <p>Diagnosis: ${diagnosisInfo}</p>
                    <p>Treatment Course: ${treatmentCourseInfo}</p>
                </div>
            </div>
            `
        });
    }
}


// ----------------------
// ADD PATIENT 
// ----------------------

const patientNameInput = document.querySelector("input[name='patient-name']");
const dateOfHospitalizationInput = document.querySelector("input[name='date-of-hospitalization']");
const dateOfDischargeInput = document.querySelector("input[name='date-of-discharge']");
const assignedDoctorInput = document.querySelector("select[name='assigned-doctor']");
const symptomsInput = document.querySelector("input[name='symptoms']");
const diagnosisInput = document.querySelector("input[name='diagnosis']");
const treatmentCourseInput = document.querySelector("input[name='treatment-course']");
const addPatientSubmitButton = document.querySelector(".add-patient-submit");
const addResponseMessage = document.querySelector(".add-response-message");

addPatientSubmitButton.addEventListener('click', event => {
    event.preventDefault();

    const patientName = patientNameInput.value;
    const dateofHospitalization = dateOfHospitalizationInput.value;
    const dateOfDischarge = dateOfDischargeInput.value;
    const assignedDoctor = assignedDoctorInput.value;
    const symptoms = symptomsInput.value;
    const diagnosis = diagnosisInput.value;
    const treatmentCourse = treatmentCourseInput.value;

    if (patientName == "" ) {
        patientNameInput.style.border = "2px solid red";
        patientNameInput.style.color = "red";
        patientNameInput.setAttribute("placeholder", "Cannot be empty!");
    } else {
        patientNameInput.style.border = "1px solid black";
        patientNameInput.style.color = "black";
        
        // const formData = new FormData();
        // formData.append('patientName', patientName);
        // formData.append('dateofHospitalization', dateofHospitalization);
        // formData.append('dateOfDischarge', dateOfDischarge);
        // formData.append('assignedDoctor', assignedDoctor);
        // formData.append('symptoms', symptoms);
        // formData.append('diagnosis', diagnosis);
        // formData.append('treatmentCourse', treatmentCourse);

        // const parameters = {
        //     'patientName': patientName,
        //     'dateOfHospitalizarion': dateofHospitalization,
        //     'dateOfDischarge': dateOfDischarge,
        //     'assignedDoctor': assignedDoctor,
        //     'symptoms': symptoms,
        //     'diagnosis': diagnosis,
        //     'treatmentCourse': treatmentCourse
        // };

        const parameters = 'patientName=' + (patientName) + '&'
                         + 'dateOfHospitalization=' + dateofHospitalization + '&'
                         + 'dateOfDischarge=' + dateOfDischarge + '&'
                         + 'assignedDoctor=' + assignedDoctor + '&'
                         + 'symptoms=' + symptoms + '&'
                         + 'diagnosis=' + diagnosis + '&'
                         + 'treatmentCourse=' + treatmentCourse;
                        
        const encodedParams = encodeURI(parameters);

        sendAddPatientRequest(encodedParams);
        console.log(encodedParams);
    }
})

function sendAddPatientRequest(parameters) {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const responseText = document.createElement('p');
            addResponseMessage.innerHTML = this.responseText;
        }
    }

    // xhttp.open("POST", "./addPatient");
    // xhttp.send(parameters);

    xhttp.open("GET", "./addPatient?" + parameters);
    xhttp.send();
}