import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import TimePicker from "rc-time-picker";
import "rc-time-picker/assets/index.css";
import {
  LineChart,
  Line,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import moment from "moment";

const apiUrl = "http://localhost:8080/api/machine_time_series_data";

const LineChartPage = () => {
  const [fromDate, setFromDate] = useState(new Date("2021-01-27T04:00:00"));
  const [fromTime, setFromTime] = useState(moment("2021-01-27 04:00:00"));
  const [data, setData] = useState(null);

  const fetchData = async () => {
    try {
      const fromDateStr = formatDate(fromDate);
      const fromTimeStr = formatTime(fromTime);

      const finalUrl = `${apiUrl}?fromDate=${fromDateStr}&fromTime=${fromTimeStr}`;

      const response = await fetch(finalUrl);
      const jsonData = await response.json();

      setData(jsonData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const formatDate = (date) => {
    return date.toISOString().split("T")[0];
  };

  const formatTime = (time) => {
    return time.format("HH:mm");
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-12">
        <h2 className="text-center mb-4">Power Consumption at a given Instant</h2>
          <div class="container-fluid">
            <div class="input-group mb-3">
              <span class="input-group-text" id="inputGroup-sizing-sm">From Date</span>
              <DatePicker selected={fromDate} onChange={setFromDate} />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" id="inputGroup-sizing-sm">From Time</span>
              <TimePicker
                value={fromTime}
                onChange={setFromTime}
                showSecond={false}
              />
            </div>
            <button onClick={fetchData} type="button" class="btn btn-primary">Fetch Data</button>
            {data && (
              <div style={{ width: "100%", height: 500 }}>
                <ResponsiveContainer width="100%" height="100%">
                  <LineChart
                    margin={{ top: 5, right: 30, left: 20, bottom: 100 }}
                    data={Object.entries(data).map(([timestamp, power]) => ({
                      timestamp,
                      power,
                    }))}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis
                      dataKey="timestamp"
                      angle={-60}
                      textAnchor="end"
                      interval={29}
                    />
                    <YAxis />
                    <Tooltip />
                    <Legend verticalAlign="top" height={50} />
                    <Line
                      type="monotone"
                      dataKey="power"
                      stroke="#8884d8"
                      name="Power Consumption"
                    />
                  </LineChart>
                </ResponsiveContainer>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default LineChartPage;
