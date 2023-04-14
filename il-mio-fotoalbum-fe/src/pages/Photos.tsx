import React, { useEffect, useState } from "react";
import axios, { Axios } from "axios";
import PhotoCard from "../components/PhotoCard";

export type Photo = {
  id: number;
  title: string;
  url: string;
  visible: boolean;
  description: string;
  categories: Category[];
};

export type Category = {
  id: number;
  name: string;
};

export default function Photos() {
  const BE_URL = "http://localhost:8080/api/v1/photos";

  const [keyword, setKeyword] = useState("");
  const [photos, setPhotos] = useState<Photo[]>([]);

  function getAllPhotos(q: string) {
    axios.get(BE_URL + "?q=" + q).then((res) => {
      console.log(res);
      return res.data;
    });
  }

  //   useEffect(() => {
  //     axios.get(BE_URL + "?q=" + keyword).then((res) => {
  //       setPhotos(res.data);
  //       console.log(photos);
  //       return photos;
  //     });
  //   }, []);

  useEffect(() => {
    // pics = getAllPhotos(keyword);
    // setPhotos(pics!);
    axios.get(BE_URL + "?q=" + keyword).then((res) => {
      if (res.data.length > 0) {
        setPhotos(res.data);
        console.log(photos);
      }
    });
  }, [keyword]);

  return (
    <div className="my-body container">
      <h1 className="my-5">All Photos</h1>
      <div id="search">
        <input
          type="text"
          id="keyword"
          name="keyword"
          placeholder="Insert a title"
          defaultValue={keyword}
          onChange={(e) => setKeyword(e.target.value)}
        ></input>
        {/* <button className="my-btn" type="submit">
          Search
        </button> */}
      </div>
      <div className="cardscontainer">
        {photos &&
          photos.map((pic, key) => (
            <PhotoCard
              key={key}
              id={pic.id}
              title={pic.title}
              description={pic.description}
              url={pic.url}
              visible={pic.visible}
              categories={pic.categories}
            ></PhotoCard>
          ))}
      </div>
    </div>
  );
}
