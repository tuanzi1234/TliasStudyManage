<!--定义js, 控制模板部分的数据和行为(JS)-->
<script setup>
import { ref } from 'vue' //引入响应式数据函数ref

const message = ref("Hello World")

</script>


<!--模板部分, 控制页面结构（HTML）-->
<template>

  <h1>{{ message }}</h1>

</template>


 <!--当前组件的样式（CSS）-->
<style scoped>

</style>
