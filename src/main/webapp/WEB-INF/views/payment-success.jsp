<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%@ include file="template/myheader.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <i class="fa fa-check round box-icon-large box-icon-center box-icon-success mb30"></i>

                <h2 class="text-center">${account.firstName}, your payment was successful!</h2>
                <h5 class="text-center mb30">Booking details has been send to ${account.username}</h5>
                <!-- <ul class="order-payment-list list mb30">
                    <li>
                        <div class="row">
                            <div class="col-xs-9">
                                <h5><i class="fa fa-plane"></i> Flight from London to New York City</h5>

                                <p>
                                    <small>April 24, 2014</small>
                                </p>
                            </div>
                            <div class="col-xs-3">
                                <p class="text-right"><span class="text-lg">$150</span>
                                </p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="row">
                            <div class="col-xs-9">
                                <h5><i class="fa fa-plane"></i> Flight from New York City to London</h5>

                                <p>
                                    <small>April 28, 2014</small>
                                </p>
                            </div>
                            <div class="col-xs-3">
                                <p class="text-right"><span class="text-lg">$187</span>
                                </p>
                            </div>
                        </div>
                    </li>
                </ul> -->
                
            </div>
        </div>
        <div class="gap"></div>
    </div>
    <%@ include file="template/footer.html" %>
    <%@ include file="template/footerscripts.html" %>
</div>
</body>

</html>


