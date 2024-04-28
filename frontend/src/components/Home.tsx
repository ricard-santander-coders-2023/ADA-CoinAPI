import React, { useState } from "react";
import { CoinChart } from "./CoinChart/CoinChart";
import { Converter } from "./Converter/Converter";
import { Favorites } from "./Favorites/Favorites";
import { Navbar } from "./Navbar";

export function Home() {
  const [selectedCoin, setSelectedCoin] = useState<string>("");
  const handleCoinSelection = (coin: string) => {
    setSelectedCoin(coin);
  };

  return (
    <div>
      <Navbar />
      <div className="pt-20 flex px-[10%] gap-4">
        <Favorites />
        <Converter onSelectCoin={handleCoinSelection} />
        <CoinChart selectedCoin={selectedCoin} />
      </div>
    </div>
  );
}
