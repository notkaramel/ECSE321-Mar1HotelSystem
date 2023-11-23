<template>
    <div class="login-container">
        <h2>Log In</h2>
        <form @submit.prevent="submitSignIn" class="login-form">
            <div class="form-group">
                <input type="email" id="email" v-model="email" placeholder="Email address" required>
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
export default {
    emits: ['signIn', 'register'],
    data() {
        return {
            email: '',
            password: '',
        };
    },
    methods: {
        submitSignIn() {
            this.$emit('signIn', { email: this.email, password: this.password });
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
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    text-align: center;
}

h2 {
    margin-bottom: 20px;
}

.login-form .form-group {
    position: relative;
    margin-bottom: 20px;
}

input[type="email"], input[type="password"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="email"]:focus, input[type="password"]:focus {
    border-color: #666;
    outline: none;
}

.form-options {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 0.9em;
}

.forgot-password, .register-link {
    color: #333;
    text-decoration: none;
}

.forgot-password:hover, .register-link:hover {
    text-decoration: underline;
}


.btn-signin {
    width: 100%;
    padding: 10px;
    background-color: #333;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.btn-signin:hover {
    background-color: #555;
}
</style>
