<script setup>
import {onMounted, ref} from 'vue'
import {getStuCount, getStuEduCount} from '@/api/report'
import {ElMessage} from "element-plus";
import * as echarts from 'echarts'

// 响应式数据
// 学生人数柱状图的响应式数据
const chartRef = ref(null);
//学生学历饼状图的响应式数据
const pieChartRef = ref(null);
//学生人数柱状图实例
let chartInstance = null;
//学生学历饼状图实例
let pieChartInstance = null;

//获得数据，渲染统计图的函数
const getData = async () => {
  const [stuCount, stuEduCount] = await Promise.all([
    getStuCount(),
    getStuEduCount()
  ]);
  if (stuCount.code) {
    renderChart(stuCount.data);
  } else {
    ElMessage.error("学生人数获取失败")
  }
  if (stuEduCount.code) {
    renderPieChart(stuEduCount.data)
  } else {
    ElMessage.error("学生学历获取失败")
  }
};
// 渲染柱状图
const renderChart = (data) => {
  const option = {
    title: {
      text: '班级人数统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      formatter: '{b}: {c}人'
    },
    xAxis: {
      type: 'category',
      data: data.clazzList,
      axisLabel: {
        fontSize: 15,
        rotate: 25, // 倾斜45度
        interval: 0 // 强制显示所有标签
      }
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        fontSize: 15
      },
      nameTextStyle: {
        fontSize: 15,
        fontWeight: 'bold'
      }
    },
    series: [
      {
        name: '班级人数',
        data: data.dataList,
        type: 'bar'
      }]
  }
  if (!chartInstance){
    chartInstance = echarts.init(chartRef.value);
  }
  chartInstance.setOption(option);
};
// 渲染饼状图
const renderPieChart = (data) => {
  const option = {
    title: {
      text: '学员学历统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c}人'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: data.map(item => item.name)
    },
    series: [
      {
        name: '学历',
        type: 'pie',
        radius: ['40%', '70%'],
        data: data,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
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
      }
    ]
  }
  if (!pieChartInstance){
    pieChartInstance = echarts.init(pieChartRef.value);
  }
  pieChartInstance.setOption(option);
};

//钩子函数
onMounted(() => {
  getData();
  // 窗口大小变化时重绘图表
  window.addEventListener('resize', () => {
    chartInstance?.resize();
    pieChartInstance?.resize();
  })
})
</script>

<template>
  <h1>学员信息统计</h1>
  <div style="display: flex; grid-gap: 10px; margin-top: 10px; ">
    <!--  学生人数柱状图-->
    <div class="chart-container" style="flex: 1">
      <div ref="chartRef" style="width: 100%; height: 600px;"></div>
    </div>
    <!--    学生学历统计饼状图-->
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