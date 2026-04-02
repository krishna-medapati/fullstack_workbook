import React, { useEffect, useState } from "react";

function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => res.json())
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(() => {
        setError("Error fetching users");
        setLoading(false);
      });
  }, []);

  return (
    <div className="container">
      <h2>API Users</h2>

      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}

      {users.map(user => (
        <div className="card" key={user.id}>
          <p><strong>Name:</strong> {user.name}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Phone:</strong> {user.phone}</p>
        </div>
      ))}
    </div>
  );
}

export default UserList;