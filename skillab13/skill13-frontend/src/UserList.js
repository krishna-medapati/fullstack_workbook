import React,{useEffect,useState} from 'react'
import axios from 'axios'
import API_URL from './config'

function UserList(){

const [users,setUsers]=useState([])

useEffect(()=>{

axios.get(API_URL+"/api/users")
.then(res=>{
setUsers(res.data)
})

},[])

return(

<div>

<h2>User List</h2>

<table border="1">

<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
</tr>

{
users.map(user=>(
<tr key={user.id}>
<td>{user.id}</td>
<td>{user.name}</td>
<td>{user.email}</td>
</tr>
))
}

</table>

</div>

)

}

export default UserList