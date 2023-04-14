import React from "react";
import jumbo from "../assets/jumbo.jpg";
import logo from "../assets/logo.png";

export default function Home() {
  return (
    <>
      <div className="my-fluid rel">
        <img className="jumbo" src={jumbo}></img>

        <div className="banner">
          <img className="big-logo" src={logo}></img>
          <h1>Your professional Photographer </h1>
        </div>
      </div>
    </>
  );
}
