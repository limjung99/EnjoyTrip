<script setup>
import { ref } from 'vue'
import { onClickOutside } from '@vueuse/core'
import { useLoginStore } from "@/stores/login";
const id = ref('')
const password = ref('')

const store = useLoginStore()
const target = ref(null)


onClickOutside(target, event => {
  store.closeModal()
})
const submit = () => {
  store.tryLogin(id.value,password.value)
}

</script>

<template>
  <div class=" container modal-overlay" style="position:inherit; margin-top: 5%">
    <div class="row">
      <div ref="target" class="card card-signup" data-background-color="orange">
        <form class="form" @submit.prevent="submit">
          <div class="card-header text-center">
            <h3 class="card-title title-up">Login</h3>
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
</template>

<style scoped>
.modal-overlay{
  z-index: 10;
}

</style>