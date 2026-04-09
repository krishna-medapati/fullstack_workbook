import React,{useState} from 'react'
import axios from 'axios'
import API_URL from './config'

function UserForm(){

const [name,setName]=useState("")
const [email,setEmail]=useState("")

const submit=(e)=>{
e.preventDefault()

axios.post(API_URL+"/api/users",{
name,
email
}).then(()=>{
alert("User Added")
})
}

return(
<div>

<h2>Add User</h2>

<form onSubmit={submit}>

<input
placeholder="Name"
value={name}
onChange={(e)=>setName(e.target.value)}
/>

<br/><br/>

<input
placeholder="Email"
value={email}
onChange={(e)=>setEmail(e.target.value)}
/>

<br/><br/>

<button>Add</button>

</form>

</div>
)

}

export default UserForm