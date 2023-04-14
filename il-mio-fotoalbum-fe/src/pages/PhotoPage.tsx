import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { Photo } from "./Photos";
import { Category } from "./Photos";
import axios from "axios";

export default function PhotoPage() {
  const { id } = useParams();
  const [singlePhoto, setSinglePhoto] = useState<Photo>();
  const BE_URL = "http://localhost:8080/api/v1/photos/";

  useEffect(() => {
    axios.get(BE_URL + id).then((res) => {
      setSinglePhoto(res.data);
      console.log(singlePhoto);
    });
  }, []);

  return (
    <>
      <div className="container my-body">
        <h1>{singlePhoto?.title}</h1>
        <div className="wrapper d-flex justify-content-center flex-column">
          <img className="cover-show" src={singlePhoto?.url} alt="" />
          <div className="details my-5">
            <p className="text-start my-1">
              Description: {singlePhoto?.description}
            </p>
            <p className="categories my-1">
              Categories:{" "}
              {singlePhoto?.categories &&
                singlePhoto.categories.map((cat, key) => (
                  <span>{cat.name} </span>
                ))}
            </p>
          </div>
        </div>
      </div>
    </>
  );
}
