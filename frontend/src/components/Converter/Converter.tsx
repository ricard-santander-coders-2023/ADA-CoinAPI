import React, { useState, useEffect } from "react";
import { api } from "../../api/axios";

interface ConverterProps {
  onSelectCoin: (coin: string) => void;
}

interface CoinValuesProps {
  [key: string]: number;
}

export function Converter({ onSelectCoin }: ConverterProps) {
  const [selectedCoins, setSelectedCoins] = useState<string[]>(["USD", ""]);
  const [quantity, setQuantity] = useState<number | "">("");
  const [selectedDate, setSelectedDate] = useState<string>("");
  const [coinValues, setCoinValues] = useState<{ [key: string]: number }>({});

  const handleCoinChange =
    (index: number) => (event: React.ChangeEvent<HTMLSelectElement>) => {
      const updatedCoins = [...selectedCoins];
      updatedCoins[index] = event.target.value;
      setSelectedCoins(updatedCoins);
      onSelectCoin(event.target.value);
    };

  useEffect(() => {
    const fetchCoinValues = async () => {
      try {
        const response = await api.get("all");
        const data = response.data;
        const coinValues:CoinValuesProps = {};
        const usdHigh = parseFloat(data.USD.high);
        for (const coin in data) {
          if (data[coin].high) {
            const coinHigh = parseFloat(data[coin].high);
            coinValues[coin] = coinHigh / usdHigh;
          }
          coinValues.BRL = parseFloat(1 / data.USD.high);
        }
        setCoinValues(coinValues);
      } catch (error) {
        console.error("Error fetching coin values:", error);
      }
    };

    fetchCoinValues();
  }, []);

  const fetchCoinInSpecificDate = async () => {
    try {
      const formattedDate = selectedDate.split("-").join("");
      const response = await api.get(
        `daily/${selectedCoins[0]}?start_date=${formattedDate}&end_date=${formattedDate}`
      );
      const data = response.data;
      const coinValues = {};
      if (data && data.length > 0) {
        const coinData = data[0]; // Assuming only one data point for the selected date
        if (coinData.high) {
          coinValues[selectedCoins[0]] = parseFloat(coinData.high);
        }
      }
      setCoinValues(coinValues);
    } catch (error) {
      console.error("Error fetching coin values for selected date:", error);
    }
  };

  const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    setQuantity(inputValue !== "" ? parseFloat(inputValue) : "");
  };

  const handleDateChange = async (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const newSelectedDate = event.target.value;
    setSelectedDate(newSelectedDate);
    await fetchCoinInSpecificDate();
  };

  return (
    <div className="w-1/2 px-4 pt-5 border rounded-lg border-slate-500">
      <div className="mb-4 flex text-slate-800 justify-around">
        <div className="flex flex-col gap-6">
          <select
            value={selectedCoins[0]}
            onChange={handleCoinChange(0)}
            className="border border-gray-300 rounded-md px-3 py-2 mr-2 text-slate-800"
          >
            {Object.keys(coinValues).map((coin) => (
              <option key={coin} value={coin}>
                {coin}
              </option>
            ))}
          </select>
          <input
            type="number"
            value={quantity}
            onChange={handleQuantityChange}
            className="border border-gray-300 rounded-md px-3 py-2 mr-2"
            placeholder="Enter quantity"
          />
        </div>
        <div className="flex flex-col gap-6">
          <select
            value={selectedCoins[1]}
            onChange={handleCoinChange(1)}
            className="border border-gray-300 rounded-md px-3 py-2 mr-2 text-slate-800"
          >
            {Object.keys(coinValues).map((coin) => (
              <option key={coin} value={coin}>
                {coin}
              </option>
            ))}
          </select>
          <input
            type="number"
            readOnly
            value={
              quantity !== ""
                ? (
                    (quantity * (coinValues[selectedCoins[0]] || 1)) /
                    (coinValues[selectedCoins[1]] || 1)
                  ).toFixed(6)
                : ""
            }
            className="border border-gray-300 rounded-md px-3 py-2 mr-2"
          />
        </div>
      </div>
      <div className="flex w-full mb-4 justify-center py-6">
        <input
          type="date"
          value={selectedDate}
          onChange={handleDateChange}
          className="border w-[30%] text-slate-800 border-gray-300 rounded-md px-3 py-2 mr-2"
        />
      </div>
      <div className="flex justify-center">
        {/* <button type="button" onClick={handleFormSubmit} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
          Convert
        </button> */}
      </div>
    </div>
  );
}
