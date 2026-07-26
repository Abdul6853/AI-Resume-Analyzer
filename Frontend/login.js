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

        const response = await axios.post(
            "http://localhost:8080/user/login",
            loginRequest
        );

        // Save logged-in user
        localStorage.setItem(
            "user",
            JSON.stringify(response.data)
        );

        message.style.color = "green";
        message.innerText = "Login Successful!";

        setTimeout(() => {
            window.location.href = "index.html";
        }, 1000);

    }
    catch (error) {

    console.log(error.response.data);

    alert(JSON.stringify(error.response.data));

    message.style.color = "red";

    message.innerText = JSON.stringify(error.response.data);
    }

}