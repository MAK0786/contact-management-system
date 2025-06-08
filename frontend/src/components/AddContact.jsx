// src/components/AddContact.jsx
import React, { useState } from "react";
import axios from "axios";
import { backendUrl } from "../config";  // import backendUrl

const AddContact = ({ onContactAdded }) => {
  const [contact, setContact] = useState({
    name: "",
    email: "",
    phone: "",
    address: "",
  });

  const handleChange = (e) => {
    setContact({ ...contact, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(`${backendUrl}/api/add`, contact)  // use backendUrl here
      .then(() => {
        alert("Contact added!");
        setContact({ name: "", email: "", phone: "", address: "" });
        onContactAdded?.();
      })
      .catch((error) => {
        console.error("Error adding contact:", error);
        alert("Failed to add contact");
      });
  };

  return (
    <div className="left-panel">
      <form onSubmit={handleSubmit}>
        <h3>Add New Contact</h3>
        <input type="text" name="name" placeholder="Name" value={contact.name} onChange={handleChange} required />
        <input type="email" name="email" placeholder="Email" value={contact.email} onChange={handleChange} required />
        <input
          type="text"
          name="phone"
          placeholder="Phone"
          value={contact.phone}
          onChange={handleChange}
          pattern="\d{10}"
          title="Phone must be 10 digits"
          required
        />
        <input type="text" name="address" placeholder="Address" value={contact.address} onChange={handleChange} />
        <button type="submit">Add Contact</button>
      </form>
    </div>
  );
};

export default AddContact;
