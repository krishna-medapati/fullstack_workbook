import { useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  useEffect(() => {
    if (!localStorage.getItem("userId")) {
      navigate("/");
    }
  }, []);

  const logout = () => {
    localStorage.removeItem("userId");
    navigate("/");
  };

  return (
    <div>
      <h2>Home</h2>
      <Link to="/profile">Profile</Link> | 
      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default Home;