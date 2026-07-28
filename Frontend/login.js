// password peek removed

async function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const message = document.getElementById("message");

    if (email === "" || password === "") {

        message.style.color = "red";
        message.innerText = "Please fill all fields.";

        return;
    }

    const loginRequest = {
        email,
        password
    };

    try {

        const API_URL = "https://ample-patience-production-077f.up.railway.app";

const response = await axios.post(
    `${API_URL}/user/login`,
    loginRequest
);

        // Save JWT
        localStorage.setItem("token", response.data.token); // or response.data.token if you rename it

        // Save only user details
        localStorage.setItem(
        "user",
        JSON.stringify({
        userId: response.data.userId,
        name: response.data.name,
        email: response.data.email
        })
        );

        message.style.color = "green";
        message.innerText = "Login Successful!";

        setTimeout(() => {
            window.location.href = "index.html";
        }, 1000);

    }
    catch (error) {

    console.log(error.response.data);

    

    message.style.color = "red";

   message.innerText = error.response.data.message;
    }

}