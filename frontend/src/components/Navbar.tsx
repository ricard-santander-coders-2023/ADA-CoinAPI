import React, { useState, useEffect } from "react";

export function Navbar() {
  const cycleInfoData = [
    "Dolar hits $5.10",
    "Bitcoin drops 0.04%",
    "Stock hit new heights",
    "Gold prices surge",
    "Tech sector slumps on NASDAQ",
    "Oil prices record high",
    "Investors safe-haven assets",
    "Crypto market fluctuates wildly",
    "Reserve raises interest rates",
  ];

  const [currentInfoIndex, setCurrentInfoIndex] = useState(0);

  const cycleInfo = () => {
    setCurrentInfoIndex((prevIndex) => (prevIndex + 1) % cycleInfoData.length);
  };

  useEffect(() => {
    const interval = setInterval(cycleInfo, 3000);
    return () => clearInterval(interval);
  }, []);

  return (
    <nav className="flex justify-between items-center bg-gray-800 text-white py-4 px-[10%]">
      <div className="flex items-center">
        <img src="logo.png" alt="Logo" className="w-28 h-auto mr-4" />
      </div>
      <div className="flex items-center justify-center">
        <div className="h-12 w-56 rounded-full bg-gray-700 flex items-center justify-center mr-2">
          <span className="text-center text-nowrap">
            {cycleInfoData[currentInfoIndex]}
          </span>
        </div>
      </div>
      <div className="flex items-center">
        <img
          src="https://xsgames.co/randomusers/avatar.php?g=male"
          alt="User Logo"
          className="w-20 h-auto mr-2 rounded-full border border-gray-300"
        />
        <button className="bg-transparent hover:bg-red-600 px-4 py-2 rounded-md">
          sair
        </button>
      </div>
    </nav>
  );
}
