const user = JSON.parse(localStorage.getItem("user"));

if (!user) {
    window.location.href = "login.html";
}
// Logout 
const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", logout);

function logout() {

    localStorage.removeItem("user");

    alert("Logged out successfully!");

    window.location.href = "login.html";
}

const analyzeBtn = document.getElementById("analyzeBtn");

analyzeBtn.addEventListener("click", analyzeResume);

async function analyzeResume() {

    const file = document.getElementById("resumeFile").files[0];

    if (!file) {
        alert("Please choose a PDF.");
        return;
    }

    const formData = new FormData();

    formData.append("file", file);

    try {

        const response = await axios.post(
            `http://localhost:8080/resume/user/${userId}/upload`,
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        );

        console.log(response.data);
        const resumeId = response.data.resumeId;

        console.log("Resume ID:", resumeId);

        const jobDescription = {
            companyName: "Unknown",
            jobTitle: "Java Developer",
            jobDescription: document.getElementById("jobDescription").value
        };

        const jdResponse = await axios.post(
            "http://localhost:8080/jd/add",
            jobDescription
        );

        const jdId = jdResponse.data.jdId;

        console.log("Job Description ID:", jdId);

        const analysisResponse = await axios.post(
            `http://localhost:8080/analysis/${resumeId}/${jdId}`);

        const analysis = analysisResponse.data;

        document.getElementById("atsScore").innerText = analysis.atsScore;
        console.log(analysis);

        document.getElementById("matchedSkills").innerText =
        analysis.matchedSkills.join(", ");

        document.getElementById("missingSkills").innerText =
        analysis.missingSkills.join(", ");

        document.getElementById("strengths").innerText =
        analysis.strengths;

        document.getElementById("weaknesses").innerText =
        analysis.weaknesses;

        document.getElementById("suggestions").innerText =
        analysis.suggestions;

        alert("Analysis Completed!");
        alert("Resume Uploaded Successfully!");

    }
    catch(error){

        console.error(error);

        alert("Upload Failed!");

    }

}