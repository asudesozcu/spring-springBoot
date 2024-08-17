<%@ taglib prefix="c" uri="jakarta.tags.core" %>


    <!DOCTYPE html>
    <html lang="en">

    <head>
      <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >

         <title>Welcome Todos Page</title>
   
 
   
   
    </head>

    <body>
        <div>Welcome to in28min</div>
        <hr> 
        <h1>Your todos are: </h1>

        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                </tr>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>

                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>



                    </tr>
                </c:forEach>
            </tbody>
            </thead>

        </table>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
        
        <!-- <div>Your password: ${password1}</div> -->

    </body>

    </html>