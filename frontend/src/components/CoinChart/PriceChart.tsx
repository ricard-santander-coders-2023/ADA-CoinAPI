import React, { useState, useEffect } from "react";
import { LineChart, Line, XAxis, YAxis, Tooltip } from "recharts";

interface ExchangeRateData {
  timestamp: number;
  high: string;
}

interface PriceChartProps {
  data: ExchangeRateData[];
  chartPeriod: number;
  width?: number;
  height?: number;
}

interface PriceDataProps {
  date: string;
  price: number;
}

export function PriceChart({
  data,
  chartPeriod,
  width = 345,
  height = 200,
}: PriceChartProps) {
  const [chartData, setChartData] = useState<PriceDataProps[]>([]);

  useEffect(() => {
    const filteredData = data
      .filter((entry) => {
        const currentDate = new Date(entry.timestamp * 1000); // Convert to milliseconds
        const cutoffDate = new Date();
        cutoffDate.setDate(cutoffDate.getDate() - chartPeriod);
        return currentDate >= cutoffDate;
      })
      .map((entry) => {
        return {
          date: new Date(entry.timestamp * 1000).toLocaleDateString(), // Convert to date string
          price: parseFloat(entry.high),
        };
      });

    setChartData(filteredData);
  }, [data, chartPeriod]);

  return (
    <LineChart
      width={width}
      height={height}
      data={chartData}
      className="bg-slate-100 py-2 rounded-lg"
    >
      <XAxis dataKey="date" interval="preserveEnd" />
      <YAxis interval={1} />
      <Tooltip />
      <Line
        type="monotone"
        dataKey="price"
        stroke="#8884d8"
        fillOpacity={0.5}
      />
    </LineChart>
  );
}
