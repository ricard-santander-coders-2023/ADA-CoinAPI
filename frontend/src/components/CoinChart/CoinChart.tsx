import React, { useState, useEffect } from "react";
import { PriceChart } from "./PriceChart";
import { ChartPeriodBubble } from "./ChartPeriodBubble";
import { api } from "../../api/axios";

interface CoinChartProps {
  selectedCoin: string;
  coinValues: { [key: string]: number };
}

export function CoinChart({ selectedCoin, coinValues }: CoinChartProps) {
  const [chartPeriod, setChartPeriod] = useState(30);
  const [coinData, setCoinData] = useState([]);

  const fetchHistoricalCoinValues = async (coin: string) => {
    try {
      const response = await api.get(`${coin}/${chartPeriod}`);
      console.log("API Response:", response.data); // Log API response for debugging
      return response.data;
    } catch (error) {
      console.error("Error fetching historical coin values:", error);
      return null;
    }
  };

  useEffect(() => {
    const fetchCoinData = async () => {
      const data = await fetchHistoricalCoinValues(selectedCoin);
      if (data) {
        console.log("Fetched Coin Data:", data); // Log fetched coin data
        setCoinData(data);
      }
    };
    fetchCoinData();
  }, [chartPeriod, selectedCoin]);

  const handlePeriodButtonClick = (period: number) => {
    setChartPeriod(period);
  };

  const periods = [30, 60, 90];
  return (
    <div className="w-1/4 pl-4 justify-center p-5 border rounded-lg border-slate-500">
      <PriceChart data={coinData} chartPeriod={chartPeriod} />
      <ChartPeriodBubble
        periods={periods}
        chartPeriod={chartPeriod}
        handlePeriodButtonClick={handlePeriodButtonClick}
      />
      <div className="border border-gray-300 rounded-md p-4">
        Table (Placeholder)
      </div>
    </div>
  );
}
