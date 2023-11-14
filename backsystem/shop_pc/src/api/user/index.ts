import http from "@/http";
import { User } from "./UserModel";
export const addAdminUserApi = (parm:User)=>{
    return http.post("/api/user",parm)
}