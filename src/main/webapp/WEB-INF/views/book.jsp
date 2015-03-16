<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>

<html>

<head>
    <%@ include file="template/header.html" %>
    <%@ include file="template/styles.html" %>
    <%@ include file="template/meta.html" %>
    <%@ include file="template/scripts.html" %>

</head>

<body>
<div class="global-wrap">
    <%@ include file="template/myheader.html" %>

    <div class="container">
        <h1 class="page-title">Booking ...</h1>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <aside class="sidebar-left">
                    <ul class="nav nav-pills nav-stacked nav-side mb30">
                        <li class="active"><a href="#">Booking Details</a>
                        </li>
                        <li><a href="/booking/payment-form">Pricing & Payment</a>
                        </li>
                        <li><a href="#">Complete</a>
                        </li>
                    </ul>
                </aside>
            </div>
            <div class="col-md-9">
                <p>You are booking a space at the ${carpark.codename} which is located at ${carpark.airport.name}.</p>

                <h2>Please enter booking details</h2>
                <form:form commandName="booking" action="/booking/payment-form" method="get">
                    <div class="row row-wrap">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="input-daterange" data-date-format="yyyy-MM-dd">
                                    <div class="col-md-6">
                                        <div class="form-group form-group-lg form-group-icon-left">
                                            <label>Arrival Date</label>
                                            <small>When your car will arrive at carpark</small>
                                            <form:input path="arrivalDate" class="date-pick form-control" name="start"
                                                        type="text"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group form-group-lg form-group-icon-left">
                                            <label>Departure Date</label>
                                            <small>Date your booking should end</small>
                                            <form:input path="departureDate" class="date-pick form-control" name="end"
                                                        type="text"/>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group form-group-lg form-group-icon-left">
                                        <label>Arrival Time</label>
                                        <small>When your car will arrive at carpark</small>
                                        <form:input path="arrivalTime" class="time-pick form-control" name="start"
                                                    value="12:00 AM" type="text"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-group-lg form-group-icon-left">
                                        <label>Departure Time</label>
                                        <small>Time your to book up to</small>
                                        <form:input path="departureTime" class="time-pick form-control" name="end"
                                                    value="12:00 AM" type="text"/>
                                    </div>
                                </div>


                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group form-group-icon-left"><i
                                            class="fa fa-car input-icon input-icon-bounce"></i>
                                        <label>Car Model</label>
                                        <form:input class="form-control" path="carModel"
                                                    placeholder="e.g. nissan, honda, ferrai" type="text"/>
                                    </div>
                                    <div class="form-group form-group-icon-left"><i
                                            class="fa fa-car input-icon input-icon-bounce"></i>
                                        <label>Car Body</label>
                                        <form:input class="form-control" path="carBody"
                                                    placeholder="e.g. van, hatchback, estate" type="text"/>
                                    </div>
                                    <div class="form-group form-group-icon-left"><i
                                            class="fa fa-car input-icon input-icon-bounce"></i>
                                        <label>Car Registration</label>
                                        <form:input class="form-control" path="carRegistration"
                                                    placeholder="Please specify Car Registration" type="text"/>
                                    </div>
                                    <hr>
                                    <div class="form-group form-group-icon-left"><i
                                            class="fa fa-plane input-icon input-icon-swing"></i>
                                        <label>Flight Number</label>
                                        <form:input class="form-control" path="flightNumber"
                                                    placeholder="Please specify Flight Number" type="text"/>
                                    </div>

                                </div>
                                <div class="col-md-6">

                                    <div class="form-group form-group-lg form-group-icon-left"><i
                                            class="fa fa-car input-icon input-icon-bounce"></i>
                                        <label>Select Car park Space type</label>

                                        <form:select path="spaceType.id" class="typeahead form-control">
                                            <c:forEach items="${spaceTypeList}" var="spacetype">
                                                <c:set var="spaceTypeChosen" scope="request" value="${spacetype.id}"/>
                                                <form:option value="${spacetype.id}" label="${spacetype.name}"/>
                                            </c:forEach>

                                        </form:select>
                                        <c:choose>
                                            <c:when test="${spaceTypeChosen == 1}">
                                                newprice = ${booking.carparkSpace.carpark.regularprice}
                                                <form:hidden class="form-control" value="newprice" disabled="true"
                                                             path="base_price"/>
                                                <form:label class="form-control" value="newprice" disabled="true"
                                                            path="base_price"/>

                                            </c:when>
                                            <c:when test="${spaceTypeChosen == 2}">
                                                newprice = ${booking.carparkSpace.carpark.regularprice} + (${booking.carparkSpace.carpark.regularprice} * 0.30);
                                                <form:hidden class="form-control" value="newprice" disabled="true"
                                                             path="base_price"/>
                                                <form:label class="form-control" value="newprice" disabled="true"
                                                            path="base_price"/>
                                            </c:when>
                                            <c:when test="${spaceTypeChosen == 3}">
                                                newprice = ${booking.carparkSpace.carpark.regularprice} + (${booking.carparkSpace.carpark.regularprice} * 0.20);
                                                <form:hidden class="form-control" value="newprice" disabled="true"
                                                             path="base_price"/>
                                                <form:label class="form-control" value="newprice" disabled="true"
                                                            path="base_price"/>
                                            </c:when>
                                            <c:otherwise>
                                                <form:hidden class="form-control" value="newprice" disabled="true"
                                                             path="base_price"/>
                                                <form:label class="form-control" value="newprice" disabled="true"
                                                            path="base_price"/>
                                                newprice = ${booking.carparkSpace.price}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="form-group">
                                        <label>Base Price</label>
                                            <%--  <c:set var="spaceTypeChosen" scope="session" value="1"/>
                                          <c:set var="newprice" scope="session" value="${space.price}"/>
                                          <c:choose>
                                              <c:when test="${spaceTypeChosen == 1}">
                                                  newprice = ${booking.carparkSpace.price}
                                              </c:when>
                                              <c:when test="${spaceTypeChosen == 2}">
                                                  newprice = ${booking.carparkSpace.price} + (${booking.carparkSpace.price} * 0.30);
                                              </c:when>
                                              <c:when test="${spaceTypeChosen == 3}">
                                                  newprice = ${booking.carparkSpace.price} + (${booking.carparkSpace.price} * 0.20);
                                              </c:when>
                                              <c:otherwise>
                                                  newprice = ${booking.carparkSpace.price}
                                              </c:otherwise>
                                          </c:choose>--%>

                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-lg pull-right">Continue
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
                <hr>

            </div>
        </div>
    </div>
    <%@ include file="template/footer.html" %>
    <%@ include file="template/footerscripts.html" %>
</div>
</body>

</html>


