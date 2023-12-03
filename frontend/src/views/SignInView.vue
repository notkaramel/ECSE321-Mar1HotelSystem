<template>
    <div class="login-container">
        <h2>Log In</h2>
        <form @submit.prevent="submitSignIn" class="login-form">
            <div class="form-group">
                <input type="email" id="email" v-model="email" placeholder="Email address" required>
            </div>
            <div v-if="loginError" class="login-error">
                {{ loginError }}
            </div>
            <div class="form-group">
                <input type="password" id="password" v-model="password" placeholder="Password" required>
                <div class="form-options">
                    <a href="#" class="forgot-password">Forgot your password?</a>
                    <router-link to="/registration" class="register-link">Register</router-link>
                </div>
            </div>
            <button type="submit" class="btn-signin">Sign In</button>
        </form>
    </div>
</template>

<script lang="ts">
import axios from 'axios';

// Correctly access the environment variable
const backendUrl = import.meta.env.VITE_BACKEND;

export default {
    data() {
        return {
            email: '',
            password: '',
            loginError: '', // Added to store login error messages
        };
    },
    methods: {

        async submitSignIn() {
            try {
                // Try to log in as a customer
                let response = await axios.get(`${backendUrl}/customer/${this.email}`);
                if (response.status === 200 && response.data.password === this.password) {
                    localStorage.setItem('userEmail', this.email);
                    localStorage.setItem('userPassword', this.password);
                    localStorage.setItem('userRole', 'customer');
                    this.$router.push('/').then(() => {
                        window.location.reload();
                    });

                    return; // Stop further execution if login is successful
                }
            } catch (error: unknown) {
                if (axios.isAxiosError(error)) {
                    if (error.response && error.response.status === 404) {
                        this.loginError = 'User not found. Please check your credentials.';
                    } else {
                        this.loginError = 'An error occurred during login';
                    }
                } else {
                    this.loginError = 'An unexpected error occurred';
                }
                console.error('Login error', error);
            }

            try {
                // If not found in customer, try employee
                let response = await axios.get(`${backendUrl}/employee/${this.email}`);
                if (response.status === 200 && response.data.password === this.password) {
                    localStorage.setItem('userEmail', this.email);
                    localStorage.setItem('userPassword', this.password);
                    localStorage.setItem('userRole', 'employee');
                    this.$router.push('/employee').then(() => {
                        window.location.reload();
                    });
                    return; // Stop further execution if login is successful
                }
            } catch (error: unknown) {
                if (axios.isAxiosError(error)) {
                    if (error.response && error.response.status === 404) {
                        this.loginError = 'User not found. Please check your credentials.';
                    } else {
                        this.loginError = 'An error occurred during login';
                    }
                } else {
                    this.loginError = 'An unexpected error occurred';
                }
                console.error('Login error', error);
            }

            try {
                // If not found in employee, try manager
                let response = await axios.get(`${backendUrl}/managers/${this.email}`);
                if (response.status === 200 && response.data.password === this.password) {
                    localStorage.setItem('userEmail', this.email);
                    localStorage.setItem('userPassword', this.password);
                    localStorage.setItem('userRole', 'manager');
                    this.$router.push('/manager').then(() => {
                        window.location.reload();
                    });
                    return; // Stop further execution if login is successful
                }
            } catch (error: unknown) {
                if (axios.isAxiosError(error)) {
                    if (error.response && error.response.status === 404) {
                        this.loginError = 'User not found. Please check your credentials.';
                    } else {
                        this.loginError = 'An error occurred during login';
                    }
                } else {
                    this.loginError = 'An unexpected error occurred';
                }
                console.error('Login error', error);
            }
        },



        onRegister() {
            this.$emit('register');
        }
    }
}
</script>



<style scoped>
.login-container {
    max-width: 350px;
    margin: 2rem auto;
    padding: 2rem;
    background: white;
    border: none;
    border-radius: 0.5rem;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    /* Example font */
    color: #333;
    text-align: center;
}

.login-error {
    color: red;
    margin-top: 10px;
}

h2 {
    font-size: 1.5rem;
    color: #333;
    margin-bottom: 1.5rem;
}

.login-form .form-group {
    margin-bottom: 1rem;
}

input[type="email"],
input[type="password"] {
    width: 100%;
    padding: 0.75rem 1rem;
    border: 1px solid #ddd;
    border-radius: 0.375rem;
    box-sizing: border-box;
}

input[type="email"]:focus,
input[type="password"]:focus {
    border-color: #333;
    /* Adjust as per the color theme of the hotel */
    box-shadow: 0 0 0 2px rgba(0, 0, 0, 0.1);
    /* subtle focus ring */
    outline: none;
}

.form-options {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 0.9rem;
    margin-top: 1rem;
}

.forgot-password,
.register-link {
    color: #0056b3;
    /* Adjust the color to match the hotel's theme */
    text-decoration: none;
}

.forgot-password:hover,
.register-link:hover {
    text-decoration: underline;
}

.btn-signin {
    width: 100%;
    padding: 0.75rem 1rem;
    background-color: #0056b3;
    /* Example button color */
    color: white;
    border: none;
    border-radius: 0.375rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.btn-signin:hover {
    background-color: #003d82;
    /* Darker shade on hover */
}
</style>

