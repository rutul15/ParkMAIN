<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="row">
            <div class="col-md-4">
                <img class="pp-img" src="img/paypal.png" alt="Image Alternative text" title="Image Title"/>

                <p>Important: You will be redirected to PayPal's website to securely complete your payment.</p><a
                    class="btn btn-primary">Checkout via Paypal</a>
            </div>

            <div class="col-md-4">
                <h4>Pay via Credit/Debit Card</h4>
                <ul class="card-select">
                    <li>
                        <img class="card-select-img" src="img/payment/visa-curved-64px.png" alt="Image Alternative text"
                             title="Image Title"/>

                        <div class="card-select-data">
                            <p class="card-select-number">xxxx xxxx xxxx 1456</p>
                            <input class="form-control card-select-cvc" type="text" placeholder="CVC"/>
                        </div>
                    </li>
                    <li>
                        <img class="card-select-img" src="img/payment/maestro-curved-64px.png"
                             alt="Image Alternative text" title="Image Title"/>

                        <div class="card-select-data">
                            <p class="card-select-number">xxxx xxxx xxxx 6698</p>
                            <input class="form-control card-select-cvc" type="text" placeholder="CVC"/>
                        </div>
                    </li>
                </ul>
                <div class="gap gap-small"></div>
                <h4>Pay with new Card</h4>

                <form class="cc-form" action="/booking/payment-successful">
                    <div class="clearfix">
                        <div class="form-group form-group-cc-number">
                            <label>Card Number</label>
                            <input class="form-control" placeholder="xxxx xxxx xxxx xxxx" type="text"/><span
                                class="cc-card-icon"></span>
                        </div>
                        <div class="form-group form-group-cc-cvc">
                            <label>CVC</label>
                            <input class="form-control" placeholder="xxxx" type="text"/>
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="form-group form-group-cc-name">
                            <label>Cardholder Name</label>
                            <input class="form-control" type="text"/>
                        </div>
                        <div class="form-group form-group-cc-date">
                            <label>Valid Thru</label>
                            <input class="form-control" placeholder="mm/yy" type="text"/>
                        </div>
                    </div>
                    <div class="checkbox checkbox-small">
                        <label>
                            <input class="i-check" type="checkbox" checked/>Add to My Cards</label>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg pull-right">Proceed Payment</button>
                    </div>

                </form>
            </div>

            <div class="col-md-4">
                <div class="booking-item-payment">
                    <header class="clearfix">
                        <a class="booking-item-payment-img" href="#">
                            <img src="img/car.png" alt="Image Alternative text" title="Image Title"/>
                        </a>
                        <h5 class="booking-item-payment-title"><a href="#">Car park Name</a></h5>
                    </header>
                    <ul class="booking-item-payment-details">
                        <li>
                            <h5>Booking for {number} days</h5>

                            <div class="booking-item-payment-date">
                                <p class="booking-item-payment-date-day">April, 26</p>

                                <p class="booking-item-payment-date-weekday">Saturday</p>
                            </div>
                            <i class="fa fa-arrow-right booking-item-payment-date-separator"></i>

                            <div class="booking-item-payment-date">
                                <p class="booking-item-payment-date-day">May, 3</p>

                                <p class="booking-item-payment-date-weekday">Saturday</p>
                            </div>
                        </li>
                        <li>
                            <h5>Car (2 Passengers)</h5>
                            <ul class="booking-item-payment-price">
                                <li>
                                    <p class="booking-item-payment-price-title">Equipment</p>

                                    <p class="booking-item-payment-price-amount">$295</p>
                                </li>
                                <li>
                                    <p class="booking-item-payment-price-title">7 days</p>

                                    <p class="booking-item-payment-price-amount">$70
                                        <small>/per day</small>
                                    </p>
                                </li>
                                <li>
                                    <p class="booking-item-payment-price-title">Taxes</p>

                                    <p class="booking-item-payment-price-amount">$5
                                        <small>/per day</small>
                                    </p>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <p class="booking-item-payment-total">Total trip: <span>$842</span>
                    </p>
                </div>
            </div>
        </div>
        <div class="gap"></div>
    </div>
    <%@ include file="template/footer.html" %>
    <%@ include file="template/footerscripts.html" %>
</div>
</body>

</html>


