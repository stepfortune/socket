<template>
  <div>
    <div v-for="g in groupData" :key="g" class="box-card">
      <button class="card-btn" @click="useThisGroup(g.id)">
        <el-card>
          <div slot="header" class="clearfix">
            <div class="groupName">{{g.name}}</div>
            <div class="groupDetail">
              <span>{{g.createUser}}</span><br> {{new Date(g.createTime).toLocaleString()}}
            </div>
          </div>
          <el-row style="margin : 0" v-for="detail in g.groupDetails" :key="detail.type">
            <el-col :span="12">
              <span style="float : left; margin-top : 20px">{{detail.type}}</span>
            </el-col>
            <el-col :span="12">
              <span style="float : left; margin-top : 20px">{{detail.sensorNum}}</span>
            </el-col>
          </el-row>
        </el-card>
      </button>
    </div>
    <el-button plain type="prime" @click="addNewGroup()">add new Group</el-button>
    <group-form :dialogFormVisible="dialogFormVisible" v-on:cancelForm="dialogFormVisible=false"></group-form>

  </div>
</template>


<script>
//import group from '../assets/data/group.json'
import GroupForm from './GroupForm.vue'
import Bus from '../eventBus'

export default {
  name: 'show-group-page',
  data() {
    return {
      groupData: [],
      formLabelWidth: '120px',
      dialogFormVisible: false
    }
  },
  mounted() {
    this.getAllGroup()
    Bus.$on('refresh', data => {
      this.groupData = data
      this.dialogFormVisible = false
    })
  },
  components: { GroupForm },
  methods: {
    getAllGroup: function() {
      this.$axios
        .get('http://' + myIp +'/api/getAllGroup')
        .then(res => {
          this.groupData = res.data
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
    addNewGroup: function() {
      this.dialogFormVisible = true
    },
    sendId() {
      Bus.$emit('id', 1)
    },
    useThisGroup: function(id) {
      this.$router.push({ name: 'client-page', params: { groupId: id } })
    }
  }
}
</script>

<style>
.card-btn {
  background-color: white;
  border: 0ch;
}
.groupName {
  float: left;
  text-align: left;
  vertical-align: middle;
  font-size: 2em;
}
.groupDetail {
  float: left;
  padding-left: 20px;
  margin-left: 30px;
  text-align: left;
}

.text {
  font-size: 14px;
}

.item {
  left: px;
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}

.box-card {
  float: left;
  margin: 10px;
}
</style>