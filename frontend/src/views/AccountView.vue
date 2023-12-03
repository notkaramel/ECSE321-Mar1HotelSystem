<template>
  <div class="max-w-2xl mx-auto p-6 bg-white rounded-lg shadow-md">
    <!-- Account Information Section -->
    <div class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Account Information</h2>
      <div class="grid gap-4">
        <p><span class="font-medium">First Name:</span> {{ customer.firstName }}</p>
        <p><span class="font-medium">Last Name:</span> {{ customer.lastName }}</p>
        <p><span class="font-medium">Email:</span> {{ customer.email }}</p>
        <p><span class="font-medium">Phone Number:</span> {{ customer.phoneNumber }}</p>
        <button class="edit-info-btn" @click="edit = true">Edit Info
        </button>
      </div>
    </div>

    <div class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Delete Account</h2>
      <button class="btn-delete" @click="confirmDelete">Delete Account</button>
    </div>

    <!-- Edit Information Section -->
    <div v-if="edit" class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Edit Personal Information</h2>
      <form class="grid gap-4">
        <input class="form-input" type="text" placeholder="First Name" v-model="customer.firstName" />
        <input class="form-input" type="text" placeholder="Last Name" v-model="customer.lastName" />
        <input class="form-input" type="tel" placeholder="Phone Number" v-model="customer.phoneNumber" />
        <div class="flex space-x-4">
          <button type="button" class="flex-1 btn-save" @click="saveInfo">Save Changes</button>
          <button type="button" class="flex-1 btn-cancel" @click="edit = false">Cancel</button>
        </div>
      </form>
    </div>

    <!-- Change Password Section -->
    <div class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Change Password</h2>
      <form class="grid gap-4">
        <input class="form-input" type="password" placeholder="Old Password" v-model="password.old" />
        <div class="relative">
          <input class="form-input pr-10" :type="showPassword ? 'text' : 'password'" placeholder="New Password"
            v-model="password.new" />
          <span class="absolute inset-y-0 right-0 flex items-center pr-3">
            <button type="button" class="text-sm font-semibold text-gray-600 hover:text-gray-800"
              @click="toggleShowPassword">
              {{ showPassword ? 'Hide' : 'Show' }}
            </button>
          </span>
        </div>
        <input class="form-input" :type="showPassword ? 'text' : 'password'" placeholder="Confirm New Password"
          v-model="password.confirm" />
        <button type="button" class="btn-save" @click="changePassword">Update Password</button>
      </form>
    </div>
  </div>
  <!-- Logout Button -->
  <div class="flex justify-center mt-6">
    <button class="btn-logout" @click="logout">Logout</button>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
const backendUrl = import.meta.env.VITE_BACKEND;

