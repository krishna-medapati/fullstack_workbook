import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Profile() {
  const [user, setUser] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const id = localStorage.getItem("userId");

    console.log("Stored userId:", id); // 👈 DEBUG

    if (!id) {
      navigate("/");
      return;
    }

    axios
      .get(`http://localhost:8080/api/users/${id}`)
      .then((res) => {
        console.log("User data:", res.data); // 👈 DEBUG
        setUser(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div>
      <h2>Profile</h2>

      <p>Username: {user.username}</p>
      <p>Email: {user.email}</p>
    </div>
  );
}

export default Profile;