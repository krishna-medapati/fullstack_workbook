import React, { useState, useEffect } from "react";
import axios from "axios";

function AddStudent({ onSuccess, editStudent }) {

  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: ""
  });

  // Prefill form when editing
  useEffect(() => {
    if (editStudent) {
      setStudent(editStudent);
    }
  }, [editStudent]);

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (student.id) {
      // UPDATE
      axios.put(`http://localhost:8080/students/${student.id}`, student)
        .then(() => {
          onSuccess();
          setStudent({ name: "", email: "", course: "" });
        });
    } else {
      // ADD
      axios.post("http://localhost:8080/students", student)
        .then(() => {
          onSuccess();
          setStudent({ name: "", email: "", course: "" });
        });
    }
  };

  return (
    <div>
      <h3>{student.id ? "Update Student" : "Add Student"}</h3>

      <form onSubmit={handleSubmit}>
        <input name="name" value={student.name} onChange={handleChange} placeholder="Name" required />
        <input name="email" value={student.email} onChange={handleChange} placeholder="Email" required />
        <input name="course" value={student.course} onChange={handleChange} placeholder="Course" required />

        <button type="submit">
          {student.id ? "Update" : "Add"}
        </button>
      </form>
    </div>
  );
}

export default AddStudent;