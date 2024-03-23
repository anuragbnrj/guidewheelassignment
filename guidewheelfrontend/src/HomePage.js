const HomePage = () => {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-12">
          <h1 className="text-center mb-4">Guidewheel Assignment UI</h1>
          <p class="lead">
            There are two types of charts that you can visualize via this
            application. You can navigate to them via the links on the navbar.
          </p>
          <p>
            <strong>1. Power Distribution:</strong> In this the power
            distribution in a given time interval is shown via a PIE chart. The
            user can enter the start date and time and the end date and time.
            The UI shows how many data points were present in the off, unloaded,
            idle, and loaded state. The readings present are polled at an
            interval of 60 seconds and the machine is considered to be off for
            an instant when no data is present for that instant.
          </p>
          <p>
            <strong>2. Power Time Series:</strong> In this the power consumption
            at a given instant is shown via a LINE chat. The user can enter the
            start date and time. For the sake of cleanliness of UI, the power
            consumption at each minute is shown for a period of only 12 hours
            starting from the starting date and time entered by the user.
          </p>
          <p>
            <strong>Note:</strong>
          </p>
          <p>
            Data is present in the CSV for time starting from January 27th 2021
            04:00 am to January 28th 2021 04:00 am. Other than this range all
            other instants are considered as off. To see any meaningful data in
            the charts these time ranges are preferred.
          </p>
          <p>
            For the calculation of operating load, the average of top 10 Psum
            readings from the provided CSV were used.
          </p>

          <h1 className="text-center mb-4">
            How to gain insights from the 2 charts
          </h1>
          <p class="lead">
            You can use the charts in the following manner to gain some valuable
            insights.
          </p>
          <p>
            1. You can use the power distribution PIE chart to see the idle and
            unloaded totals in a given time window.
          </p>
          <p>
            2. If they are very large, you can use the power time series LINE
            chart to check if they occurred randomly or they occurred in a
            sequence.
          </p>
          <p>
            3. If they occurred in a sequence, that means the work load was not
            sufficient for a given interval of time and it would be better to
            shut off the machine in that time interval to save some cost or
            optimize the other workflows so that the machine is not idling for
            large amount of time in a sequence.
          </p>

          <h1 className="text-center mb-4">Application Improvement Ideas</h1>
          <p class="lead">
            The application can be improved in the following areas:
          </p>
          <p>
            1. The data from the CSV file can be stored in a database and the
            database can be queried according to the required time interval
            instead of loading all records from the CSV for every request.
          </p>
          <p>
            2. The unit tests on both the UI and backend are very barebones and
            can be improved further.
          </p>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
