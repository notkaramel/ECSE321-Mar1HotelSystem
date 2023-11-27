<template>
    <div class="register-container">
        <h2>Register</h2>
        <form @submit.prevent="submitRegister" class="register-form">
            <div class="form-group">
                <input type="text" id="firstName" v-model="firstName" placeholder="First Name" required>
            </div>
            <div class="form-group">
                <input type="text" id="lastName" v-model="lastName" placeholder="Last Name" required>
            </div>
            <div class="form-group">
                <input type="email" id="email" v-model="email" placeholder="Email address" required>
            </div>
            <div class="form-group">
                <input type="tel" id="phoneNumber" v-model="phoneNumber" placeholder="Phone Number" required>
            </div>
            <div class="form-group">
                <input type="password" id="password" v-model="password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <input type="password" id="confirmPassword" v-model="confirmPassword" placeholder="Confirm Password"
                    required>
            </div>
            <button type="submit" class="btn-next">Complete Registration</button>
        </form>
    </div>
</template>

<script lang="ts">
const backendUrl = import.meta.env.VITE_BACKEND;
import axios from 'axios';

export default {
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            phoneNumber: '',
            password: '',
            confirmPassword: ''
        };
    },
    methods: {
    async submitRegister() {
        // Check if the password and the confirmation password match
        if (this.password !== this.confirmPassword) {
            alert('Oops! Passwords do not match.');
            return;
        }

        // If the passwords match, call the createCustomer method to create a new customer
        // Convert phoneNumber to a number, or use 0 if it's not available
        const createdCustomer = await this.createCustomer(
            this.firstName,
            this.lastName,
            this.email,
            this.phoneNumber ? Number(this.phoneNumber) : 0,
            this.password
        );

        // Log the created customer
        console.log(createdCustomer);
    },

    // This method sends a POST request to create a new customer
    async createCustomer(firstName: String, lastName :String , email :String, phoneNumber :number, password:String) {
        // The body of the request is an object containing the customer data
        let createdCustomer = await axios.post(backendUrl + "/customer/", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password })
            .then(response => {
                return response.data;
            })
            .catch(err => {
                console.log(err);
                alert(err.response["data"]);
            });

        return createdCustomer;
    }
}
}
</script>


<style scoped>
.register-container {
    max-width: 500px;
    margin: 2rem auto;
    padding: 2rem;
    background: white;
    border: none;
    border-radius: 0.5rem;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Example font */
    color: #333;
}

h2 {
    text-align: center;
    font-size: 1.5rem;
    color: #333;
    margin-bottom: 1.5rem;
}

.register-form .form-group {
    margin-bottom: 1rem;
}

input[type="text"],
input[type="email"],
input[type="tel"],
input[type="password"] {
    width: 100%;
    padding: 0.75rem 1rem;
    border: 1px solid #ddd;
    border-radius: 0.375rem;
    box-sizing: border-box;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="tel"]:focus,
input[type="password"]:focus {
    border-color: #333; /* Adjust as per the color theme of the hotel */
    box-shadow: 0 0 0 2px rgba(0, 0, 0, 0.1); /* subtle focus ring */
    outline: none;
}

.btn-next {
    width: 100%;
    padding: 0.75rem 1rem;
    background-color: #0056b3; /* Example button color */
    color: white;
    border: none;
    border-radius: 0.375rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.btn-next:hover {
    background-color: #003d82; /* Darker shade on hover */
}
</style>
