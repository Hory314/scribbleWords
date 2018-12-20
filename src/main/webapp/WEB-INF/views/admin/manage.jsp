<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/admin_header.jsp"/>
<a href="<c:url value="/adminpanel/logout"/>">Wyloguj się</a><br>
<a href="<c:url value="/adminpanel/list"/>">Sprawdzone słowa</a>
<p>
    Lista słów do sprawdzenia.
</p>
<p>Słów do sprawdzenia: <strong>${count}</strong></p>
<span style="color: red;"><c:out value="${error}"/></span>
<c:forEach var="word" items="${words}">
    <form action="<c:url value="/adminpanel/manage"/>" method="post">
        <input type="text" value="${word.word}" data-org-word="${word.word}" name="word" size="40">
        <input type="submit" value="Akceptuj">
        <button class="reject">Odrzuć</button>
        <input type="text" name="reject_reason" placeholder="podaj powód" style="display: none;">
        <input type="submit" value="OK" style="display: none;">
        <input type="hidden" name="word_id" value="${word.id}">
        <input type="hidden" name="accept" value="1">
    </form>
</c:forEach>
<jsp:include page="../template/doc_footer.jsp"/>