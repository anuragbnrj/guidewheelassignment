import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import TimePicker from "rc-time-picker";
import "rc-time-picker/assets/index.css";
import { PieChart, Pie, Cell, ResponsiveContainer } from "recharts";
import moment from "moment";

const apiUrl = "http://localhost:8080/api/machine_data";
const colors = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const PieChartPage = () => {
  const [fromDate, setFromDate] = useState(new Date("2021-01-27T04:00:00"));
  const [fromTime, setFromTime] = useState(moment("2021-01-27 04:00:00"));

  const [toDate, setToDate] = useState(new Date("2021-01-28T04:00:00"));
  const [toTime, setToTime] = useState(moment("2021-01-28 04:00:00"));

  const [data, setData] = useState(null);

  const fetchData = async () => {
    try {
      const fromDateStr = formatDate(fromDate);
      const fromTimeStr = formatTime(fromTime);
      const toDateStr = formatDate(toDate);
      const toTimeStr = formatTime(toTime);

      const finalUrl = `${apiUrl}?fromDate=${fromDateStr}&fromTime=${fromTimeStr}&toDate=${toDateStr}&toTime=${toTimeStr}`;
      console.log(finalUrl);

      const response = await fetch(finalUrl);
      const jsonData = await response.json();
      console.log(jsonData);
      const formattedData = Object.entries(jsonData).map((entry) => ({
        name: entry[0],
        value: entry[1],
      }));
      setData(formattedData);
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
          <h2 className="text-center mb-4">
            Power Load Distribution within a time range
          </h2>

          <div class="container-fluid">
            <div class="input-group mb-3">
              <span class="input-group-text" id="inputGroup-sizing-sm">
                From Date
              </span>
              <DatePicker selected={fromDate} onChange={setFromDate} />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" id="inputGroup-sizing-sm">
                From Time
              </span>
              <TimePicker
                value={fromTime}
                onChange={setFromTime}
                showSecond={false}
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" id="inputGroup-sizing-sm">
                To Date
              </span>
              <DatePicker selected={toDate} onChange={setToDate} />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" id="inputGroup-sizing-sm">
                To Time
              </span>
              <TimePicker
                value={toTime}
                onChange={setToTime}
                showSecond={false}
              />
            </div>
            <button onClick={fetchData} type="button" class="btn btn-primary">
              Fetch Data
            </button>

            {data && (
              <div>
                <ResponsiveContainer width="100%" height={500}>
                  <PieChart>
                    <text
                      x="50%"
                      y="10%"
                      textAnchor="middle"
                      dominantBaseline="middle"
                      fontSize={20}
                    >
                      Distribution of power consumed by Machine
                    </text>
                    <Pie
                      data={data}
                      dataKey="value"
                      nameKey="name"
                      cx="50%"
                      cy="50%"
                      outerRadius={150}
                      fill="#8884d8"
                      label={({
                        cx,
                        cy,
                        midAngle,
                        innerRadius,
                        outerRadius,
                        value,
                        index,
                      }) => {
                        console.log("handling label?");
                        const RADIAN = Math.PI / 180;
                        const radius =
                          25 + innerRadius + (outerRadius - innerRadius);
                        const x = cx + radius * Math.cos(-midAngle * RADIAN);
                        const y = cy + radius * Math.sin(-midAngle * RADIAN);

                        return (
                          <text
                            x={x}
                            y={y}
                            fill={colors[index % colors.length]}
                            textAnchor={x > cx ? "start" : "end"}
                            dominantBaseline="central"
                          >
                            {data[index].name} ({value})
                          </text>
                        );
                      }}
                    >
                      {Object.keys(data).map((entry, index) => (
                        <Cell
                          key={`cell-${index}`}
                          fill={colors[index % colors.length]}
                        />
                      ))}
                    </Pie>
                  </PieChart>
                </ResponsiveContainer>
              </div>
            )}

          </div>
        </div>
      </div>
    </div>
  );
};

export default PieChartPage;
