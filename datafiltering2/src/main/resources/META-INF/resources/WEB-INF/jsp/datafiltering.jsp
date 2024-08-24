<%@page import="java.util.Collections"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.example.datafiltering.data.Data"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Datepicker CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">Data Filtering Form</h2>
		<form action="/filterData" method="post">
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="secType">SEC TYPE</label>
					<%
					Page<Data> dataPage = (Page<Data>) request.getAttribute("dataPage");
					List<Data> dataList = (dataPage != null) ? dataPage.getContent() : Collections.emptyList();
					Set<String> secTypeSet = (Set<String>) request.getAttribute("secTypeSet");
					Set<String> fundTypeSet = (Set<String>) request.getAttribute("fundTypeSet");

					%>
					<select id="secType" name="secType" class="form-control">
						<option value="">Select Sec Type</option>
						<%
						if (secTypeSet != null && !secTypeSet.isEmpty()) {
							for (String secType : secTypeSet) {
						%>
						<option value="<%=secType%>"><%=secType%></option>
						<%
						}
						}
						%>
					</select>
				</div>
				<div class="form-group col-md-4">
					<label for="fundType">FUND TYPE</label>
					 <select id="fundType" name="fundType" class="form-control">
						<option value="">Select Fund Type</option>
						<%
						if (fundTypeSet != null && !fundTypeSet.isEmpty()) {
							for (String fundType : fundTypeSet) {
						%>
						<option value="<%=fundType%>"><%=fundType%></option>
						<%
						}
						}
						%>
					</select>
				</div>
				<div class="form-group col-md-4">
					<label for="calendartext">Calendar Year Month Text</label> <input
						type="text" id="calendartext" name="calendartext"
						class="form-control" />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="donembasıbakıyeMin">DONEM BASI BAKIYE (Min)</label> <input
						type="number" id="donembasıbakıyeMin" name="donembasıbakıyeMin"
						class="form-control" placeholder="Min value" />
				</div>
				<div class="form-group col-md-6">
					<label for="donembasıbakıyeMax">DONEM BASI BAKIYE (Max)</label> <input
						type="number" id="donembasıbakıyeMax" name="donembasıbakıyeMax"
						class="form-control" placeholder="Max value" />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="donemsonubakıyeMin">DONEM SONU BAKIYE (Min)</label> <input
						type="number" id="donemsonubakıyeMin" name="donemsonubakıyeMin"
						class="form-control" placeholder="Min value" />
				</div>
				<div class="form-group col-md-6">
					<label for="donemsonubakıyeMax">DONEM SONU BAKIYE (Max)</label> <input
						type="number" id="donemsonubakıyeMax" name="donemsonubakıyeMax"
						class="form-control" placeholder="Max value" />
				</div>
			</div>

			<div class="form-group">
				<label for="daterange">Select Date Range</label> <input type="text"
					name="daterange" class="form-control datepicker" />
			</div>

			<input type="hidden" name="startDate" /> <input type="hidden"
				name="endDate" />

			<div class="form-row">
				<div class="col-md-6">
					<button type="submit" name="Submit" class="btn btn-primary w-100">Submit</button>
				</div>
				<div class="col-md-6">
					<button type="submit" name="showallbutton"
						class="btn btn-secondary w-100">Show All</button>
				</div>
			</div>
		</form>

		<br>

		<div class="table-wrapper">
			<table class="table table-striped table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>Sec Type</th>
						<th>Fund Type</th>
						<th>Calendar</th>
						<th>Text</th>
						<th>Start Balance</th>
						<th>End Balance</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (dataList != null && !dataList.isEmpty()) {
						for (Data data : dataList) {
					%>
					<tr>
						<td><%=data.getsecType()%></td>
						<td><%=data.getFundType()%></td>
						<td><%=data.getCalenderYearMonth()%></td>
						<td><%=data.getCalenderYearMonthText()%></td>
						<td><%=data.getDonemBasiIsinBakiye()%></td>
						<td><%=data.getDonemSonuIsinBakiye()%></td>
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
			<nav aria-label="Page navigation example">
    <ul class="pagination">
        <% 
            int currentPage = 0;
            if (request.getAttribute("currentPage") != null) {
                currentPage = (int) request.getAttribute("currentPage");
            }
            int totalPages = (dataPage != null) ? dataPage.getTotalPages() : 0;

            int buttonsToShow = 10;

            int startPage = Math.max(0, currentPage - buttonsToShow / 2);
            int endPage = Math.min(totalPages - 1, startPage + buttonsToShow - 1);

            if (endPage - startPage + 1 < buttonsToShow) {
                startPage = Math.max(0, endPage - buttonsToShow + 1);
            }
        %>

        <li class="page-item <%= (currentPage <= 0) ? "disabled" : "" %>">
            <a class="page-link" href="?page=<%= (currentPage - 1) %>&size=<%= dataPage.getSize() %>">Previous</a>
        </li>

        <%
            for (int i = startPage; i <= endPage; i++) {
        %>
            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                <a class="page-link" href="?page=<%= i %>&size=<%= dataPage.getSize() %>"><%= i + 1 %></a>
            </li>
        <%
            }
        %>

        <li class="page-item <%= (currentPage >= totalPages - 1) ? "disabled" : "" %>">
            <a class="page-link" href="?page=<%= (currentPage + 1) %>&size=<%= dataPage.getSize() %>">Next</a>
        </li>
    </ul>
</nav>


		</div>

		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

		<script>
			
			$(function() {
				$('input[name="daterange"]').daterangepicker(
						{
							opens : 'left',
							autoUpdateInput : false,
							locale : {
								format : 'YYYY-MM-DD'
							}
						},
						function(start, end, label) {
							$('input[name="daterange"]').val(
									start.format('YYYY-MM-DD') + ' - '
											+ end.format('YYYY-MM-DD'));
							$('input[name="startDate"]').val(
									start.format('YYYY-MM-DD'));
							$('input[name="endDate"]').val(
									end.format('YYYY-MM-DD'));
						});
			});
		</script>
</body>
</html>
