interface ChartPeriodBubbleProps {
  periods: number[];
  chartPeriod: number;
  handlePeriodButtonClick: (period: number) => void;
}

export function ChartPeriodBubble({
  periods,
  chartPeriod,
  handlePeriodButtonClick,
}: ChartPeriodBubbleProps) {
  return (
    <div className="border border-gray-300 rounded-md p-4 mb-4 mt-4">
      <div className="flex justify-around">
        {periods.map((period) => (
          <button
            key={period}
            className={`border border-gray-300 rounded-full px-4 py-2 hover:bg-gray-200 hover:text-slate-800 ${
              chartPeriod === period ? "bg-gray-200 text-slate-800" : ""
            }`}
            onClick={() => handlePeriodButtonClick(period)}
          >
            {period}
          </button>
        ))}
      </div>
    </div>
  );
}
