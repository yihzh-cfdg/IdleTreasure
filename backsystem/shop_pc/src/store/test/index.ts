import { defineStore } from "pinia";
//创建store
export const testStore = defineStore('testStore',{
    state:()=>{
        return{
            count:0
        }
    },
    //获取值
    getters:{
        getCount(state){
            return state.count
        }
    }, 
    //改变state的值
    actions:{
        setCount(count:number){
            this.count = count;
        }
    }
})