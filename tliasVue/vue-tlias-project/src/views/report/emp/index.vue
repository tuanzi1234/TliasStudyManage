<script setup>
import {ref, onMounted} from "vue";
import {getEmpCount, getEmpSexCount} from "@/api/report.js"
import {ElMessage} from "element-plus";
import * as echarts from 'echarts'

// 柱状图容器引用
const chartRef = ref(null);
// 饼图容器引用
const pieChartRef = ref(null)
// 饼图实例
let pieChartInstance = null
// 柱状图实例
let chartInstance = null

// 获取数据并渲染图表
const loadChartData = async () => {
  const [jobRes, genderRes] = await Promise.all([
    getEmpCount(),
    getEmpSexCount()
  ]);
  if (jobRes.code) {
    renderChart(jobRes.data);
  } else {
    ElMessage.error("职位人数获取失败")
  }
  if (genderRes.code) {
    renderPieChart(genderRes.data)
  } else {
    ElMessage.error("员工性别获取失败")
  }
}

// 渲染柱状图
const renderChart = (data) => {
  // 配置柱状图选项
  const option = {
    title: {
      text: '员工职位统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      formatter: '{b}: {c}人'
    },
    xAxis: {
      type: 'category',
      data: data.jobList,
      axisLabel: {
        fontSize: 15
      }
    },
    yAxis: {
      type: 'value',
      name: '人数',
      axisLabel: {
        fontSize: 15
      },
      nameTextStyle: {
        fontSize: 15, // 坐标轴名称字体大小
        fontWeight: 'bold'
      }
    },
    series: [{
      name: '员工数量',
      type: 'bar',
      data: data.dataList,
    }]
  }
  // 渲染图表
  if (!chartInstance) {
    chartInstance = echarts.init(chartRef.value)
  }
  chartInstance.setOption(option)
}
//渲染饼图
const renderPieChart = (genderData) => {
  // 配置饼图选项
  const option = {
    title: {
      text: '员工性别统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c}人'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: genderData.map(item => item.name)
    },
    series: [{
      name: '员工性别',
      type: 'pie',
      radius: ['40%', '70%'],
      data: genderData,
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      label: {
        fontSize: 14,
        formatter: '{b}: {c}人'
      },
      itemStyle: {
        borderRadius: 10,
        borderColor: '#fff',
        borderWidth: 2
      }
    }]
  };
  // 初始化或更新饼图
  if (!pieChartInstance) {
    pieChartInstance = echarts.init(pieChartRef.value);
  }
  pieChartInstance.setOption(option);
}

// 页面加载时初始化
onMounted(() => {
  loadChartData()
  // 窗口大小变化时重绘图表
  window.addEventListener('resize', () => {
    chartInstance?.resize();
    pieChartInstance?.resize();
  })
})


</script>

<template>
  <h1>员工信息统计</h1>
  <div style="display: flex; grid-gap: 10px; margin-top: 10px">
    <!--  员工人数职位人数柱状图-->
    <div class="chart-container" style="flex: 1">
      <div ref="chartRef" style="width: 100%; height: 500px;"></div>
    </div>
    <!-- 员工性别饼图-->
    <div class="chart-container" style="flex: 1">
      <div ref="pieChartRef" style="width: 100%; height: 500px;"></div>
    </div>
  </div>
</template>

<style scoped>
.chart-container {
  padding: 20px;
  background: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>