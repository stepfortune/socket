<template>
  <div>
    <el-tabs style="margin-top:80px" type="card" v-model="tapActiveName" @tap-click="handleTapClick">
      <el-tab-pane label="temperature" name="temperature">
        <div>
          <el-row :gutter="10">
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :tableData="temperatureData.slice(0,temperatureData.length/2)" :clientType="temperature">
              </client-config-table>
            </el-col>
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="temperatureData.slice(temperatureData.length/2,temperatureData.length)">
              </client-config-table>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>

      <el-tab-pane label="humidity" name="humidity">
        <div>
          <el-row :gutter="10">
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="humidityData.slice(0,humidityData.length/2)">
              </client-config-table>
            </el-col>
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="humidityData.slice(humidityData.length/2, humidityData.length)">
              </client-config-table>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="pressure" name="pressure">
        <div>
          <el-row :gutter="10">
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="pressureData.slice(0, pressureData.length/2)">
              </client-config-table>
            </el-col>
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="pressureData.slice(pressureData.length/2, pressureData.length)">
              </client-config-table>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="thunder" name="thunder">
        <div>
          <el-row :gutter="10">
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="thunderData.slice(0,thunderData.length/2)">
              </client-config-table>
            </el-col>
            <el-col :span="12">
              <client-config-table @updateSendingMsg="updateSendingMsg" @updateClientState="updateClientState" :table-data="thunderData.slice(thunderData.length/2,thunderData.length)">
              </client-config-table>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="observe" name="observe">
        <client-observe-table :groupId="groupId"></client-observe-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
//import client_groups from '../assets/data/client_groups.json'
import ClientConfigTable from './common/ClientConfigTable.vue'
import ClientObserveTable from './common/ClientObserveTable.vue'
import Bus from '../eventBus'

export default {
  name: 'client-page',
  data() {
    return {
      temperatureData: null, //client_groups['temperature'],
      thunderData: null, //client_groups['thunder'],
      pressureData: null, //client_groups['pressure'],
      humidityData: null, //client_groups['humidity'],
      tapActiveName: 'temperature',
      activeName: 'second',
      client: null,
      sendSubcribe: null
    }
  },
  props: ['groupId'],
  components: { ClientConfigTable, ClientObserveTable },
  mounted() {
    this.connect()
    this.$axios
      .get('http://' + myIp + '/api/getSensorsDividedByType', {
        params: {
          id: this.groupId
        }
      })
      .then(res => {
        this.temperatureData = res.data['temperature']
        this.humidityData = res.data['humidity']
        this.pressureData = res.data['pressure']
        this.thunderData = res.data['thunder']
      })
  },
  methods: {
    handleTapClick(tap, event) {},
    handleClick(tab, event) {
      console.log(tab, event)
    },
    updateSendingMsg(id, msg, interval) {
      this.client.send(
        '/application/order',
        {},
        'msg/' + id + '/' + interval + '/' + msg
      )
      console.log('id: ' + id + ' msg: ' + msg + ' interval: ' + interval)
      //console.log('index : ' + index + ' msg: ' + msg)
    },
    updateClientState(id, active) {
      this.client.send('/application/order', {}, 'state/' + id + '/' + active)
      console.log('id: ' + id + ' active: ' + active)
    },
    msgSwitch(active) {
      console.log('active : ' + active)
    },
    onConnected() {
      /*this.sendSubcribe = this.client.subscribe('/topic/order', function (res) {

        })*/
    },
    onFailed() {},
    connect() {
      this.client = Stomp.client('ws://' + myIp + '/my-websocket')
      this.client.connect({}, this.onConnected, this.onFailed)
    }
  }
}
</script>


<style>
.el-row {
  margin-bottom: 20px;
}
</style>