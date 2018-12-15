<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/admin_header.jsp"/>
<a href="/adminpanel/logout">Wyloguj się</a><br>
<a href="/adminpanel/manage">Słowa do sprawdzenia</a><br>
<div style="display: inline-block; padding-right: 100px; float: left;">
    <p>
        Lista zaakceptowanych słów.
    </p>
    <p>Zaakceptowane słowa: <strong>${count}</strong></p>
    <span style="color: red;"><c:out value="${error}"></c:out></span>
    <c:forEach var="word" items="${words}">
        <form action="/adminpanel/list" method="post">
            <input type="text" value="${word.word}" name="word" size="40" disabled>
            <button class="edit">Edytuj</button>
            <input type="submit" value="OK" style="display: none;">
            <input class="delete" type="submit" value="Usuń">
            <input type="hidden" name="word_id" value="${word.id}">
            <input type="hidden" name="edit" value="0">
        </form>
    </c:forEach>
</div>
<div style="display: inline-block; padding-left: 100px;  border-left: 2px dashed black; float: left;">
    <p style="margin-bottom: 50px;">
        Lista odrzuconych słów.
    </p>
    <c:forEach var="word" items="${rejectedWords}">
        <form action="/adminpanel/list" method="post">
            <input type="submit" value="Poddaj ponownej weryfikacji">
            <input type="text" value="${word.word}" name="word" size="40" disabled>
            <span>Powód: ${word.rejectReason}</span>
            <input type="hidden" name="word_id" value="${word.id}">
            <input type="hidden" name="verify" value="1">
        </form>
    </c:forEach>
</div>
<div style="clear: both;"></div>
<jsp:include page="../template/doc_footer.jsp"/>