import React, { useState } from "react";
import axios, { Axios } from "axios";
import { useNavigate } from "react-router-dom";

export default function ContactUs() {
  const BE_URL = "http://localhost:8080/api/v1/messages";
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  function sendForm(e: any) {
    e.preventDefault();
    console.log("sending");

    const data = {
      email: email,
      message: message,
    };

    axios.post(BE_URL, data).then((res) => {
      console.log("sent");
      navigate("/photos");
    });
  }

  return (
    <div className="container my-body">
      <h1>Contact Us</h1>
      <form
        onSubmit={sendForm}
        method="POST"
        className="d-flex justify-content-center flex-column"
        style={{ gap: "1.5em", width: "80%", margin: "0 auto" }}
      >
        <input
          type="email"
          name="email"
          id="email"
          defaultValue={email}
          placeholder="Email address"
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <textarea
          required
          name="message"
          cols={30}
          rows={5}
          defaultValue={message}
          placeholder="Write your message"
          onChange={(e) => setMessage(e.target.value)}
        ></textarea>

        <button className="my-btn" type="submit">
          Sent
        </button>
      </form>
    </div>
  );
}
