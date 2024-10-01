<script setup>
import { ref } from 'vue'
const id = ref('')
const password = ref('')
const email = ref('')

const submit = () => {
}
</script>

<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="section section-signup"
      style="background-image: linear-gradient( rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3) ), url('assets/img/bg11.jpg'); background-size: cover; background-position: top center; min-height: 700px; ">
      <div class="container">
        <div class="row">
          <div class="card card-signup" data-background-color="orange">
            <form class="form" @submit.prevent="submit">
              <div class="card-header text-center">
                {{ id }}{{ password }}{{ email }}
                <h3 class="card-title title-up">Sign Up</h3>
                <div class="social-line">
                  <a href="#" class="btn btn-neutral btn-facebook btn-icon btn-round">
                    <i class="fab fa-facebook-square"></i>
                  </a>
                  <a href="#" class="btn btn-neutral btn-twitter btn-icon btn-lg btn-round">
                    <i class="fab fa-twitter"></i>
                  </a>
                  <a href="#" class="btn btn-neutral btn-google btn-icon btn-round">
                    <i class="fab fa-google-plus"></i>
                  </a>
                </div>
              </div>
              <div class="card-body">
                <div class="input-group no-border">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="now-ui-icons users_circle-08"></i>
                    </span>
                  </div>
                  <!-- id part -->
                  <input v-model="id" type="text" class="form-control" placeholder="ID">
                </div>
                <div class="input-group no-border">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="now-ui-icons text_caps-small"></i>
                    </span>
                  </div>
                  <input v-model="password" type="password" placeholder="Password" class="form-control" />
                </div>
                <div class="input-group no-border">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="now-ui-icons ui-1_email-85"></i>
                    </span>
                  </div>
                  <input v-model="email" type="text" class="form-control" placeholder="Email...">
                </div>
                <!-- If you want to add a checkbox to this form, uncomment this code -->
                <!-- <div class="checkbox">
								<input id="checkboxSignup" type="checkbox">
									<label for="checkboxSignup">
									Unchecked
									</label>
								</div> -->
              </div>
              <div class="card-footer text-center">
                <button class="btn btn-neutral btn-round btn-lg">Login</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
form {
  /* background-color:  */
}
</style>