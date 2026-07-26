const registerBtn = document.getElementById("registerBtn");

registerBtn.addEventListener("click", registerUser);

async function registerUser() {

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const message = document.getElementById("message");

    message.innerText = "";

    // Validation
    if (name === "" || email === "" || password === "") {
        message.style.color = "red";
        message.innerText = "Please fill all fields.";
        return;
    }

    const user = {
        name: name,
        email: email,
        password: password
    };

    try {

        const response = await axios.post(
            "http://localhost:8080/user/register",
            user
        );

        message.style.color = "green";
        message.innerText = "Registration Successful! Redirecting to Login...";

        setTimeout(() => {
            window.location.href = "login.html";
        }, 2000);

    }
    catch(error){

    const message = document.getElementById("message");

    message.style.color = "red";

    message.innerText = error.response.data.message;

}

    }

