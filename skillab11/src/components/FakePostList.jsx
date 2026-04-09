import React, { useEffect, useState } from "react";
import axios from "axios";

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [loading, setLoading] = useState(true);
  const [userId, setUserId] = useState("");

  const fetchPosts = async () => {
    try {
      setLoading(true);
      const res = await axios.get("https://dummyjson.com/posts");
      setPosts(res.data.posts);
      setFiltered(res.data.posts);
      setLoading(false);
    } catch {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const handleFilter = (e) => {
    const value = e.target.value;
    setUserId(value);

    if (value === "") {
      setFiltered(posts);
    } else {
      setFiltered(posts.filter(p => p.userId == value));
    }
  };

  return (
    <div className="container">
      <h2>Fake API Posts</h2>

      <button onClick={fetchPosts}>Refresh</button>

      <select value={userId} onChange={handleFilter}>
        <option value="">All Users</option>
        <option value="1">User 1</option>
        <option value="2">User 2</option>
        <option value="3">User 3</option>
      </select>

      {loading ? (
        <p>Loading...</p>
      ) : (
        filtered.map(post => (
          <div className="card" key={post.id}>
            <h4>{post.title}</h4>
            <p>{post.body}</p>
          </div>
        ))
      )}
    </div>
  );
}

export default FakePostList;