// src/App.jsx
import React from "react";
import ContactList from "./components/ContactList";

function App() {
  return (
    <div>
      <h1 style={{ textAlign: 'center', margin: '20px 0' }}>Contact Management System</h1>
      <ContactList />
    </div>
  );
}

export default App;