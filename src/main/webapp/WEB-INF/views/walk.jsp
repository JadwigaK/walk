<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Walk Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc; cellpadding:10;
            cellspacing:10;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333; cellpadding:10;
            cellspacing:10;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0; cellpadding:10;
            cellspacing:10;}
        .tg .tg-4eph{background-color:#f9f9f9}


        a.button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            margin: 10px 15px 10px 8px;
        }
        a.btn{
            background-color: #f44336;
            border: none;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            margin: 10px 15px 10px 8px;
        }
        .table {
            border: 1px;

        }
        .th{ width:10%;
        }
        .value{
            margin: 10px 15px 10px 8px;
        }

    </style>
</head>
<body>
<br>
<h3>Walks List</h3>
<c:if test="${!empty listWalks}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Walk Name</th>
            <th width="160">User ID</th>

        </tr>
        <c:forEach items="${listWalks}" var="walk">
            <tr>
                <td>${walk.id}</td>
                <td>${walk.name}</td>
                <td>${walk.user_id}</td>
            </tr>
        </c:forEach>
    </table>
    </table>
</c:if>
</body>
</html>