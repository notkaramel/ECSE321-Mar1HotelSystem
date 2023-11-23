<template>
  <div class="max-w-2xl mx-auto p-6 bg-white rounded-lg shadow-md">
    <!-- Account Information Section -->
    <div class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Account Information</h2>
      <div class="grid gap-4">
        <p><span class="font-medium">First Name:</span> {{ customer.firstName }}</p>
        <p><span class="font-medium">Last Name:</span> {{ customer.lastName }}</p>
        <p><span class="font-medium">Email:</span> {{ customer.email }}</p>
        <p><span class="font-medium">Address:</span> {{ customer.address }}</p>
        <p><span class="font-medium">Phone Number:</span> {{ customer.phone }}</p>
        <button class="mt-4 px-4 py-2 bg-black-900 text-black rounded hover:bg-black-700 focus:outline-none" @click="edit = true">Edit Info</button>
      </div>
    </div>

    <!-- Edit Information Section -->
    <div v-if="edit" class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Edit Personal Information</h2>
      <form class="grid gap-4">
        <input class="form-input" type="text" placeholder="First Name" v-model="customer.firstName" />
        <input class="form-input" type="text" placeholder="Last Name" v-model="customer.lastName" />
        <input class="form-input" type="email" placeholder="Email" v-model="customer.email" />
        <input class="form-input" type="text" placeholder="Address" v-model="customer.address" />
        <input class="form-input" type="tel" placeholder="Phone Number" v-model="customer.phone" />
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
          <input class="form-input pr-10" :type="showPassword ? 'text' : 'password'" placeholder="New Password" v-model="password.new" />
          <span class="absolute inset-y-0 right-0 flex items-center pr-3">
            <button type="button" class="text-sm font-semibold text-gray-600 hover:text-gray-800" @click="toggleShowPassword">
              {{ showPassword ? 'Hide' : 'Show' }}
            </button>
          </span>
        </div>
        <input class="form-input" :type="showPassword ? 'text' : 'password'" placeholder="Confirm New Password" v-model="password.confirm" />
        <button type="button" class="btn-save" @click="changePassword">Update Password</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      edit: false,
      showPassword: false,
      customer: {
        firstName: 'Jane',
        lastName: 'Doe',
        email: 'jane.doe@example.com',
        address: '123 Maple Street',
        phone: '555-1234',
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
    changePassword() {
      // Handle password change logic
    },
    saveInfo() {
      // Handle save information logic
      this.edit = false;
    },
  },
};
</script>

<style scoped>
.form-input {
  padding: 0.5rem 1rem;
  border: 1px solid #ddd;
  border-radius: 0.375rem;
  margin-bottom: 0.75rem;
}

.btn-save {
  padding: 0.5rem 1rem;
  background-color: #10B981; /* Tailwind green-500 */
  color: white;
  border-radius: 0.375rem;
  margin-bottom: 0.75rem;
}

.btn-cancel {
  padding: 0.5rem 1rem;
  background-color: #EF4444; /* Tailwind red-500 */
  color: white;
  border-radius: 0.375rem;
  margin-bottom: 0.75rem;
}
</style>
