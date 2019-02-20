<template>
  <el-dialog title="dialog" :show-close="false" :visible="dialogFormVisible">
    <el-form :model="form">
      <el-form-item label="group name" :label-width="formLabelWidth">
        <el-input v-model="form.name" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="temperature num" :label-width="formLabelWidth">
        <el-input v-model="form.temperature" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="humidity num" :label-width="formLabelWidth">
        <el-input v-model="form.humidity" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="thunder num" :label-width="formLabelWidth">
        <el-input v-model="form.thunder" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="pressure num" :label-width="formLabelWidth">
        <el-input v-model="form.pressure" auto-complete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" :label-width="formLabelWidth">
      <el-button @click="cancelForm">取 消</el-button>
      <el-button type="primary" @click="upload(form)">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Bus from '../eventBus'

export default {
  name: 'group-form',
  data() {
    return {
      form: {
        name: '',
        temperature: 0,
        humidity: 0,
        thunder: 0,
        pressure: 0
      },
      formLabelWidth: '120px'
    }
  },
  methods: {
    cancelForm: function() {
      this.$emit('cancelForm')
    },
    upload(form) {
      this.$axios
        .post('http://' + myIp + '/api/addNewGroup', {
          createUser: 'huzehao',
          name: this.form.name,
          groupDetails: [
            { type: 'temperature', sensorNum: this.form.temperature },
            { type: 'humidity', sensorNum: this.form.humidity },
            { type: 'thunder', sensorNum: this.form.thunder },
            { type: 'pressure', sensorNum: this.form.pressure }
          ]
        })
        .then(res => {
          console.log(res)
          var result;
          Bus.$emit('refresh', res.data)
          
        })
        .catch(err => {
          console.log(err)
        })
      //this.dialogFormVisible = false
       //location.reload()
    }
  },
  props: ['dialogFormVisible']
}
</script>

