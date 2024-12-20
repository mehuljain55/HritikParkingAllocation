<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Parking History</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Parking History</h2>

        <!-- Form to select dates -->
        <form action="ViewParkingHistory" method="get" class="mb-4">
            <div class="form-row align-items-end">
                <div class="col-md-5">
                    <label for="startDate">Start Date</label>
                    <input type="date" id="startDate" name="startDate" class="form-control" required>
                </div>
                <div class="col-md-5">
                    <label for="endDate">End Date</label>
                    <input type="date" id="endDate" name="endDate" class="form-control" required>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary btn-block">Fetch Data</button>
                </div>
            </div>
        </form>

        <!-- Display error message if any -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Parking History Table -->
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>S.No</th>
                    <th>Parking Slot</th>
                    <th>Employee ID</th>
                    <th>Employee Name</th>
                    <th>Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="history" items="${parkingHistory}">
                    <tr>
                        <td>${history.sno}</td>
                        <td>${history.parkingSlot}</td>
                        <td>${history.employeeId}</td>
                        <td>${history.employeeName}</td>
                        <td>${history.date}</td>
                        <td>${history.startTime}</td>
                        <td>${history.endTime}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty parkingHistory}">
                    <tr>
                        <td colspan="7" class="text-center">No parking history found for the selected dates.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>

        <a href="index.jsp" class="btn btn-secondary">Go Back</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
