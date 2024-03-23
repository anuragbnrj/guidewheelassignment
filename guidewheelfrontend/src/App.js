import React from "react";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import PieChartPage from "./PieChartPage";
import LineChartPage from "./LineChartPage";
import HomePage from "./HomePage"
import "bootstrap/dist/css/bootstrap.min.css";



const App = () => {
  return (
    <Router>
      <div>
        <nav className="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
          <div className="container-fluid">
            <Link className="navbar-brand" to="/">
              Home
            </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link" to="/pie-chart">
                    Power Distribution
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/line-chart">
                    Power Time Series
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <Routes>
          <Route path="/pie-chart" element={<PieChartPage />} />
          <Route path="/line-chart" element={<LineChartPage />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
