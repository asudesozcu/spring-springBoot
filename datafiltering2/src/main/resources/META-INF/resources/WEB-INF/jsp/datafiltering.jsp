<!DOCTYPE html>
<%@page import="com.example.datafiltering.data.Data"%>
<%@page import="java.util.List"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Filtering</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Takvim için -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>

   
</head>

<body>

    <div class="container mt-4">
        <form action="/filterData" method="post" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="secType">SEC TYPE</label>
                    <input type="text" id="secType" name="secType" class="form-control"/>
                </div>

                <div class="form-group col-md-4">
                    <label for="fundType">FUND TYPE</label>
                    <input type="text" id="fundType" name="fundType" class="form-control"/>
                </div>

                <div class="form-group col-md-4">
                    <label for="calendartext">Calendar Year Month Text</label>
                    <input type="text" id="calendartext" name="calendartext" class="form-control"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="donembasıbakıyeMin">DONEM BASI BAKIYE</label>
                    <input type="number" id="donembasıbakıyeMin" name="donembasıbakıyeMin" class="form-control" placeholder="Min value"/>
                    <input type="number" id="donembasıbakıyeMax" name="donembasıbakıyeMax" class="form-control mt-2" placeholder="Max value"/>
                </div>

                <div class="form-group col-md-6">
                    <label for="donemsonubakıyeMin">DONEM SONU BAKIYE</label>
                    <input type="number" id="donemsonubakıyeMin" name="donemsonubakıyeMin" class="form-control" placeholder="Min value"/>
                    <input type="number" id="donemsonubakıyeMax" name="donemsonubakıyeMax" class="form-control mt-2" placeholder="Max value"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="calendarMin">Calendar Min Date</label>
                    <input type="text" id="calendarMin" name="calendarMin" class="form-control datepicker" placeholder="Min date"/>
                </div>

                <div class="form-group col-md-6">
                    <label for="calendarMax">Calendar Max Date</label>
                    <input type="text" id="calendarMax" name="calendarMax" class="form-control datepicker" placeholder="Max date"/>
                </div>
            </div>

             <div class="form-row">
                <div class="col-md-6">
                    <button type="submit" name="Submit" class="btn btn-primary w-100">Submit</button>
                </div>
                <div class="col-md-6">
                    <button type="submit" name="showallbutton" class="btn btn-secondary w-100">Show All</button>
                </div>
            </div>
            
        </form>

        <div class="table-wrapper">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Type</th>
                        <th>Fund</th>
                        <th>Calendar</th>
                        <th>Text</th>
                        <th>Start Balance</th>
                        <th>End Balance</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Data> dataList = (List<Data>) request.getAttribute("dataList");
                        if (dataList != null && !dataList.isEmpty()) {
                            for (Data data : dataList) {
                    %>
                    <tr>
                        <td><%= data.getsecType() %></td>
                        <td><%= data.getFundType() %></td>
                        <td><%= data.getCalenderYearMonth() %></td>
                        <td><%= data.getCalenderYearMonthText() %></td>
                        <td><%= data.getDonemBasiIsinBakiye() %></td>
                        <td><%= data.getDonemSonuIsinBakiye() %></td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No data available</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap calendar formatı -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
    $(function() {
        $(".datepicker").datepicker({
            dateFormat: "yy-mm-dd"  
        });
    });
    </script>
</body>

</html>
