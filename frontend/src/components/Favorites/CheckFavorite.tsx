import { Star } from "phosphor-react";

export function CheckFavorite() {

  return (
    <Star
      size={24}
      color="#eedd22"
      weight="fill"
      className="transition-colors duration-300 hover:fill-gray-500 cursor-pointer"
    />
  );
}
