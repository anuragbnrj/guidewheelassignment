import React from "react";
import { render, screen } from "@testing-library/react";
import LineChartPage from "./LineChartPage";

describe("LineChartPage", () => {
  test("renders the page", () => {
    render(<LineChartPage />);
    const pageTitle = screen.getByText("Power Consumption at a given Instant");
    expect(pageTitle).toBeInTheDocument();
  });
});