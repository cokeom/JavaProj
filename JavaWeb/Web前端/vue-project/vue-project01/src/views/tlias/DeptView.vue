<template>
  <div>
    <el-container style="border: 1px solid #eee">
      <el-header style="font-size: 40px; background-color: rgb(238, 241, 246)">tlias 智能学习辅助系统</el-header>

      <el-container>
        <el-aside width="230px" style="border: 1px solid #eee">
          <el-menu :default-openeds="['1']">
            <el-submenu index="1">
              <template slot="title"> 系统信息管理 </template>
              <el-menu-item-group>
                <el-menu-item index="1-1">
                  <router-link to="/dept">
                    部门管理
                  </router-link>
                </el-menu-item>
                <el-menu-item index="1-2">
                  <router-link to="/emp">
                    员工管理
                  </router-link>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-main>
          <!-- 增加部门 -->
          <el-row>
            <el-button type="primary">新增部门</el-button>
          </el-row>
          <br>
          <!-- 表格 -->
          <el-table :data="tableData" border style="text-align: center;">
            <el-table-column prop="name" label="序号" width="180">
            </el-table-column>
            <el-table-column prop="deptname" label="部门名称" width="180">
            </el-table-column>
            <el-table-column prop="updatetime" label="最后操作时间" width="180">
            </el-table-column>
            <el-table-column label="操作">
              <el-button type="primary" size="mini">编辑</el-button>
              <el-button type="danger" size="mini">删除</el-button>
            </el-table-column>
          </el-table>
          <br>

          <!-- 分页 -->
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="currentPage4" :page-sizes="[100, 200, 300, 400]" :page-size="100"
            layout="total, sizes, prev, pager, next, jumper" :total="400">
          </el-pagination>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      tableData: [],
      searchItem: {
        user: "",
        region: "",
        entrydate: [],
      },
    };
  },
  methods: {
    onSubmit: function () {
      console.log("submit!");
    },
    handleSizeChange(value) {
      alert("每页记录变化" + value);
    },
    handleCurrentChange(value) {
      alert("页码变化" + value);
    }
  },
  mounted() {
    // 发送异步请求
    axios.get("https://mock.apifox.cn/m1/3128855-0-default/emp/list").then((result) => {
      this.tableData = result.data.data;
    })
  },
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
/* a{
  text-decoration: none;
  color: #000;
}
.router-link-active {
  text-decoration: none;
  color: #000;
} */
</style>