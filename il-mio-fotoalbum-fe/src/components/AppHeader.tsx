import React, { useState } from "react";
import logo from "../assets/logo.png";
import { Link } from "react-router-dom";

export default function AppHeader() {
  const headerLinks = [
    {
      label: "Home",
      link: "/",
      routeName: "/",
    },
    {
      label: "Photos",
      link: "/photos",
      routeName: "/photos",
    },
    {
      label: "Contact Us",
      link: "/contact",
      routeName: "/contact",
    },
  ];

  //   const [keyword, setKeyword] = useState("");

  return (
    <>
      <nav className="navbar fixed-top navbar-dark navbar-expand-lg bg-body-tertiary ">
        <div className="container-fluid">
          <Link className="navbar-brand" to={"/"}>
            <img className="my-logo" src={logo} alt="logo"></img>
          </Link>
          <button
            className="navbar-toggler "
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div
            className="collapse navbar-collapse justify-content-between"
            id="navbarSupportedContent"
          >
            <ul className="navbar-nav " style={{ gap: "1em" }}>
              {headerLinks &&
                headerLinks.map((hlink, key) => (
                  <li key={key} className="nav-item">
                    <Link to={hlink.routeName}> {hlink.label}</Link>
                  </li>
                ))}
            </ul>
            {/* <form
              // th:if="${showSearch}"
              className="d-flex"
              role="search"
              method="get"
              // th:action="@{/photos/index}"
            >
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
                name="q"
                defaultValue={keyword}
                onChange={(e) => setKeyword(e.target.value)}
                //    th:value="${keyword}"
              ></input>
              <button className="btn btn-outline-light" type="submit">
                <i className="fa-solid fa-magnifying-glass"></i>
              </button>
            </form> */}
          </div>
        </div>
      </nav>
    </>
  );
}
