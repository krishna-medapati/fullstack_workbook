import React, { useState } from "react";
import StudentList from "./components/StudentList";
import AddStudent from "./components/AddStudent";

function App() {
  const [refresh, setRefresh] = useState(false);
  const [editStudent, setEditStudent] = useState(null);

  const refreshList = () => {
    setRefresh(!refresh);
    setEditStudent(null);
  };

  return (
    <div>
      <h2>Student Management System</h2>

      <AddStudent onSuccess={refreshList} editStudent={editStudent} />

      <StudentList
        refresh={refresh}
        onEdit={(student) => setEditStudent(student)}
      />
    </div>
  );
}

export default App;