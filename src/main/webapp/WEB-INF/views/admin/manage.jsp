<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/admin_header.jsp"/>
<a href="/adminpanel/logout">Wyloguj się</a>
<p>Słów do sprawdzenia: <strong>${count}</strong></p>
<c:forEach var="word" items="${words}">
    <form action="/adminpanel/manage" method="post">
        <input type="text" value="${word.word}" disabled>
        <input type="submit" value="Akceptuj">
        <button class="reject">Odrzuć</button>
        <input type="text" name="reject_reason" placeholder="podaj powód" style="display: none;">
        <input type="submit" value="OK" style="display: none;">
        <input type="hidden" name="word_id" value="${word.id}">
    </form>
</c:forEach>
<jsp:include page="../template/doc_footer.jsp"/>