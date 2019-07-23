<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>

    <form:form action="/" method="post" modelAttribute="cityForm">
        <table style="with: 50%">

            <tr>
                <form:select path="cityName">
                    <form:option value="" label="Select City" />
                    <form:options items="${cityForm.cityList}" />
                </form:select>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form:form>

    <c:if test="${not empty errorMsg}">
        <span style="color:red">${errorMsg}</span>
    </c:if>

    <div class="container">
        <c:if test="${not empty cityData}">
            <div class="starter-template">
                <h2>OpenWeatherMap Test</h2>

                <table>
                    <tr>
                        <td>Current Date</td>
                        <td>${cityData.currentDate}</td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>${cityData.cityName}</td>
                    </tr>
                    <tr>
                        <td>Weather Description</td>
                        <td>${cityData.weatherDescription}</td>
                    </tr>
                    <tr>
                        <td>Temperature</td>
                        <td>${cityData.temperatureC} / ${cityData.temperatureF}</td>
                    </tr>
                    <tr>
                        <td>Sunrise Time</td>
                        <td>${cityData.sunRiseTime}</td>
                    </tr>
                    <tr>
                        <td>Sunset Time</td>
                        <td>${cityData.sunSetTime}</td>
                    </tr>
                </table>

            </div>
        </c:if>
    </div>
</body>
</html>
