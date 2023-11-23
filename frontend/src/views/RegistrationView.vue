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
                <input type="password" id="confirmPassword" v-model="confirmPassword" placeholder="Confirm Password" required>
            </div>
            <button type="submit" class="btn-next">Complete Registration</button>
        </form>
    </div>
</template>

<script lang="ts">
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
        submitRegister() {
            //TODO
        }
    }
}
</script>

<style scoped>
.register-container {
    max-width: 500px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

.register-form .form-group {
    margin-bottom: 15px;
}

input[type="text"], input[type="email"], input[type="tel"], input[type="password"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="text"]:focus, input[type="email"]:focus, input[type="tel"]:focus, input[type="password"]:focus {
    border-color: #666;
    outline: none;
}

.btn-next {
    width: 100%;
    padding: 10px;
    background-color: #333;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.btn-next:hover {
    background-color: #555;
}
</style>
