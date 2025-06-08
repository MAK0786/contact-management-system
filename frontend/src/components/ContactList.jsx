import React, { useEffect, useState } from "react";
import axios from "axios";
import AddContact from "./AddContact";
import { backendUrl } from "../config";  // Import backend URL
import "./ContactList.css";

// Use backendUrl + /api as base URL
const axiosInstance = axios.create({ baseURL: `${backendUrl}/api` });

const ContactList = () => {
  const [contacts, setContacts] = useState([]);
  const [editingContact, setEditingContact] = useState(null);

  useEffect(() => { fetchContacts(); }, []);

  const fetchContacts = async () => {
    try {
      const res = await axiosInstance.get("/contacts");
      setContacts(res.data);
    } catch (err) {
      console.error("Error fetching contacts:", err);
    }
  };

  const deleteContact = async (id) => {
    try {
      await axiosInstance.delete(`/remove/${id}`);
      alert("Contact deleted!");
      fetchContacts();
    } catch (err) {
      console.error("Error deleting contact:", err);
      alert("Failed to delete contact");
    }
  };

  const handleEditChange = (e) => {
    const { name, value } = e.target;
    setEditingContact((prev) => ({ ...prev, [name]: value }));
  };

  const handleUpdate = async () => {
    try {
      if (!editingContact?.id) return alert("Invalid contact to update");
      await axiosInstance.put(`/update/${editingContact.id}`, editingContact);
      alert("Contact updated!");
      setEditingContact(null);
      fetchContacts();
    } catch (err) {
      console.error("Error updating contact:", err);
      alert("Failed to update contact");
    }
  };

  const renderInput = (label, name, value, onChangeHandler) => (
    <div style={{ marginBottom: "10px" }}>
      <label>{label}</label>
      <input
        type="text"
        name={name}
        value={value}
        onChange={onChangeHandler}
        style={{ marginLeft: "10px", padding: "5px", width: "100%" }}
      />
    </div>
  );

  return (
    <div className="container">
      <AddContact onContactAdded={fetchContacts} />

      <div className="right-panel">
        <h3>Contact List</h3>
        <table className="contact-table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Address</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {contacts.map((c) => (
              <tr key={c.id}>
                <td>{c.name}</td>
                <td>{c.email}</td>
                <td>{c.phone}</td>
                <td>{c.address}</td>
                <td>
                  <button onClick={() => setEditingContact(c)}>Edit</button>
                  <button onClick={() => deleteContact(c.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {editingContact && (
          <div className="edit-form">
            <h4>Edit Contact</h4>
            {renderInput("Name", "name", editingContact.name, handleEditChange)}
            {renderInput("Email", "email", editingContact.email, handleEditChange)}
            {renderInput("Phone", "phone", editingContact.phone, handleEditChange)}
            {renderInput("Address", "address", editingContact.address, handleEditChange)}
            <button onClick={handleUpdate} style={{ marginRight: "10px" }}>
              Update
            </button>
            <button onClick={() => setEditingContact(null)}>Cancel</button>
          </div>
        )}
      </div>
    </div>
  );
};

export default ContactList;
