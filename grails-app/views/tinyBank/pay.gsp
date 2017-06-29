<%@ page import="se.tiny.bank.Account" %>

<html>
<head>
    <title>Pay Some Person</title>
    <meta name="layout" content="main" />
</head>
<body>
<div style="padding:10px;">
    <i style="color: #18aa81">${request.message}</i>
    <h2>Pay</h2>
    <g:link action="transactions">View transactions</g:link>
    <br/>
    <br/>
    <g:form name="pay" action="doPayment">
        <b>From:</b>
        <g:select
                name="cr"
                from="${Account.list()}"
                optionKey="id"
                optionValue="${{it.name + " : £" + it.balance}}"
                noSelection="['':'-Choose account to credit-']"/>

        <b style="padding-left:5px">To: </b>
        <g:select
                name="dr"
                from="${Account.list()}"
                optionKey="id"
                optionValue="${{it.name + " : £" + it.balance}}"
                noSelection="['':'-Choose account to debit-']"/>
        <b style="padding-left:5px">Amount(£):</b>
        <g:field type="number" name="amt" />
        <input type="submit" value="Pay">
    </g:form>
</div>
</body>
</html>
