<template>
  <div class="container">
    <RouterView></RouterView>
  </div>
</template>   

<script setup>
    import { onMounted, ref } from "vue";
    import { getPostsBySizeNPage } from "@/api/PostApi.js" 
    const data = ref([]);
    const pageNum = ref(1);
   
    const successHandler = (data)=>{
      const totalNum = data.total;
      pageNum.value = totalNum / 10; // divide by ten 
      data.value = items;
    }

    const failHandler = ()=>{
        console.log("fail http get");
    }

    // Data initialize 
    onMounted(()=>{
        getPostsBySizeNPage(1,10,successHandler,failHandler);
    })
</script>

<style scoped>
.container{
    background-color: white;
    width: 80rem;
    margin-top: 100px;
    border-radius: 1%;
    margin-bottom: 1000px;
    height: initial;
}

table{
    color : black;
}
</style>
