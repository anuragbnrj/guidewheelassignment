import React from "react";
import { render, screen } from "@testing-library/react";
import PieChartPage from "./PieChartPage";

describe("PieChartPage", () => {
  test("renders the page", () => {
    render(<PieChartPage />);
    const pageTitle = screen.getByText("Power Load Distribution within a time range");
    expect(pageTitle).toBeInTheDocument();
  });
});