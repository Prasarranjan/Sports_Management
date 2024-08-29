<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sports Club Registration Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-header h1 {
            color: #007bff;
        }
        .btn-custom {
            background-color: #007bff;
            color: #ffffff;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .btn-reset {
            background-color: #6c757d;
            color: #ffffff;
        }
        .btn-reset:hover {
            background-color: #5a6268;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="form-header text-center">
        <h1>Sports Club Registration Form</h1>
    </div>
    <form id="playername"  enctype="multipart/form-data">
        <div class="form-row mb-4">
            <!-- Club Name first -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="clubName">Club Name</label>
                    <select id="clubName" name="clubName" class="form-control">
<%--                        <span id="clubError" style="color: red;"></span>--%>
                        <option value="">Select Club</option>
                        <%
                            // Database connection details
                            String jdbcURL = "jdbc:mysql://localhost:3306/SportsClub";
                            String jdbcUsername = "root";  // change to your username
                            String jdbcPassword = "prasar123";  // change to your password

                            // Query to fetch club names and IDs
                            String sql = "SELECT clubId, clubName FROM Club";

                            Connection conn = null;
                            Statement stmt = null;
                            ResultSet rs = null;

                            try {
                                // Load the MySQL JDBC driver
                                Class.forName("com.mysql.cj.jdbc.Driver");

                                // Establish connection to the database
                                conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

                                // Execute query
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(sql);

                                // Populate the dropdown with club names and bind the ID as value
                                while (rs.next()) {
                                    int clubId = rs.getInt("clubId");
                                    String clubName = rs.getString("clubName");
                        %>
                        <option value="<%= clubId %>"><%= clubName %></option>
                        <%
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                // Close resources
                                if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
                                if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
                                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
                            }
                        %>
                    </select>
                    <!-- Hidden input to store the selected clubId -->
                    <input type="hidden" id="clubId" name="clubId">
                </div>
            </div>

            <!-- Sports Name second -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="sportsName">Sports Name</label>
                    <select id="sportsName" class="form-control">
                        <option value="">Select Sports</option>
                    </select>
<%--                    <span id="sportsError" style="color: red;"></span>--%>

                    <!-- Hidden input to capture sportsId -->
                    <input type="hidden" id="sportsId" name="sportsId" value="">
                </div>
            </div>
        </div>

        <!-- Rest of the form -->
        <fieldset class="border p-4 mb-4">
            <legend class="w-auto">Applicant Details</legend>
            <div class="form-row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="applicantName">Name</label>
                        <input type="text" id="applicantName" class="form-control" name="name" placeholder="Full Name">
                        <div id="nameError" class="error-message"></div>

                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" class="form-control" name="email" placeholder="Email Address">
                        <span id="emailError" style="color: red;"></span>

                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="mobileNo">Mobile No</label>
                        <input type="text" id="mobileNo" class="form-control" name="mobile" placeholder="Mobile Number">
                        <span id="mobileError" class="error"></span>

                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" class="form-control">
<%--                        <span id="dobError" class="error"></span>--%>

                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select id="gender" class="form-control" name="gender">
                            <option>Select Gender</option>
                            <option>Male</option>
                            <option>Female</option>
                            <option>Other</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="photo">Upload Photo</label>
                        <input type="file" id="photo" name="image" class="form-control-file">


                    </div>
                </div>
            </div>
        </fieldset>
        <div class="form-group text-center">
            <button type="submit" class="btn btn-custom">Submit</button>
            <button type="reset" class="btn btn-reset">Reset</button>
        </div>
    </form>

</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="Ajax.js"></script>
</body>
</html>
