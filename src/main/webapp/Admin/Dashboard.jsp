<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parking Slots</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
        }

        .sidebar {
            height: 100vh;
            background-color: #343a40;
            color: white;
            padding-top: 20px;
        }

        .sidebar a {
            color: white;
            padding: 10px;
            text-decoration: none;
            display: block;
            margin: 5px 0;
        }

        .sidebar a:hover {
            background-color: #007bff;
        }

        .parking-container {
            margin-top: 30px;
        }

        .parking-slot {
            text-align: center;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 0.9rem;
            cursor: pointer;
            transition: transform 0.2s;
            max-width:150px;
        }

        .parking-slot:hover {
            transform: scale(1.05);
        }

        .free {
            background-color: #d4edda;
            border-color: #c3e6cb;
        }

        .occupied {
            background-color: #f8d7da;
            border-color: #f5c6cb;
        }

        .parking-slot div {
            margin: 2px 0;
        }

        /* Modal Style */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .hidden {
            display: none;
        }

        @media (max-width: 768px) {
            .parking-slot {
                font-size: 0.8rem;
                padding: 8px;
            }
        }
    </style>
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <div class="sidebar p-3">
        <h3 class="text-center">Parking System</h3>
        <a href="#">Add Parking</a>
        <a href="#">View Booking History</a>
    </div>

    <!-- Main Content -->
    <div class="container-fluid">
        <!-- Navbar with Date and Time -->
        <nav class="navbar navbar-light bg-light">
            <div class="container-fluid">
                <span class="navbar-text">
                    <strong>Today's Date and Time:</strong> <span id="currentDateTime"></span>
                </span>
            </div>
        </nav>

        <!-- User Info Section -->
        <div class="mt-3">
            <h4>Welcome, ${sessionScope.user.name} (${sessionScope.user.userId})</h4>
        </div>

        <h2 class="mt-3">Parking Slots</h2>

        <!-- Bike Parking Section -->
        <h4>Bike Parking</h4>
        <div class="parking-container row row-cols-2 row-cols-md-4 g-3">
            <c:forEach var="parking" items="${parking}">
                <c:if test="${parking.parkingType == 'Bike'}">
                    <div class="col">
                        <c:choose>
                            <c:when test="${parking.status == 'Free'}">
                                <!-- Free Slot: Show form to book vehicle -->
                                <div class="parking-slot free" onclick="openModal('${parking.parkingId}')">
                                    <div>Slot: ${parking.parkingId}</div>
                                    <div>Status: ${parking.status}</div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="parking-slot occupied">
                                    <div>Slot: ${parking.parkingId}</div>
                                                  <div>Status: ${parking.status}</div>
                                                  <div>Employee Id: ${parking.userId}</div>
                                                  <div>Employee Name: ${parking.userName}</div>

                                                                   <form action="/user/checkOut" method="POST">
                                                                       <input type="hidden" name="parkingId" value="${parking.parkingId}">
                                                                       <button type="submit" class="btn btn-danger mt-2">Checkout</button>
                                                                   </form>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <!-- Car Parking Section -->
        <h4 class="mt-4">Car Parking</h4>
        <div class="parking-container row row-cols-2 row-cols-md-4 g-3">
            <c:forEach var="parking" items="${parking}">
                <c:if test="${parking.parkingType == 'Car'}">
                    <div class="col">
                        <c:choose>
                            <c:when test="${parking.status == 'Free'}">
                                <!-- Free Slot: Show form to book vehicle -->
                                <div class="parking-slot free" onclick="openModal('${parking.parkingId}')">
                                    <div>Slot: ${parking.parkingId}</div>
                                    <div>Status: ${parking.status}</div>
                                </div>
                            </c:when>
       <c:otherwise>
           <div class="parking-slot occupied">
               <div>Slot: ${parking.parkingId}</div>
               <div>Status: ${parking.status}</div>
               <div>Employee Id: ${parking.userId}</div>
               <div>Employee Name: ${parking.userName}</div>

                               <form action="/user/checkOut" method="POST">
                                   <input type="hidden" name="parkingId" value="${parking.parkingId}">
                                   <button type="submit" class="btn btn-danger mt-2">Checkout</button>
                               </form>
           </div>
       </c:otherwise>

                        </c:choose>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>


</body>
</html>
