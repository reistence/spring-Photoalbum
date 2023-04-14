import React from "react";

import { Photo } from "../pages/Photos";
import { Category } from "../pages/Photos";
import { Link } from "react-router-dom";

type photoProps = {
  id: number;
  title: string;
  url: string;
  visible: boolean;
  description: string;
  categories: Category[];
};

export default function PhotoCard({
  url,
  id,
  title,
  description,
  categories,
  visible,
}: photoProps) {
  return (
    visible && (
      <Link to={`/photo/${id}`} className="photo-card">
        <div className="cardimg">
          <img src={url} alt="" />
        </div>
        <div className="cardtxt">
          <h5>{title}</h5>
          <p>{description}</p>
          <div className="categories">
            <>
              {categories &&
                categories.map((cat, key) => <span key={key}>{cat.name}</span>)}
            </>
          </div>
        </div>
      </Link>
    )
  );
}
