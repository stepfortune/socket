<template>
    <header  style="background-color: rgb(10, 47, 88);">
        <div class="text-logo">{{msg}}</div>
        <ul class="header-operations">
        </ul>
    </header>
</template>

<script>
    export default {
        name: 'page-header',
        data () {
            return {
                msg: 'Lab409 物联网接入平台'
            }
        }
    }
</script>

<style>
    header {
        height: 70px;
        position: absolute;
        width: 100%;
        top: 0;
        left: 0;
        padding: 0 20px;
        z-index: 1;
        box-sizing: border-box;
    }

    .text-logo {
        display: inline-block;
        vertical-align: middle;
        border-style: none;
        position: relative;
        top: 16px;
        right: 450px;
        font-size: 25px;
        color: white;
    }

</style>