<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/admin_header.jsp"/>
<a href="/adminpanel/logout">Wyloguj się</a><br>
<a href="/adminpanel/manage">Słowa do sprawdzenia</a>
<c:forEach var="word" items="${words}">
    <form action="/adminpanel/list" method="post">
        <input type="text" value="${word.word}" data-org-word="${word.word}" name="word" size="40">
        <button class="reject">Edytuj</button>
        <input type="submit" value="OK" style="display: none;">
        <input type="submit" value="Usuń">
        <input type="hidden" name="word_id" value="${word.id}">
    </form>
</c:forEach>
<jsp:include page="../template/doc_footer.jsp"/>