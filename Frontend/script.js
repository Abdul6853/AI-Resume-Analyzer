const user = JSON.parse(localStorage.getItem("user"));

if (!user) {
    window.location.href = "login.html";
}

// Logout
const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", logout);

function logout() {

    localStorage.removeItem("user");
    localStorage.removeItem("token");

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

    const token = localStorage.getItem("token");

    const formData = new FormData();
    formData.append("file", file);

    try {

        // Upload Resume
        const response = await axios.post(
            "http://localhost:8080/resume/user/" + user.userId + "/upload",
            formData,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        );

        console.log(response.data);

        const resumeId = response.data.resumeId;

        console.log("Resume ID:", resumeId);

        // Create Job Description
        const jobDescription = {
            companyName: "Unknown",
            jobTitle: "Java Developer",
            jobDescription: document.getElementById("jobDescription").value
        };

        const jdResponse = await axios.post(
            `${API_URL}/jd/add`,
            jobDescription,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        );

        const jdId = jdResponse.data.jdId;

        console.log("Job Description ID:", jdId);

        // Analyze Resume
        const analysisResponse = await axios.post(
            `${API_URL}/analysis/${resumeId}/${jdId}`,
            {},
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        );

        const analysis = analysisResponse.data;

        console.log(analysis);

        document.getElementById("atsScore").innerText = analysis.atsScore;
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

        alert("Resume Uploaded Successfully!");
        alert("Analysis Completed!");

    } catch (error) {

        console.error(error);

        if (error.response) {
            console.log(error.response.data);
            alert(JSON.stringify(error.response.data));
        } else {
            alert("Upload Failed!");
        }
    }
}