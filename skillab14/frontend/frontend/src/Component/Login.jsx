import { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

function Login() {
  const [user, setUser] = useState({
    username: "",
    password: ""
  });

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      console.log("Sending data:", user); // DEBUG

      const res = await axios.post(
        "http://localhost:8080/api/users/login",
        user
      );

      console.log("Login response:", res.data); // VERY IMPORTANT

      // ✅ FIX: check if response contains user and id
      if (res.data && res.data.id) {
        localStorage.setItem("userId", res.data.id);
        console.log("Stored userId:", res.data.id);

        navigate("/home");
      } else {
        console.log("No userId received ❌");
        alert("Login failed ❌");
      }

    } catch (err) {
      console.error("Login error:", err.response?.data);
      alert("Invalid username or password ❌");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>

      <input
        name="username"
        placeholder="Username"
        value={user.username}
        onChange={(e) =>
          setUser({ ...user, username: e.target.value })
        }
      />

      <input
        type="password"
        name="password"
        placeholder="Password"
        value={user.password}
        onChange={(e) =>
          setUser({ ...user, password: e.target.value })
        }
      />

      <button type="submit">Login</button>

      <p>
        New user? <Link to="/register">Register</Link>
      </p>
    </form>
  );
}

export default Login;