export default {
  data() {
    return {
      edit: false,
      showPassword: false,
      customer: {
        firstName: '',
        lastName: '',
        email: '',
        address: '',
        phoneNumber: '',
        password: '',
      },
      password: {
        old: '',
        new: '',
        confirm: '',
      },
    };
  },
  methods: {
    toggleShowPassword() {
      this.showPassword = !this.showPassword;
    },
    async changePassword() {
      if (this.password.new !== this.password.confirm) {
        alert("The new passwords do not match.");
        return;
      }

      try {
        const response = await axios.put(`${backendUrl}/customer/${this.customer.email}`, {
          firstName: this.customer.firstName,
          lastName: this.customer.lastName,
          email: this.customer.email,
          phoneNumber: this.customer.phoneNumber,
          password: this.password.new,
        });

        if (response.status === 200) {
          alert("Your password has been successfully changed.");
          // Reset the password fields after successful change
          this.password.old = '';
          this.password.new = '';
          this.password.confirm = '';
        } else {
          alert("There was a problem changing your password.");
        }
      } catch (error) {
        console.error('Error changing password', error);
        alert('Failed to change password. Please try again later.');
      }
    },

    async saveInfo() {
      try {
        const response = await axios.put(`${backendUrl}/customer/${this.customer.email}`, {
          firstName: this.customer.firstName,
          lastName: this.customer.lastName,
          email: this.customer.email,
          phoneNumber: this.customer.phoneNumber,
          password: this.customer.password,
        });

        if (response.status === 200) {
          this.customer = response.data; // Update the customer data with the response
          alert("Your information has been successfully updated.");
          this.edit = false;
        } else {
          alert("There was a problem updating your information.");
        }
      } catch (error) {
        console.error('Error saving customer information', error);
        alert('Failed to update information. Please try again later.');
      }
    },

    logout() {
      localStorage.removeItem('userEmail');
      localStorage.removeItem('userRole');
      // Add any other cleanup you need here

      this.$router.push('/').then(() => {
        window.location.reload();
      });
    },
    async fetchCustomerInfo(email: String) {
      try {
        const response = await axios.get(`${backendUrl}/customer/${email}`);
        if (response.status === 200) {
          this.customer = response.data;
        }
      } catch (error) {
        console.error('Error fetching customer data', error);
      }
    },

  confirmDelete() {
      if (confirm("Are you sure you want to delete your account? This action cannot be undone.")) {
        this.deleteAccount();
      }
    },
    async deleteAccount() {
      try {
        const response = await axios.delete(`${backendUrl}/customer/${this.customer.email}`);

        if (response.status === 200) {
          alert("Your account has been successfully deleted.");

          this.$router.push('/');
        } else {
          alert("There was a problem deleting your account.");
        }
      } catch (error) {
        console.error('Error deleting account', error);
        alert('Failed to delete account. Please try again later.');
      }
    },
  },
  created() {
    this.fetchCustomerInfo(this.$route.params.email.toString());
  },
};

</script>

<style scoped>
.form-input {
  padding: 0.75rem 1rem;
  border: 1px solid #ccc;
  /* Subtle border color */
  border-radius: 0.5rem;
  /* Matched rounded corners */
  margin-bottom: 1rem;
  /* Consistent spacing */
  box-sizing: border-box;
  width: 100%;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  /* Consistent font */
}

/* You may need to adjust the colors to match the hotel's branding */
.btn-save {
  padding: 0.75rem 1rem;
  background-color: #0056b3;
  /* Example primary color */
  color: white;
  border: none;
  border-radius: 0.5rem;
  margin-bottom: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-weight: bold;
  /* Adding bold font for button text */
}

.btn-delete {
  padding: 0.75rem 1rem;
  background-color: #dc2626;
  /* Red background for delete button */
  color: white;
  border: none;
  border-radius: 0.5rem;
  margin-bottom: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-weight: bold;
}

.btn-delete:hover {
  background-color: #b91c1c;
  /* Darker shade on hover */
}

.btn-logout {
    padding: 0.75rem 1rem;
    background-color: #64748b; /* Grayish color for logout button */
    color: white;
    border: none;
    border-radius: 0.5rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
    font-weight: bold;
  }

  .btn-logout:hover {
    background-color: #475569; /* Darker shade on hover */
  }
.edit-info-btn {
  padding: 0.75rem 1rem;
  /* Standard padding */
  background-color: #0056b3;
  /* Blue background */
  color: white;
  /* White text */
  border: none;
  /* No border */
  border-radius: 0.375rem;
  /* Rounded corners */
  cursor: pointer;
  /* Pointer cursor on hover */
  transition: background-color 0.3s;
  /* Smooth transition for hover effect */
  font-weight: bold;
  /* Bold font */
  margin-top: 1rem;
  /* Margin on top */
}

.edit-info-btn:hover {
  background-color: #003d82;
  /* Darker blue on hover */
}

.btn-save:hover {
  background-color: #003d82;
  /* Darker shade on hover */
}

.btn-cancel {
  padding: 0.75rem 1rem;
  background-color: #EF4444;
  /* Consistent with Tailwind red-500 */
  color: white;
  border: none;
  border-radius: 0.5rem;
  margin-bottom: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-weight: bold;
  /* Adding bold font for button text */
}

.btn-cancel:hover {
  background-color: #cc3636;
  /* Darker shade on hover */
}</style>