import React, { useState, useEffect } from "react";
import { api } from "../../api/axios";

interface ConverterProps {
  onSelectCoin: (coin: string) => void;
}

interface CoinValuesProps {
  [key: string]: number;
}

export function Converter({ onSelectCoin }: ConverterProps) {
  const [coinOptions, setCoinOptions] = useState<CoinValuesProps>({});
  const [selectedCoins, setSelectedCoins] = useState<string[]>(["", ""]);
  const [quantity, setQuantity] = useState<number | "">("");
  const [selectedDate, setSelectedDate] = useState<string>("");
  const [convertedValue, setConvertedValue] = useState<number | "">("");

  const fetchConvertedValue = async () => {
    if (selectedCoins[0] && selectedCoins[1] && quantity !== "") {
      try {
        const response = await api.get(
          `/quotes/convert/${selectedCoins[0]}/${selectedCoins[1]}/${quantity}`
        );
        const data = response.data;
        setConvertedValue(data);
      } catch (error) {
        console.error("Error fetching converted value:", error);
      }
    }
  };

  const handleCoinChange =
    (index: number) => async (event: React.ChangeEvent<HTMLSelectElement>) => {
      const updatedCoins = [...selectedCoins];
      updatedCoins[index] = event.target.value;
      setSelectedCoins(updatedCoins);
      onSelectCoin(event.target.value);
      if (selectedCoins[0] && selectedCoins[1] && quantity !== "") {
        await fetchConvertedValue();
      }
    };

  useEffect(() => {
    const fetchCoinTypes = async () => {
      try {
        const response = await api.get(
          "https://economia.awesomeapi.com.br/json/available/uniq"
        );
        const data = response.data;
        setCoinOptions(data);
      } catch (error) {
        console.error("Error fetching coin options:", error);
      }
    };

    fetchCoinTypes();
  }, []);

  const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    setQuantity(inputValue !== "" ? parseFloat(inputValue) : "");
  };

  const handleDateChange = async (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const newSelectedDate = event.target.value;
    setSelectedDate(newSelectedDate);
  };

  useEffect(() => {
    const fetchConvertedValue = async () => {
      if (
        selectedCoins[0] !== "" &&
        selectedCoins[1] !== "" &&
        quantity !== ""
      ) {
        console.log("SELECTED COINS ===> ", selectedCoins)
        try {
          const response = await api.get(
            `/convert/${selectedCoins[0]}/${selectedCoins[1]}/${quantity}`
          );
          const data = response.data;
          setConvertedValue(data); // Assuming the API returns the converted value directly
        } catch (error) {
          console.error("Error fetching converted value:", error);
        }
      }
    };

    fetchConvertedValue();
  }, [selectedCoins[0], selectedCoins[1], quantity]);

  return (
    <div className="w-1/2 px-4 pt-5 border rounded-lg border-slate-500">
      <div className="mb-4 flex text-slate-800 justify-around">
        <div className="flex flex-col gap-6">
          <select
            value={selectedCoins[0]}
            onChange={handleCoinChange(0)}
            className="border border-gray-300 rounded-md px-3 py-2 mr-2 text-slate-800"
          >
            <option value="">Select coin</option>
            {Object.entries(coinOptions).map(([symbol, name]) => (
              <option key={symbol} value={symbol}>
                {symbol} - {name}
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
            <option value="">Select coin</option>
            {Object.entries(coinOptions).map(([symbol, name]) => (
              <option key={symbol} value={symbol}>
                {symbol} - {name}
              </option>
            ))}
          </select>
          <input
            type="number"
            readOnly
            value={convertedValue}
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
