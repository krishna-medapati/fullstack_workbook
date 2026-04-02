import React from "react";
import { Link } from "react-router-dom";

function Dashboard() {
  return (
    <div className="container">
      <h1>Dashboard</h1>

      <div className="nav">
        <Link to="/local">Local Users</Link>
        <Link to="/api">Users API</Link>
        <Link to="/posts">Fake API Posts</Link>
      </div>
    </div>
  );
}

export default Dashboard;