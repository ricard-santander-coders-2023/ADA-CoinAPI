import React from "react";
import { CheckFavorite } from "./CheckFavorite"; // Assuming you have a Star component for checking/unchecking favorites

const favoriteCoins = ["Bitcoin", "Ethereum", "Litecoin"];

export function Favorites() {
  return (
    <div className="w-1/4 pr-4  p-5 border rounded-lg border-slate-500">
      <h2 className="text-xl font-bold mb-4">Favoritos</h2>
      <div className="w-full">
        <div className="w-4/5">
          {favoriteCoins.map((coin, index) => (
            <div
              key={index}
              className="flex items-center justify-between hover:bg-slate-700 rounded-lg cursor-pointer py-2 px-4 mb-2"
            >
              <div>{coin}</div>
              <div>
                <CheckFavorite />
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
