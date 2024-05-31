<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lớp Học</title>
    <style>
        .form-search {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 160px;
            width: 250px;
            border: 1px solid;
        }

        .form-search form {
            padding: 20px;
        }

        .form-input {
            display: initial;
            justify-content: center;
            align-items: center;
        }

        .form-input form {
            padding: 20px;
        }

        table, th, tr, td {
            border: 1px solid;
        }
    </style>
</head>
<body>
<div>
    <div class="form-search">
        <form action="/lop-hoc/tim-kiem" method="get">
            Tên lớp học: <input type="text" name="ten" value="${search.ten}"/><br>
            Địa điểm lớp học: <input type="text" name="diaDiem" value="${search.diaDiem}"/><br>
            <button type="submit">Search</button>
        </form>
    </div>
    <div class="form-input">
        <form method="post">
            <label>ID: </label>
            <label>
                <input type="text" name="id" value="${lopHoc.id}" disabled/>
            </label>
            <br/>

            <label>Tên lớp: </label>
            <label>
                <input type="text" name="name" value="${lopHoc.name}"/>
            </label>
            <span style="color: red">${messageError.name[0]}</span>
            <br/>

            <label>Địa điểm lớp học: </label>
            <label>
                <input type="text" name="location" value="${lopHoc.location}"/>
            </label>
            <span style="color: red">${messageError.location[0]}</span>
            <br/>
            <button type="button"><a href="/lop-hoc/hien-thi-tat-ca">Clear</a></button>
            <c:choose>
                <c:when test="${isUpdate}">
                    <button type="submit" formaction="/lop-hoc/update/${lopHoc.id}">Update</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" formaction="/lop-hoc/add">Add</button>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>ID Lớp Học</th>
                <th>Tên Lớp Học</th>
                <th>Địa Điểm Lớp Học</th>
                <th colspan="2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lh" items="${lopHocList}">
                <tr>
                    <td>${lh.id}</td>
                    <td>${lh.name}</td>
                    <td>${lh.location}</td>
                    <td><a type="button" href="/lop-hoc/view-update/${lh.id}">Edit</a></td>
                    <td><a type="button" href="/lop-hoc/remove/${lh.id}">Remove</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
